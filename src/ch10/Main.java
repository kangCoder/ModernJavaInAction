package ch10;

import static ch10.lambda.LambdaOrderBuilder.*;
import static ch10.methodchain.MethodChainingOrderBuilder.*;
import static ch10.nestedfunction.NestedFunctionOrderBuilder.*;
import static ch10.mix.MixBuilder.*;

import ch10.domain.Order;
import ch10.mix.MixBuilder;
import ch10.tax.Tax;
import ch10.tax.TaxCalculator;

public class Main {


    public static void main(String[] args) {
        //1. 메서드 체인 방법.
        Order order1 = forCustomer("BigBank")
                .buy(80).stock("IBM").on("NYSE").at(125.00)
                .sell(50).stock("GOOGLE").on("NASDAQ").at(375.00)
                .end();

        //2. 중첩된 함수 패턴.
        Order order2 = order(
                "BigBank",
                buy(80, stock("IBM", on("NYSE")), at(125.00)),
                sell(50, stock("GOOGLE", on("NASNAQ")), at(375.00)));

        //3. 람다 표현식을 이용한 함수 시퀀싱.
        Order lambdaOrder = order(o -> {
            o.forCustomer("BigBank");
            o.buy(t -> {
                t.quantity(80);
                t.price(125.00);
                t.stock(s -> {
                    s.symbol("IBM");
                    s.market("NYSE");
                });
            });
            o.sell(t -> {
                t.quantity(50);
                t.price(275.00);
                t.stock(s -> {
                    s.symbol("GOOGLE");
                    s.market("NASDAQ");
                });
            });
        });

        //4. DSL 패턴들을 조합해서 사용해보기
        Order order = forCustomer("BinBank",
                buy(t -> t.quantity(80).stock("IBM").on("NYSE").at(125.00)),
                sell(t -> t.quantity(50).stock("GOOGLE").on("NASDAQ").at(125.00))
        );

        //5. order에 세금을 적용해보기
        double tax = new TaxCalculator()
                .with(Tax::regional)
                .with(Tax::surcharge)
                .calculate(order);

        System.out.println(tax);
    }

}
