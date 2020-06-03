package com.hn.service;

/**
 * Interface for getting and setting values from Cache
 * 
 * @author Rahul Midha
 *
 */
public interface CacheService {
	/**
	 * Set a value for a key in cache
	 */
	void set(String key, String value);

	/**
	 * Get a value from a key in cache
	 */
	Object get(String key);
}
