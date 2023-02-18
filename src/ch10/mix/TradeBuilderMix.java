package ch10.mix;

import ch10.domain.Trade;

public class TradeBuilderMix {

    public Trade trade = new Trade();

    public TradeBuilderMix quantity(int quantity) {
        trade.setQuantity(quantity);
        return this;
    }

    public TradeBuilderMix at(double price) {
        trade.setPrice(price);
        return this;
    }

    public StockBuilderMix stock(String symbol) {
        return new StockBuilderMix(this, trade, symbol);
    }

}
