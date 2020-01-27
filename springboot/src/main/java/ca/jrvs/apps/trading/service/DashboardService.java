package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.PositionDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.*;
import ca.jrvs.apps.trading.model.domain.PortfolioView;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DashboardService {

    private TraderDao traderDao;
    private PositionDao positionDao;
    private AccountDao accountDao;
    private QuoteDao quoteDao;

    @Autowired
    public DashboardService(TraderDao traderDao, PositionDao positionDao, AccountDao accountDao, QuoteDao quoteDao){
        this.accountDao = accountDao;
        this.positionDao = positionDao;
        this.quoteDao = quoteDao;
        this.traderDao = traderDao;
    }

    public TraderAccountView getTraderAccount(Integer traderId){
        if(traderId == null){
            throw new IllegalArgumentException("Trader ID cannot be null");
        }

        Trader trader = traderDao.findById(traderId).get();
        Account account = findAccountByTraderId(traderId);
        TraderAccountView view = new TraderAccountView();
        view.setAccount(account);
        view.setTrader(trader);
        return view;
    }

    public PortfolioView getProfileViewByTraderId(Integer traderId){
        if(traderId == null){
            throw new IllegalArgumentException("Trader ID cannot be null");
        }

        PortfolioView view = new PortfolioView();
        Account account = findAccountByTraderId(traderId);
        List<Position> positions = positionDao.findPositionByAccountID(account.getID());
        view.setPositions(positions);
        view.setAccout(account);
        return view;
    }

    private Account findAccountByTraderId(Integer traderId){
            return accountDao.findAccountByTraderID(traderId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid traderID"));

    }
}