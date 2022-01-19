package fr.eql.eqlXchange.matchEngine.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eql.eqlXchange.matchEngine.entity.Currency;

public interface CurrencyRepo extends JpaRepository<Currency, String>{

}
