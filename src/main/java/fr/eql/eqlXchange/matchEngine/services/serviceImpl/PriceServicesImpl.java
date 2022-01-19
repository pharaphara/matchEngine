package fr.eql.eqlXchange.matchEngine.services.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.eqlXchange.matchEngine.constant.Interval;
import fr.eql.eqlXchange.matchEngine.constant.OrderStatus;
import fr.eql.eqlXchange.matchEngine.constant.TradingPair;
import fr.eql.eqlXchange.matchEngine.dao.OrderRepository;
import fr.eql.eqlXchange.matchEngine.dto.PriceDto;
import fr.eql.eqlXchange.matchEngine.dto.PricesRequest;
import fr.eql.eqlXchange.matchEngine.entity.Ordre;
import fr.eql.eqlXchange.matchEngine.services.serviceInterface.PriceServices;

@Service
public class PriceServicesImpl  implements PriceServices{
	
	@Autowired
	OrderRepository orderRepository;
	
	private Map<TradingPair, PriceDto> lastPrices;
	
	//when api start look for last prices in database
	@PostConstruct
	void onInit() {
		lastPrices= new HashMap<TradingPair, PriceDto>();
		Arrays.stream(TradingPair.values()).forEach(pair->{
			PriceDto lastPrice = new PriceDto();
			Ordre ordre = orderRepository.findFirstByCurrencyPairAndStatusOrderByIdAsc(pair, OrderStatus.FILLED);
			if (ordre!=null) {
				BeanUtils.copyProperties(ordre, lastPrice);
				
				lastPrices.put(pair, lastPrice);
			}
			

		});
	}

	@Override
	public PriceDto getLastPrice(TradingPair pair) {
		return lastPrices.get(pair);
	}

	@Override
	public void setLastPrice(PriceDto price) {
		lastPrices.put(price.getCurrencyPair(), price);
		System.out.println(lastPrices);
		
	}

	@Override
	public List<PriceDto> getPricesBetween(TradingPair pair, LocalDateTime start, LocalDateTime end) {
		List<Ordre> ordres = orderRepository.findByCurrencyPairAndFilledDateBetweenOrderByFilledDateAsc(pair, start, end);
		List<PriceDto> prices = new ArrayList<>();
		ordres.forEach(o->prices.add(new PriceDto(o)));

		return prices;
	}

	@Override
	public List<PriceDto> getPricesBetween(PricesRequest request) {
		return getPricesBetween(request.getPair(), request.getStart(), request.getEnd());
	}

	@Override
	public List<Double[]> getLastCandles(TradingPair pair, Interval interval, int nb) {
		@SuppressWarnings("unused")
		List<PriceDto> prices=getPricesBetween(pair, LocalDateTime.now().minusHours(1), LocalDateTime.now());
		
		return null;
	}

	@Override
	public Map<TradingPair, PriceDto> getLastPrices() {
		
		return lastPrices;
	}

	
	

}
