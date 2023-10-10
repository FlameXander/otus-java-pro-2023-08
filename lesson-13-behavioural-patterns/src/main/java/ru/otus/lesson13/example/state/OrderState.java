package ru.otus.lesson13.example.state;

import ru.otus.lesson13.example.state.data.OrderItem;
import ru.otus.lesson13.example.state.data.OrderStateEnum;

import java.math.BigDecimal;
import java.util.List;

public abstract class OrderState {
    private OrderStateEnum state;

    private List<OrderItem> itemList;

    protected BigDecimal sum = BigDecimal.ZERO;

    protected OrderState(final OrderStateEnum state, final List<OrderItem> itemList) {
        this.state = state;
        this.itemList = itemList;
    }

    protected List<OrderItem> getItems() {
        return itemList;
    }

    public abstract void next(OrderContext ctx);

    public abstract void addItem(OrderItem item);

    public abstract BigDecimal removeItems();

    public OrderStateEnum getState() {
        return state;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(final List<OrderItem> itemList) {
        this.itemList = itemList;
    }
}
