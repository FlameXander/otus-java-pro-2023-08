package ru.otus.lesson13.example.state;

import ru.otus.lesson13.example.state.data.OrderItem;
import ru.otus.lesson13.example.state.data.OrderStateEnum;

import java.math.BigDecimal;
import java.util.ArrayList;

public class OrderOpened extends OrderState {
    public OrderOpened() {
        super(OrderStateEnum.OPENED, new ArrayList<>());
    }

    @Override
    public void next(OrderContext ctx) {
        ctx.setState(new OrderSubmitted(getItems()));
    }

    @Override
    public void addItem(OrderItem item) {
        getItems().add(item);
    }

    @Override
    public BigDecimal removeItems() {
       getItems().clear();
       return BigDecimal.ZERO;
    }
}
