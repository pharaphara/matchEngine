package fr.eql.eqlXchange.matchEngine.dto;

import fr.eql.eqlXchange.matchEngine.constant.OrderType;
import fr.eql.eqlXchange.matchEngine.constant.TradingPair;

public class OrderDTO {
	
	/**
     * user email
	 */
	private String user;
	
	/**
	 * BASE/COUNTER, ex : BTC/USD, ETH/EUR
	 */
	private TradingPair currencyPair;
	
	 /**
      * BID=Buying order (the trader is providing the counter currency)
	  * ASK=Selling order (the trader is providing the base currency)
	  */
	private OrderType orderType; 
	
	/**
     * amount in base currency
	 */
	private float amount;
	
	/**
     * desired price in counter currency for 1 unit of base currency
	 */
	private float limitPrice;

	public OrderDTO() {
		super();
	}

	public OrderDTO(String user, TradingPair currencyPair, OrderType orderType, float amount, float limitPrice) {
		super();
		this.user = user;
		this.currencyPair = currencyPair;
		this.orderType = orderType;
		this.amount = amount;
		this.limitPrice = limitPrice;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public TradingPair getCurrencyPair() {
		return currencyPair;
	}

	public void setCurrencyPair(TradingPair currencyPair) {
		this.currencyPair = currencyPair;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(float limitPrice) {
		this.limitPrice = limitPrice;
	}

	@Override
	public String toString() {
		return "OrderDTO [user=" + user + ", currencyPair=" + currencyPair + ", orderType=" + orderType + ", amount="
				+ amount + ", limitPrice=" + limitPrice + "]";
	}
	
	
}

	