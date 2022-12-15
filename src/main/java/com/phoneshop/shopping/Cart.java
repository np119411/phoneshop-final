package com.phoneshop.shopping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.phoneshop.entities.ProductEntity;
import com.phoneshop.enums.ProductType;

public class Cart {
	Set<LineItem> lineItems = new HashSet<>();
	Set<LinePhonecase> linePhonecase = new HashSet<>();
	long totalPrice = 0;
	private Map<String, CartProduct> cart;

	public Cart() {
	}

	public Cart(Map<String, CartProduct> cart) {
		this.cart = cart;
	}

	public Map<String, CartProduct> getCart2() {
		return cart;
	}

	public void setCart2(Map<String, CartProduct> cart) {
		this.cart = cart;
	}

	public Cart(Set<LineItem> lineItems) {
		super();
		this.lineItems = lineItems;
		calculateTotalPrice();
	}



	public void addItem(LineItem newLineItem) {
		boolean exist = false;
		for (LineItem line1 : lineItems) {
			if (newLineItem.getProduct() != null) {
				if (line1.getProduct() != null) {
					if (line1.getProduct().getId() == (newLineItem.getProduct().getId())) {
						line1.setQuantity(line1.getQuantity() + newLineItem.getQuantity());
						exist = true;
						break;
					}
				}
			}
		}
		if (!exist) {
			lineItems.add(newLineItem);
		}

		calculateTotalPrice();
	}

	/*
	 * public void addPhonecase(LineItem newLineItem) { boolean exist = false; for
	 * (LineItem line1 : lineItems) { if (newLineItem.getPhoneDTO() != null) { if
	 * (line1.getPhoneDTO() != null) { if
	 * (line1.getPhoneDTO().getID().equals(newLineItem.getPhoneDTO().getID())) {
	 * line1.setQuantity(line1.getQuantity() + newLineItem.getQuantity()); exist =
	 * true; break; } }
	 * 
	 * } else { if (line1.getPhonecaseDTO() != null) { if
	 * (line1.getPhonecaseDTO().getPcID().equals(newLineItem.getPhonecaseDTO().
	 * getPcID())) { line1.setQuantity(1); exist = true; existGlobal = true; break;
	 * } }
	 * 
	 * }
	 * 
	 * } if (!exist) { lineItems.add(newLineItem); }
	 * 
	 * calculateTotalPrice(); }
	 */

	public void removeItem(long phoneId) {
		List<LineItem> listPhoncase = new ArrayList<>();
		for (LineItem lineItem : lineItems) {
			if (lineItem.getProduct() != null) {
				if (lineItem.getProduct().getId() == phoneId) {
					if (lineItem.getProduct().getType().equals(ProductType.PHONE)) {
						for (LineItem lineItem2 : lineItems) {
							ProductEntity phone = lineItem2.getProduct().getPhone();
							if (phone != null && phone.getId() == lineItem.getProduct().getId()) {
								//removeItem(lineItem2.getProduct().getId());
								listPhoncase.add(lineItem2);
							}
						}
					}

					lineItems.remove(lineItem);
					break;
				}
			}

		}
		System.out.println(listPhoncase.size());
		lineItems.removeAll(listPhoncase);
		calculateTotalPrice();
	}

	public void getAllCart() {
		
	}

	/*
	 * public void removePhonecase(LineItem newLineItem) { for (LineItem line1 :
	 * lineItems) { if (newLineItem.getPhoneDTO() != null) { if (line1.getPhoneDTO()
	 * != null) { if
	 * (line1.getPhoneDTO().getID().equals(newLineItem.getPhoneDTO().getID())) {
	 * lineItems.remove(line1); break; } }
	 * 
	 * } else { if (line1.getPhonecaseDTO() != null) { if
	 * (line1.getPhonecaseDTO().getPcID().equals(newLineItem.getPhonecaseDTO().
	 * getPcID())) { lineItems.remove(line1); break; } }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * calculateTotalPrice();
	 * 
	 * }
	 */

	public void calculateTotalPrice() {
		totalPrice = 0;
		for (LineItem lineItem : lineItems) {
			if (lineItem.getProduct() != null) {
				totalPrice = totalPrice + lineItem.getProduct().getCost() * lineItem.getQuantity();
			}
		}
	}

	public void removeCart(String productID) {
		if (this.cart == null) {
			return;
		}
		if (this.cart.containsKey(productID)) {
			this.cart.remove(productID);
		}
	}

	public void updateCart(String productID, CartProduct product) {
		if (this.cart == null) {
			return;
		}
		if (this.cart.containsKey(productID)) {
			this.cart.replace(productID, product);
		}
	}

	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

}
