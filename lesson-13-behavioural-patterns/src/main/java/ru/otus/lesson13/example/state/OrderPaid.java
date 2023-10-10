package ru.otus.lesson13.example.state;

import ru.otus.lesson13.example.state.data.OrderItem;
import ru.otus.lesson13.example.state.data.OrderStateEnum;

import java.math.BigDecimal;
import java.util.List;

public class OrderPaid extends OrderState {

    public OrderPaid(final List<OrderItem> itemList) {
        super(OrderStateEnum.PAID, itemList);
        sum = itemList.stream()
                .map(OrderItem::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void next(OrderContext ctx) {
        throw new IllegalStateException("can't move from paid state");
    }

    @Override
    public void addItem(OrderItem item) {
        throw new IllegalStateException("can't add from paid state");
    }

    @Override
    public BigDecimal removeItems() {
        throw new IllegalStateException("can't remove from paid state");
    }
}
