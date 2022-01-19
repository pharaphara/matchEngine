package fr.eql.eqlXchange.matchEngine.services.serviceInterface;

import java.time.LocalDateTime;
import java.util.List;

import fr.eql.eqlXchange.matchEngine.constant.Interval;
import fr.eql.eqlXchange.matchEngine.constant.TradingPair;
import fr.eql.eqlXchange.matchEngine.dto.PriceDto;
import fr.eql.eqlXchange.matchEngine.dto.PricesRequest;

public interface PriceServices {
	
	void setLastPrice(PriceDto price);
	PriceDto getLastPrice(TradingPair pair);
	List<PriceDto> getPricesBetween(TradingPair pair,LocalDateTime start, LocalDateTime end);
	List<PriceDto> getPricesBetween(PricesRequest request);
	List<Double[]>getLastCandles(TradingPair pair,Interval interval,int nb);
	

}
