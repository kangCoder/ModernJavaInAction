package ch10.nestedfunction;

import ch10.domain.Order;
import ch10.domain.Stock;
import ch10.domain.Trade;
import ch10.domain.Trade.Type;
import java.util.stream.Stream;

public class NestedFunctionOrderBuilder {

    public static Order order(String customer, Trade... trades) {
        Order order = new Order();
        order.setCustomer(customer);
        Stream.of(trades).forEach(order::addTrade);
        return order;
    }

    public static Trade buy(int quantity, Stock stock, double price) {
        return buildTrade(quantity, stock, price, Type.BUY);
    }

    public static Trade sell(int quantity, Stock stock, double price) {
        return buildTrade(quantity, stock, price, Type.SELL);
    }

    public static double at(double price) {
        return price;
    }

    public static Stock stock(String symbol, String market) {
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setMarket(market);
        return stock;
    }

    public static String on(String market) {
        return market;
    }

    private static Trade buildTrade(int quantity, Stock stock, double price, Trade.Type buy) {
        Trade trade = new Trade();
        trade.setQuantity(quantity);
        trade.setStock(stock);
        trade.setPrice(price);
        trade.setType(buy);

        return trade;
    }
}
