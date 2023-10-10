package ru.otus.lesson13.example.state;

import ru.otus.lesson13.example.state.data.OrderItem;
import ru.otus.lesson13.example.state.data.OrderStateEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private OrderStateEnum state = OrderStateEnum.OPENED;

    private final List<OrderItem> itemList = new ArrayList<>();

    private BigDecimal sum;

    public void submit() {
        if (state == OrderStateEnum.OPENED) {
            if (!itemList.isEmpty()) {
                sum = itemList.stream()
                        .map(OrderItem::getCost)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                state = OrderStateEnum.SUBMITTED;

            }
        } else {
            throw new IllegalStateException("submitter order in incorrect state");
        }
    }

    public void addItem(OrderItem item) {
        if (state == OrderStateEnum.OPENED) {
            itemList.add(item);
        } else {
            throw new IllegalStateException("submitter order in incorrect state");
        }
    }

    public BigDecimal removeItems() {
        switch (state) {
            case OPENED : {
                itemList.clear();
                return BigDecimal.ZERO;
            }
            case PAID : {
                throw new IllegalStateException("Can't remove items from paid order");
            }
            case SUBMITTED : {
                itemList.clear();
                return sum.multiply(new BigDecimal("0.1"));
            }
        }
        throw new IllegalStateException("unknown order status");
    }

    public void pay() {
        if (state == OrderStateEnum.SUBMITTED) {
            state = OrderStateEnum.PAID;
        } else {
            throw new IllegalStateException("submitter order in incorrect state");
        }
    }

    public OrderStateEnum getState() {
        return state;
    }
}
