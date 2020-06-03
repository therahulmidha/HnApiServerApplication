package com.hn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * TopComment class
 * @author Rahul Midha
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TopComment {
	private String text;
	private String by;
	private int age;
	@JsonIgnore
	private int totalComments;
}
