package com.blurdel.sdjpa.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class OrderLine extends BaseEntity {

    private Integer quantityOrdered;

    @ManyToOne
    private OrderHeader orderHeader;

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals(quantityOrdered, orderLine.quantityOrdered) && Objects.equals(orderHeader, orderLine.orderHeader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantityOrdered);
    }
}
