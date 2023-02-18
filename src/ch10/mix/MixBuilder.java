package ch10.mix;

import ch10.domain.Order;
import ch10.domain.Trade.Type;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MixBuilder {

    public static Order forCustomer(String customer, TradeBuilderMix... builders) {
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(builders).forEach(t -> order.addTrade(t.trade));
        return order;
    }

    public static TradeBuilderMix buy(Consumer<TradeBuilderMix> consumer) {
        return buildTrade(consumer, Type.BUY);
    }

    public static TradeBuilderMix sell(Consumer<TradeBuilderMix> consumer) {
        return buildTrade(consumer, Type.SELL);
    }

    private static TradeBuilderMix buildTrade(Consumer<TradeBuilderMix> consumer, Type type) {
        TradeBuilderMix builder = new TradeBuilderMix();
        consumer.accept(builder);
        builder.trade.setType(type);
        return builder;
    }

}
