package ch10.lambda;

import ch10.domain.Trade;
import java.util.function.Consumer;

public class TradeBuilderWithLambda {

    public Trade trade = new Trade();

    public void quantity(int quantity) {
        trade.setQuantity(quantity);
    }

    public void price(double price) {
        trade.setPrice(price);
    }

    public void stock(Consumer<StockBuilderWithLambda> consumer) {
        StockBuilderWithLambda builder = new StockBuilderWithLambda();
        consumer.accept(builder);
        trade.setStock(builder.stock);
    }

}
