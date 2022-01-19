package fr.eql.eqlXchange.matchEngine.endpointServices.serviceInterface;

import fr.eql.eqlXchange.matchEngine.entity.Ordre;

public interface WalletServices {

	void sendPayment(Ordre ordre);

}
