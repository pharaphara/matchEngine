package fr.eql.eqlXchange.matchEngine.services.serviceImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eql.eqlXchange.matchEngine.constant.OrderStatus;
import fr.eql.eqlXchange.matchEngine.constant.OrderType;
import fr.eql.eqlXchange.matchEngine.constant.TradingPair;
import fr.eql.eqlXchange.matchEngine.dao.OrderRepository;
import fr.eql.eqlXchange.matchEngine.endpointServices.serviceInterface.WalletServices;
import fr.eql.eqlXchange.matchEngine.entity.Ordre;
import fr.eql.eqlXchange.matchEngine.services.serviceInterface.MatchingService;

@Service
public class MatchinServiceImpl implements MatchingService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	WalletServices walletServices;

	private Map<TradingPair, LinkedList<Ordre>> bidBooks;
	private Map<TradingPair, LinkedList<Ordre>> askBooks;

	@PostConstruct
	public void init() {

		List<Ordre> allOpenBid = orderRepository.findByOrderTypeAndStatusInOrderByLimitPriceAscAmountAsc(OrderType.BID, Arrays.asList(OrderStatus.NEW,OrderStatus.PARTIALLY_FILLED));
		System.out.println("open bid size = "+allOpenBid.size());
		List<Ordre> allOpenAsk = orderRepository.findByOrderTypeAndStatusInOrderByLimitPriceAscAmountDesc(OrderType.ASK, Arrays.asList(OrderStatus.NEW,OrderStatus.PARTIALLY_FILLED));
		System.out.println("open ask size = "+allOpenAsk.size());

		//initilizing each book
		bidBooks = new HashMap<TradingPair, LinkedList<Ordre>>();
		askBooks = new HashMap<TradingPair, LinkedList<Ordre>>();

		//Create an entry for each trading pair in each book order
		Arrays.stream(TradingPair.values()).forEach(pair->{
			bidBooks.put(pair, new LinkedList<Ordre>());
			askBooks.put(pair, new LinkedList<Ordre>());

		});


		//add all orders in the right linkedlist
		allOpenBid.forEach(bidOrder->bidBooks.get(bidOrder.getCurrencyPair()).add(bidOrder));
		allOpenAsk.forEach(askOrder->askBooks.get(askOrder.getCurrencyPair()).add(askOrder));


		bookToConsole();

	}

	private void bookToConsole() {
		bidBooks.forEach((pair,book)->{
			if(book.size()>0) {
				System.out.println("Pair  :"+pair);
				book.forEach(ordre->System.out.println("BUY"+" "+ordre.getLimitPrice()+" -"+(ordre.getAmount()-ordre.getFilledamount())));
			}

		});
		System.out.println("------");
		askBooks.forEach((pair,book)->{
			if(book.size()>0) {

				book.forEach(ordre->System.out.println("SEL"+" "+ordre.getLimitPrice()+" -"+(ordre.getAmount()-ordre.getFilledamount())));
			}

		});
	}
	@Override
	public void updateBook(Ordre newOrder) {

		init();
		bookToConsole();
		matchOrders();

	}

	private void matchOrders() {
		bidBooks.forEach((pair,book)->{

			if(book.size()>0&&askBooks.get(pair).size()>0) {
				Ordre lowestBID =book.getLast();
				Ordre lowestASK = askBooks.get(pair).getFirst();
				Ordre highestLeftAmmount = (lowestBID.getAmount()-lowestBID.getFilledamount()>=lowestASK.getAmount()-lowestASK.getFilledamount()) ? lowestBID: lowestASK ;
				Ordre lowestLeftAmmount = (lowestBID.getAmount()-lowestBID.getFilledamount()>=lowestASK.getAmount()-lowestASK.getFilledamount()) ? lowestASK: lowestBID ;




				if(lowestBID.getLimitPrice()>=lowestASK.getLimitPrice()) {
					fullfillOrders(highestLeftAmmount,lowestLeftAmmount);
					matchOrders();//recursivelly match all possible orders
				}
			}
		});
	}

	private void fullfillOrders(Ordre HIGH, Ordre LOW) {
		//oldest order set the trade price
		double tradePrice= (HIGH.getCreationDate().isBefore(LOW.getCreationDate()))? HIGH.getLimitPrice():LOW.getLimitPrice() ;

		HIGH.setAveragePrice(HIGH.getAveragePrice()+tradePrice/HIGH.getAmount()*LOW.leftamount());
		LOW.setAveragePrice(LOW.getAveragePrice()+tradePrice/LOW.getAmount()*LOW.leftamount());
		HIGH.setFilledamount(HIGH.getFilledamount()+LOW.leftamount());
		LOW.setFilledamount(LOW.getAmount());
		LOW.setStatus(OrderStatus.FILLED);
		LOW.setFilledDate(LocalDateTime.now());
		walletServices.sendPayment(LOW);
		if (HIGH.getAmount()==HIGH.getFilledamount()) {
			HIGH.setStatus(OrderStatus.FILLED);
			HIGH.setFilledDate(LocalDateTime.now());
			walletServices.sendPayment(HIGH);
		}else {
			HIGH.setStatus(OrderStatus.PARTIALLY_FILLED);
		}


		orderRepository.saveAll(Arrays.asList(HIGH,LOW));

		init();



	}
	


}
