package fr.eql.eqlXchange.matchEngine.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Payment {
	

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;
	
	private String currencyTicker;
	private String userEmail;
	private double montant;
	private LocalDateTime timestamp;
	private boolean isSent;
	
	@ManyToOne ()
	@JoinColumn(name = "ordre_id")
	private Ordre ordre;
	

	public Payment() {
		super();
	}

	

	public Payment(String currencyTicker, String userEmail, double montant, LocalDateTime timestamp, Ordre ordre) {
		super();
		this.currencyTicker = currencyTicker;
		this.userEmail = userEmail;
		this.montant = montant;
		this.timestamp = timestamp;
		this.ordre = ordre;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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



	public LocalDateTime getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}



	public Ordre getOrdre() {
		return ordre;
	}



	public void setOrdre(Ordre ordre) {
		this.ordre = ordre;
	}



	public boolean isSent() {
		return isSent;
	}



	public void setSent(boolean isSent) {
		this.isSent = isSent;
	}



	@Override
	public String toString() {
		return "Payment [id=" + id + ", currencyTicker=" + currencyTicker + ", userEmail=" + userEmail + ", montant="
				+ montant + ", timestamp=" + timestamp + ", ordre=" + ordre + "]";
	}


	
}