package ca.jrvs.apps.trading.model.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IexQuote {
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("calculationPrice")
    @Expose
    private String calculationPrice;
    @SerializedName("open")
    @Expose
    private String open;
    @SerializedName("openTime")
    @Expose
    private String openTime;
    @SerializedName("close")
    @Expose
    private String close;
    @SerializedName("closeTime")
    @Expose
    private String closeTime;
    @SerializedName("high")
    @Expose
    private String high;
    @SerializedName("low")
    @Expose
    private String low;
    @SerializedName("latestPrice")
    @Expose
    private String latestPrice;
    @SerializedName("latestSource")
    @Expose
    private String latestSource;
    @SerializedName("latestTime")
    @Expose
    private String latestTime;
    @SerializedName("latestUpdate")
    @Expose
    private String latestUpdate;
    @SerializedName("latestVolume")
    @Expose
    private String latestVolume;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("iexRealtimePrice")
    @Expose
    private String iexRealtimePrice;
    @SerializedName("iexRealtimeSize")
    @Expose
    private String iexRealtimeSize;
    @SerializedName("iexLastUpdated")
    @Expose
    private String iexLastUpdated;
    @SerializedName("delayedPrice")
    @Expose
    private String delayedPrice;
    @SerializedName("delayedPriceTime")
    @Expose
    private String delayedPriceTime;
    @SerializedName("extendedPrice")
    @Expose
    private String extendedPrice;
    @SerializedName("extendedChange")
    @Expose
    private String extendedChange;
    @SerializedName("extendedChangePercent")
    @Expose
    private String extendedChangePercent;
    @SerializedName("extendedPriceTime")
    @Expose
    private String extendedPriceTime;
    @SerializedName("previousClose")
    @Expose
    private String previousClose;
    @SerializedName("previousVolume")
    @Expose
    private String previousVolume;
    @SerializedName("change")
    @Expose
    private String change;
    @SerializedName("changePercent")
    @Expose
    private String changePercent;
    @SerializedName("iexMarketPercent")
    @Expose
    private String iexMarketPercent;
    @SerializedName("iexVolume")
    @Expose
    private String iexVolume;
    @SerializedName("avgTotalVolume")
    @Expose
    private String avgTotalVolume;
    @SerializedName("iexBidPrice")
    @Expose
    private String iexBidPrice;
    @SerializedName("iexBidSize")
    @Expose
    private String iexBidSize;
    @SerializedName("iexAskPrice")
    @Expose
    private String iexAskPrice;
    @SerializedName("iexAskSize")
    @Expose
    private String iexAskSize;
    @SerializedName("marketCap")
    @Expose
    private String marketCap;
    @SerializedName("week52High")
    @Expose
    private String week52High;
    @SerializedName("week52Low")
    @Expose
    private String week52Low;
    @SerializedName("ytdChange")
    @Expose
    private String ytdChange;
    @SerializedName("peRatio")
    @Expose
    private String peRatio;
    @SerializedName("lastTradeTime")
    @Expose
    private String lastTradeTime;
    @SerializedName("isUSMarketOpen")
    @Expose
    private String isUSMarketOpen;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCalculationPrice() {
        return calculationPrice;
    }

    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(String latestPrice) {
        this.latestPrice = latestPrice;
    }

    public String getLatestSource() {
        return latestSource;
    }

    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public String getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(String latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public String getLatestVolume() {
        return latestVolume;
    }

    public void setLatestVolume(String latestVolume) {
        this.latestVolume = latestVolume;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    public void setIexRealtimePrice(String iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    public String getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    public void setIexRealtimeSize(String iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    public String getIexLastUpdated() {
        return iexLastUpdated;
    }

    public void setIexLastUpdated(String iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    public String getDelayedPrice() {
        return delayedPrice;
    }

    public void setDelayedPrice(String delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    public String getDelayedPriceTime() {
        return delayedPriceTime;
    }

    public void setDelayedPriceTime(String delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    public String getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(String extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public String getExtendedChange() {
        return extendedChange;
    }

    public void setExtendedChange(String extendedChange) {
        this.extendedChange = extendedChange;
    }

    public String getExtendedChangePercent() {
        return extendedChangePercent;
    }

    public void setExtendedChangePercent(String extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    public String getExtendedPriceTime() {
        return extendedPriceTime;
    }

    public void setExtendedPriceTime(String extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    public String getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(String previousClose) {
        this.previousClose = previousClose;
    }

    public String getPreviousVolume() {
        return previousVolume;
    }

    public void setPreviousVolume(String previousVolume) {
        this.previousVolume = previousVolume;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public String getIexMarketPercent() {
        return iexMarketPercent;
    }

    public void setIexMarketPercent(String iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    public String getIexVolume() {
        return iexVolume;
    }

    public void setIexVolume(String iexVolume) {
        this.iexVolume = iexVolume;
    }

    public String getAvgTotalVolume() {
        return avgTotalVolume;
    }

    public void setAvgTotalVolume(String avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    public String getIexBidPrice() {
        return iexBidPrice;
    }

    public void setIexBidPrice(String iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    public String getIexBidSize() {
        return iexBidSize;
    }

    public void setIexBidSize(String iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    public String getIexAskPrice() {
        return iexAskPrice;
    }

    public void setIexAskPrice(String iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    public String getIexAskSize() {
        return iexAskSize;
    }

    public void setIexAskSize(String iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getWeek52High() {
        return week52High;
    }

    public void setWeek52High(String week52High) {
        this.week52High = week52High;
    }

    public String getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(String week52Low) {
        this.week52Low = week52Low;
    }

    public String getYtdChange() {
        return ytdChange;
    }

    public void setYtdChange(String ytdChange) {
        this.ytdChange = ytdChange;
    }

    public String getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(String peRatio) {
        this.peRatio = peRatio;
    }

    public String getLastTradeTime() {
        return lastTradeTime;
    }

    public void setLastTradeTime(String lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    public String getIsUSMarketOpen() {
        return isUSMarketOpen;
    }

    public void setIsUSMarketOpen(String isUSMarketOpen) {
        this.isUSMarketOpen = isUSMarketOpen;
    }

}
