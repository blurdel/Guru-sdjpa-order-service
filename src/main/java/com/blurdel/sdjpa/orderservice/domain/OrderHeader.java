package com.blurdel.sdjpa.orderservice.domain;

import java.util.Objects;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
@AttributeOverrides({
		@AttributeOverride(
				name = "shippingAddress.address",
				column = @Column(name = "shipping_address")
		),
		@AttributeOverride(
				name = "shippingAddress.city",
				column = @Column(name = "shipping_city")
		),
		@AttributeOverride(
				name = "shippingAddress.state",
				column = @Column(name = "shipping_state")
		),
		@AttributeOverride(
				name = "shippingAddress.zipCode",
				column = @Column(name = "shipping_zip_code")
		),
		@AttributeOverride(
				name = "billToAddress.address",
				column = @Column(name = "bill_to_address")
		),
		@AttributeOverride(
				name = "billToAddress.city",
				column = @Column(name = "bill_to_city")
		),
		@AttributeOverride(
				name = "billToAddress.state",
				column = @Column(name = "bill_to_state")
		),
		@AttributeOverride(
				name = "billToAddress.zipCode",
				column = @Column(name = "bill_to_zip_code")
		)
})
public class OrderHeader extends BaseEntity {
	
	private String customer;

	@Embedded
	private Address shippingAddress;

	@Embedded
	private Address billToAddress;


	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillToAddress() {
		return billToAddress;
	}

	public void setBillToAddress(Address billToAddress) {
		this.billToAddress = billToAddress;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		OrderHeader that = (OrderHeader) o;
		return Objects.equals(customer, that.customer) && Objects.equals(shippingAddress, that.shippingAddress) && Objects.equals(billToAddress, that.billToAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), customer, shippingAddress, billToAddress);
	}

}
