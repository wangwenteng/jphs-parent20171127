package com.jinpaihushi.jphs.goods.model;

import java.util.List;

import com.jinpaihushi.jphs.price.model.Price;
import com.jinpaihushi.jphs.price.model.PriceGrade;
import com.jinpaihushi.jphs.price.model.PricePart;

/**
 * 商品价格内容or价格表
 * @author Administrator
 *
 */
public class ListPrice {

	private List<Price> prices;
	
	private List<PricePart> pricePart;
	
	private List<PriceGrade> priceGrade;

	public List<PriceGrade> getPriceGrade() {
		return priceGrade;
	}

	public void setPriceGrade(List<PriceGrade> priceGrade) {
		this.priceGrade = priceGrade;
	}

	
	public List<PricePart> getPricePart() {
		return pricePart;
	}

	public void setPricePart(List<PricePart> pricePart) {
		this.pricePart = pricePart;
	}

	public List<Price> getPrice() {
        return prices;
    }

    public void setPrice(List<Price> prices) {
        this.prices = prices;
    }

    public ListPrice(List<Price> prices) {
        super();
        this.prices = prices;
    }

    public ListPrice() {
        super();
    }
	
}
