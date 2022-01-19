package fr.eql.eqlXchange.matchEngine.constant;

public enum OrderStatus {


	/**
	 * Initial order when instantiated
	 */
	PENDING_NEW,
	/**
	 * Initial order when placed on the order book at exchange
	 */
	NEW,
	/**
	 * Partially match against opposite order on order book at exchange
	 */
	PARTIALLY_FILLED,
	/**
	 * Fully match against opposite order on order book at exchange
	 */
	FILLED,
	/**
	 * Waiting to be removed from order book at exchange
	 */
	PENDING_CANCEL,
	/**
	 * Removed from order book at exchange
	 */
	CANCELED,
	/**
	 * Waiting to be replaced by another order on order book at exchange
	 */
	PENDING_REPLACE,
	/**
	 * Order has been replace by another order on order book at exchange
	 */
	REPLACED,
	/**
	 * Order has been triggered at stop price
	 */
	STOPPED,
	/**
	 * Order has been rejected by exchange and not place on order book
	 */
	REJECTED,
	/**
	 * Order has expired it's time to live or trading session and been removed from order book
	 */
	EXPIRED


}