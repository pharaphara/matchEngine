package fr.eql.eqlXchange.matchEngine.services.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.eql.eqlXchange.matchEngine.constant.OrderStatus;
import fr.eql.eqlXchange.matchEngine.dao.OrderRepository;
import fr.eql.eqlXchange.matchEngine.entity.Ordre;
import fr.eql.eqlXchange.matchEngine.services.serviceInterface.MatchingService;
import fr.eql.eqlXchange.matchEngine.services.serviceInterface.OrderServices;

@Service
public class OrderServicesImpl implements OrderServices{
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	MatchingService matchingService;
	
	@Override
	public ResponseEntity<?> newOrder(Ordre newOrder) {
		
		newOrder.setCreationDate(LocalDateTime.now());
		newOrder.setStatus(OrderStatus.NEW);
		
		orderRepository.save(newOrder);
		matchingService.updateBook(newOrder);
		
	
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	

}
