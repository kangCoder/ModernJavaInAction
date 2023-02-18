package ch10.methodchain;

import ch10.domain.Trade;
import ch10.methodchain.MethodChainingOrderBuilder;

public class TradeBuilderWithStock {

    private final MethodChainingOrderBuilder builder;
    private final Trade trade;

    public TradeBuilderWithStock(MethodChainingOrderBuilder builder, Trade trade) {
        this.builder = builder;
        this.trade = trade;
    }

    public MethodChainingOrderBuilder at(double price) {
        trade.setPrice(price);
        return builder.addTrade(trade);
    }
}
