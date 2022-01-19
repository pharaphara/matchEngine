package fr.eql.eqlXchange.matchEngine.dto;

import java.time.LocalDateTime;

import fr.eql.eqlXchange.matchEngine.constant.TradingPair;

public class PricesRequest {

	private TradingPair pair;
	private LocalDateTime start;
	private LocalDateTime end;
	
	public PricesRequest(TradingPair pair, LocalDateTime start, LocalDateTime end) {
		super();
		this.pair = pair;
		this.start = start;
		this.end = end;
	}

	public TradingPair getPair() {
		return pair;
	}

	public void setPair(TradingPair pair) {
		this.pair = pair;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
		
	
}
