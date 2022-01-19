package fr.eql.eqlXchange.matchEngine.services.serviceInterface;

import org.springframework.http.ResponseEntity;

import fr.eql.eqlXchange.matchEngine.entity.Ordre;

public interface OrderServices {
	ResponseEntity<?> newOrder(Ordre newOrder);

}
