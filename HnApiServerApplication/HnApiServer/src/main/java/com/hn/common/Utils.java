package com.hn.common;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hn.exceptions.ConnectionException;
import com.hn.exceptions.InternalServerException;
import com.hn.model.Comment;
import com.hn.model.Story;
import com.hn.model.User;

import lombok.extern.log4j.Log4j2;

/**
 * HnApiServer Utility methods class
 * 
 * @author Rahul Midha
 *
 */
@Log4j2
public class Utils {

	/**
	 * private constructor
	 */
	private Utils() {
		throw new IllegalStateException("Utils class: Instantiation not allowed");
	}

	/**
	 * ObjectMapper static instance
	 */
	private static ObjectMapper objectMapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public static List<Integer> getTopStoryIds() {
		List<Integer> topStories;
		URL url;
		try {
			url = new URL(AppConstants.HACKER_NEWS_API_BASE_URL + AppConstants.TOP_STORIES_END_POINT
					+ AppConstants.ENDPOINT_SUFFIX);
			topStories = objectMapper.readValue(url, new TypeReference<List<Integer>>() {
			});
		} catch (UnknownHostException e) {
			log.error(e.toString());
			throw new ConnectionException();
		} catch (Exception e) {
			log.error(e.toString());
			throw new InternalServerException();
		}
		return topStories;
	}

	public static Story getStory(int storyId) {
		Story story = null;
		try {
			story = objectMapper.readValue(new URL(AppConstants.HACKER_NEWS_API_BASE_URL + AppConstants.ITEM_END_POINT
					+ storyId + AppConstants.ENDPOINT_SUFFIX), Story.class);
		} catch (UnknownHostException e) {
			log.error(e.toString());
			throw new ConnectionException();
		} catch (Exception e) {
			log.error(e.toString());
			throw new InternalServerException();
		}
		return story;
	}

	public static Comment getComment(int commentId) {
		Comment comment = null;
		try {
			comment = objectMapper.readValue(new URL(AppConstants.HACKER_NEWS_API_BASE_URL + AppConstants.ITEM_END_POINT
					+ commentId + AppConstants.ENDPOINT_SUFFIX), Comment.class);

		} catch (UnknownHostException e) {
			log.error(e.toString());
			throw new ConnectionException();
		} catch (Exception e) {
			log.error(e.toString());
			throw new InternalServerException();
		}
		return comment;
	}

	public static int getCommentCount(Comment comment) {
		if (comment == null) {
			return 0;
		}
		int count = 0;

		if (comment.getKids() != null) {

			count += comment.getKids().length;

			for (int commentId : comment.getKids()) {
				try {
					Comment kidComment = objectMapper.readValue(new URL(AppConstants.HACKER_NEWS_API_BASE_URL
							+ AppConstants.ITEM_END_POINT + commentId + AppConstants.ENDPOINT_SUFFIX), Comment.class);
					count += getCommentCount(kidComment);
				} catch (UnknownHostException e) {
					log.error(e.toString());
					throw new ConnectionException();
				} catch (IOException e) {
					log.error(e.toString());
					throw new InternalServerException();
				}

			}

		}
		return count;
	}

	/**
	 * Method to calculate HackerNews age of a user i.e. how old their Hacker News
	 * profile is in years.
	 * 
	 * @param name
	 * @return
	 */
	public static int getUserAgeByName(String name) {
		User user = null;

		try {
			user = objectMapper.readValue(new URL(AppConstants.HACKER_NEWS_API_BASE_URL + AppConstants.USER_END_POINT
					+ name + AppConstants.ENDPOINT_SUFFIX), User.class);
		} catch (IOException e) {
			log.error("Error getting age for user: {}", name);
		}
		if (user != null) {
			return Period.between(
					LocalDate.ofInstant(Instant.ofEpochMilli(user.getCreated() * 1000L), ZoneId.systemDefault()),
					LocalDate.now()).getYears();
		}
		return 0;
	}

}
