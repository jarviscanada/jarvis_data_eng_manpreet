package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.Application;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.SecurityOrderDao;
import ca.jrvs.apps.trading.model.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    private Logger logger = LoggerFactory.getLogger(Application.class);

    private AccountDao accountDao;
    private SecurityOrderDao securityOrderDao;
    private QuoteDao quoteDao;
    private PositionDao positionDao;

    public OrderService(AccountDao accountDao, SecurityOrderDao securityOrderDao, QuoteDao quoteDao,
                        PositionDao positionDao) {
        this.accountDao = accountDao;
        this.securityOrderDao = securityOrderDao;
        this.quoteDao = quoteDao;
        this.positionDao = positionDao;
    }

    /**
     * Execute a market order
     *
     * @param orderDto market order
     * @return SecurityOrder from security_order table
     * @throws org.springframework.dao.DataAccessException if unable to get data from dao
     * @throws IllegalArgumentException for invalid input
     *  @return securityOrder
     */
    public SecurityOrder executeMarketOrder(MarketOrderDto orderDto) {
        orderDto.setTicker(orderDto.getTicker().toUpperCase());
        checkTicker(orderDto.getTicker());

        Quote quote = quoteDao.findById(orderDto.getTicker()).get();
        Account account = accountDao.findById(orderDto.getAccountId()).get();

        SecurityOrder order = new SecurityOrder();
        order.setStatus("CREATED");
        order.setAccountId(orderDto.getAccountId());
        order.setSize(orderDto.getSize());
        order.setTicker(orderDto.getTicker());

        if (orderDto.getSize() > 0) {
            order.setPrice(quote.getAskPrice());
            handleBuyMarketOrder(order, account);
        } else if (orderDto.getSize() < 0) {
            order.setPrice(quote.getBidPrice());
            handleSellMarketOrder(orderDto, order, account);
        } else {
            throw new IllegalArgumentException("Size can't be 0");
        }
        securityOrderDao.save(order);
        return order;
    }

    private void checkTicker(String ticker) {
        if (ticker == null || !ticker.matches("[A-Za-z]+")) {
            throw new IllegalArgumentException("Incorrect Ticker format");
        }
    }

    /**
     * Helper method that execute a buy order
     *
     * @param securityOrder to be saved in database
     * @param account acount
     */
    protected void handleBuyMarketOrder(SecurityOrder securityOrder,
                                        Account account) {
        Double totalPrice = securityOrder.getPrice() * securityOrder.getSize();
        if (account.getAmount() - totalPrice >= 0) {
            account.setAmount(account.getAmount() - totalPrice);
            accountDao.save(account);
            securityOrder.setStatus("FILLED");
        } else {
            securityOrder.setStatus("CANCELLED");
            securityOrder.setNotes("Insufficient balance in the Account");
        }
    }

    /**
     * Helper method that execute a sell order
     *
     * @param marketOrderDto user order
     * @param securityOrder to be saved in database
     * @param account account
     */
    protected void handleSellMarketOrder(MarketOrderDto marketOrderDto, SecurityOrder securityOrder,
                                         Account account) {
        Optional<Position> optionalPosition = positionDao
                .getByAccountIdAndTicker(marketOrderDto.getAccountId(), marketOrderDto.getTicker());

        if (!optionalPosition.isPresent()) {
            throw new IllegalArgumentException("No Position size available to sell");
        }

        Position position = optionalPosition.get();

        if (position.getPosition() < marketOrderDto.getSize() * (-1)) {
            securityOrder.setStatus("CANCELLED");
            securityOrder.setNotes("Size is not enough to complete the order");
        } else {
            Double totalPrice = securityOrder.getPrice() * -marketOrderDto.getSize();
            account.setAmount(account.getAmount() + totalPrice);
            accountDao.save(account);
            securityOrder.setStatus("FILLED");
        }

    }

}
