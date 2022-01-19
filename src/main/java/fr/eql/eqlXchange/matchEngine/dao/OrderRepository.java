package fr.eql.eqlXchange.matchEngine.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.eql.eqlXchange.matchEngine.constant.OrderStatus;
import fr.eql.eqlXchange.matchEngine.constant.OrderType;
import fr.eql.eqlXchange.matchEngine.constant.TradingPair;
import fr.eql.eqlXchange.matchEngine.entity.Ordre;

@Repository
public interface OrderRepository extends JpaRepository<Ordre, Integer>{
	
	List<Ordre> findByStatus(OrderStatus status);
	List<Ordre> findByStatusNotLike(OrderStatus status);
 	List<Ordre> findByStatusIn(List<OrderStatus> list);
 	List<Ordre> findByOrderTypeAndStatusIn(OrderType type, List<OrderStatus> list);
 	List<Ordre> findByOrderTypeAndStatusInOrderByLimitPriceAscAmountDesc(OrderType type, List<OrderStatus> list);
 	List<Ordre> findByOrderTypeAndStatusInOrderByLimitPriceAscAmountAsc(OrderType type, List<OrderStatus> list);
	List<Ordre> findByUser(String user);
	Ordre findFirstByCurrencyPairAndStatusOrderByIdAsc(TradingPair pair,OrderStatus status);
	Ordre findFirstByUserOrderByIdAsc(String user);
	List<Ordre> findByCurrencyPairAndFilledDateBetweenOrderByFilledDateAsc(TradingPair pair, LocalDateTime start, LocalDateTime end);
	List<Ordre> findByCurrencyPair(TradingPair pair);
}
