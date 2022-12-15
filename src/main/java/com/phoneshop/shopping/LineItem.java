package com.phoneshop.shopping;

import com.phoneshop.entities.ProductEntity;
import com.phoneshop.phonecase.PhonecaseDTO;
import com.phoneshop.phones.PhoneDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineItem {
	/*
	 * private PhoneDTO phoneDTO; private PhonecaseDTO phonecaseDTO;
	 */
	private ProductEntity product;
	private int quantity;
	private long subPrice = 0;

	/*
	 * public LineItem(PhoneDTO phoneDTO, int quantity) { super(); this.phoneDTO =
	 * phoneDTO; this.quantity = quantity; calculateSubPrice(); }
	 */
	/*
	 * public LineItem(PhonecaseDTO phonecaseDTO, int quantity) { super();
	 * this.phonecaseDTO = phonecaseDTO; this.pcquantity = quantity;
	 * calculateSubPrice(); }
	 */
	public LineItem(ProductEntity product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
		calculateSubPrice();
	}

	/*
	 * public void calculateSubPrice() { if (phoneDTO != null) { subPrice =
	 * phoneDTO.getPrice() * quantity; } else { subPrice = 0; } }
	 */

	public void calculateSubPrice() {
		if (product != null) {
			subPrice = product.getCost() * quantity;
		} else {
			subPrice = 0;
		}
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		calculateSubPrice();
	}

}
