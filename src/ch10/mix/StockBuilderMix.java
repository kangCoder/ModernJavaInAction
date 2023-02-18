package ch10.mix;

import ch10.domain.Stock;
import ch10.domain.Trade;

public class StockBuilderMix {

    private final TradeBuilderMix builder;
    private final Trade trade;
    private final Stock stock = new Stock();

    StockBuilderMix(TradeBuilderMix builder, Trade trade, String symbol) {
        this.builder = builder;
        this.trade = trade;
        stock.setSymbol(symbol);
    }

    public TradeBuilderMix on(String market) {
        stock.setMarket(market);
        trade.setStock(stock);
        return builder;
    }

}
