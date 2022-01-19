package fr.eql.eqlXchange.matchEngine.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eql.eqlXchange.matchEngine.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {
	
	Payment findFirstByOrderByTimestampAsc();

}
