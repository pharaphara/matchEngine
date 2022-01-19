package fr.eql.eqlXchange.matchEngine.dto;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import fr.eql.eqlXchange.matchEngine.constant.TradingPair;
import fr.eql.eqlXchange.matchEngine.entity.Ordre;


public class PriceDto {
	
	private double price;
	
	@Enumerated(EnumType.STRING)
	private TradingPair currencyPair;
	private LocalDateTime filledDate;
	
	
	public PriceDto() {
		super();
	}


	public PriceDto(double price, TradingPair currencyPair, LocalDateTime filledDate) {
		super();
		this.price = price;
		this.currencyPair = currencyPair;
		this.filledDate = filledDate;
	}


	public PriceDto(Ordre ordre) {
		price = ordre.getAveragePrice();
		currencyPair=ordre.getCurrencyPair();
		filledDate= ordre.getFilledDate();
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public TradingPair getCurrencyPair() {
		return currencyPair;
	}


	public void setCurrencyPair(TradingPair currencyPair) {
		this.currencyPair = currencyPair;
	}


	public LocalDateTime getFilledDate() {
		return filledDate;
	}


	public void setFilledDate(LocalDateTime filledDate) {
		this.filledDate = filledDate;
	}


	public int compareTo(ChronoLocalDateTime<?> other) {
		return filledDate.compareTo(other);
	}


	@Override
	public String toString() {
		return "PriceDto [price=" + price + ", currencyPair=" + currencyPair + ", filledDate=" + filledDate + "]";
	}
	
	
	
	

}