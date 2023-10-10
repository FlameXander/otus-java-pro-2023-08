package ru.otus.lesson13.example.state;

import ru.otus.lesson13.example.state.data.OrderItem;
import ru.otus.lesson13.example.state.data.OrderStateEnum;

import java.math.BigDecimal;
import java.util.List;

public class OrderContext {
    private OrderState state;

    public OrderContext() {
        this.state = new OrderOpened();
    }

    public void setState(final OrderState state) {
        this.state = state;
    }

    public void submit() {
        state.next(this);
    }

    public void addItem(OrderItem item) {
        state.addItem(item);
    }

    public BigDecimal removeItems() {
        return state.removeItems();
    }

    public void pay() {
        state.next(this);
    }

    public OrderStateEnum getState() {
        return state.getState();
    }

    public List<OrderItem> getItemList() {
        return state.getItemList();
    }

    public BigDecimal getSum() {
        return state.getSum();
    }
}
