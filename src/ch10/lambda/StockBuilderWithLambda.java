package ch10.lambda;

import ch10.domain.Stock;

public class StockBuilderWithLambda {

    public Stock stock = new Stock();

    public void symbol(String symbol) {
        stock.setSymbol(symbol);
    }

    public void market(String market) {
        stock.setMarket(market);
    }

}
