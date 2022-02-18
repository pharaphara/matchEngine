package fr.eql.eqlXchange.matchEngine.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.eql.eqlXchange.matchEngine.constant.OrderStatus;
import fr.eql.eqlXchange.matchEngine.constant.TradingPair;
import fr.eql.eqlXchange.matchEngine.dao.OrderRepository;
import fr.eql.eqlXchange.matchEngine.entity.Ordre;
import fr.eql.eqlXchange.matchEngine.services.serviceInterface.OrderServices;

@RestController
@RequestMapping("/bookOrder")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	OrderServices orderServices;



	@Autowired
	OrderRepository orderRepository;


	@GetMapping("/hello")
	public String hello(){
		return "Hello there !";

	}

	@PostMapping("/order")
	public ResponseEntity<?> newOrder(@RequestBody Ordre newOrder){
		return orderServices.newOrder(newOrder);
	}

	
	@GetMapping("/myOrders")
	public ResponseEntity<List<Ordre>> getOrdersByUser(@RequestParam String user){
		return new ResponseEntity<List<Ordre>>(orderRepository.findByUser(user), HttpStatus.OK) ;
	}
	
	@GetMapping("/Orders")
	public ResponseEntity<List<Ordre>> getOrders(@RequestParam TradingPair pair){
		return new ResponseEntity<List<Ordre>>(orderRepository.findByCurrencyPair(pair), HttpStatus.OK) ;
	}
	
	@GetMapping("/lastOrder")
	public ResponseEntity<Ordre> getLastOrder(@RequestParam String user){
		return new ResponseEntity<Ordre>(orderRepository.findFirstByUserOrderByIdDesc(user), HttpStatus.OK) ;
	}

	@GetMapping("/status")
	public ResponseEntity<List<Ordre>> getByStatus(@RequestParam OrderStatus status){
		return new ResponseEntity<List<Ordre>>(orderRepository.findByStatus(status), HttpStatus.OK) ;
	}


}
