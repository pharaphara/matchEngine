package fr.eql.eqlXchange.matchEngine.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import fr.eql.eqlXchange.matchEngine.constant.OrderStatus;
import fr.eql.eqlXchange.matchEngine.constant.OrderType;
import fr.eql.eqlXchange.matchEngine.constant.TradingPair;



@Entity
public class Ordre {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	

	/**
     * user email
	 */
	private String user;
	
	/**
	 * BASE/COUNTER, ex : BTC/USD, ETH/EUR
	 */
	 @Enumerated(EnumType.STRING)
	private TradingPair currencyPair;
	
	 /**
      * BID=Buying order (the trader is providing the counter currency)
	  * ASK=Selling order (the trader is providing the base currency)
	  */
	
    @Enumerated(EnumType.STRING)
	private OrderType orderType ; 
	
	/**
     * amount in base currency
	 */
	private double amount;
	
	/**
     * amount in base currency
	 */
	private double filledamount;
	
	/**
     * desired price in counter currency for 1 unit of base currency
     */
	private double limitPrice;
	
	/**
	   * Weighted Average price of the fills in the order
	   */
	 @Column(columnDefinition = "double default 0")
	  private double averagePrice;
	
	/**
     * order status
	 */
	 @Enumerated(EnumType.STRING)
	private OrderStatus status;

	/**
     * timestamp of creation date
	 */

	private LocalDateTime creationDate;
	
	private LocalDateTime filledDate;
	
	

	public Ordre() {
		super();
	}



	public Ordre(int id, String user, TradingPair currencyPair, OrderType orderType, double amount, double filledamount,
			double limitPrice, double averagePrice, OrderStatus status, LocalDateTime creationDate) {
		super();
		this.id = id;
		this.user = user;
		this.currencyPair = currencyPair;
		this.orderType = orderType;
		this.amount = amount;
		this.filledamount = filledamount;
		this.limitPrice = limitPrice;
		this.averagePrice = averagePrice;
		this.status = status;
		this.creationDate = creationDate;
	}
	
	



	public Ordre(String user, TradingPair currencyPair, OrderType orderType, double amount, double filledamount,
			double limitPrice, double averagePrice, OrderStatus status, LocalDateTime creationDate,
			LocalDateTime filledDate) {
		super();
		this.user = user;
		this.currencyPair = currencyPair;
		this.orderType = orderType;
		this.amount = amount;
		this.filledamount = filledamount;
		this.limitPrice = limitPrice;
		this.averagePrice = averagePrice;
		this.status = status;
		this.creationDate = creationDate;
		this.filledDate = filledDate;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public double getFilledamount() {
		return filledamount;
	}



	public void setFilledamount(double filledamount) {
		this.filledamount = filledamount;
	}



	public double getLimitPrice() {
		return limitPrice;
	}



	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}



	public double getAveragePrice() {
		return averagePrice;
	}



	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}



	public OrderStatus getStatus() {
		return status;
	}



	public void setStatus(OrderStatus status) {
		this.status = status;
	}



	public LocalDateTime getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	public double leftamount() {
		return amount-filledamount;
	}



	public LocalDateTime getFilledDate() {
		return filledDate;
	}



	public void setFilledDate(LocalDateTime filledDate) {
		this.filledDate = filledDate;
	}



	@Override
	public String toString() {
		return "Ordre [id=" + id + ", user=" + user + ", currencyPair=" + currencyPair + ", orderType=" + orderType
				+ ", amount=" + amount + ", filledamount=" + filledamount + ", limitPrice=" + limitPrice
				+ ", averagePrice=" + averagePrice + ", status=" + status + ", creationDate=" + creationDate
				+ ", filledDate=" + filledDate + "]";
	}



	



	
}
	