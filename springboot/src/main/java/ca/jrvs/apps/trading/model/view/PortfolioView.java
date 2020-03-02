package ca.jrvs.apps.trading.model.domain;

import java.util.List;

public class PortfolioView {
    private Account account;
    private List<Position> positions;

    public Account getAccout() {
        return account;
    }

    public void setAccout(Account account) {
        this.account = account;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
