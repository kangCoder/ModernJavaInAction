package ch10.lambda;

import ch10.domain.Order;
import ch10.domain.Trade;
import ch10.domain.Trade.Type;
import java.util.function.Consumer;

public class LambdaOrderBuilder {

    private Order order = new Order();

    public static Order order(Consumer<LambdaOrderBuilder> consumer) {
        LambdaOrderBuilder builder = new LambdaOrderBuilder();
        consumer.accept(builder);
        return builder.order;
    }

    public void forCustomer(String customer) {
        order.setCustomer(customer);
    }

    public void buy(Consumer<TradeBuilderWithLambda> consumer) {
        trade(consumer, Type.BUY);
    }

    public void sell(Consumer<TradeBuilderWithLambda> consumer) {
        trade(consumer, Type.SELL);
    }

    private void trade(Consumer<TradeBuilderWithLambda> consumer, Trade.Type type) {
        TradeBuilderWithLambda builder = new TradeBuilderWithLambda();
        builder.trade.setType(type);
        consumer.accept(builder);
        order.addTrade(builder.trade);
    }

}
