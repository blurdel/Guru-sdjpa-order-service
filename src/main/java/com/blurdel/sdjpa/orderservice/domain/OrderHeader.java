package com.blurdel.sdjpa.orderservice.domain;

import java.util.Objects;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class OrderHeader extends BaseEntity {
	
	private String customer;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		OrderHeader that = (OrderHeader) o;
		return Objects.equals(customer, that.customer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), customer);
	}

}
