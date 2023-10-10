package ru.otus.lesson13.example.state;

import ru.otus.lesson13.example.state.data.OrderItem;

import java.math.BigDecimal;

public class OrderExample {
    public static void main(String[] args) {
        OrderContext ctx = new OrderContext();

        OrderItem item = new OrderItem(new BigDecimal("30.0"), "Screwdriver");
        OrderItem item1 = new OrderItem(new BigDecimal("20.0"), "Pliers");

        ctx.addItem(item);
        ctx.addItem(item1);

        System.out.println(ctx.getItemList());
        System.out.println(ctx.getSum());
        System.out.println(ctx.getState());
        System.out.println("-------------------------------");

        ctx.submit();

        System.out.println(ctx.getItemList());
        System.out.println(ctx.getSum());
        System.out.println(ctx.getState());
        System.out.println("-------------------------------");

/*        System.out.println(ctx.getItemList());
        System.out.println(ctx.getSum());
        System.out.println(ctx.getState());
        System.out.println("-------------------------------");

        ctx.pay();*/

        ctx.pay();

        System.out.println(ctx.getItemList());
        System.out.println(ctx.getSum());
        System.out.println(ctx.getState());
        System.out.println("-------------------------------");


    }
}
