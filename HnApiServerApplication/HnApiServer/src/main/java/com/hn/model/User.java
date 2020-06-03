package com.hn.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * User class
 * @author Rahul Midha
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class User {
	private String about;
	private int created;
	private int delay;
	private String id;
	private int karma;
	private int[] submitted;
}