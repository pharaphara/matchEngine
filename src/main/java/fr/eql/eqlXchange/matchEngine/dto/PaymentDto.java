package fr.eql.eqlXchange.matchEngine.dto;

public class PaymentDto {
	
	private String currencyTicker;
	private String userEmail;
	private double montant;
	
	
	public PaymentDto(String currencyTicker, String userEmail, double montant) {
		super();
		this.currencyTicker = currencyTicker;
		this.userEmail = userEmail;
		this.montant = montant;
	}
	
	public PaymentDto() {
		// TODO Auto-generated constructor stub
	}

	public String getCurrencyTicker() {
		return currencyTicker;
	}
	public void setCurrencyTicker(String currencyTicker) {
		this.currencyTicker = currencyTicker;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}

	
}
