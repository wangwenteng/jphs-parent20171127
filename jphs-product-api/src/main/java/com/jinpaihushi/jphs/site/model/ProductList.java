package com.jinpaihushi.jphs.site.model;

import java.util.List;

import com.jinpaihushi.jphs.product.model.Product;

public class ProductList {
	
	private List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public ProductList(List<Product> productList) {
		super();
        this.productList = productList;
	}
	
	public ProductList() {
		super();
	}
	
	@Override
	public String toString() {
		return "ProductList [productList=" + productList + ", getProductList()=" + getProductList() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
