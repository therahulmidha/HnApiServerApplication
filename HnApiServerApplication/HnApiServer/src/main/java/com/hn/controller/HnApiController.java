package com.hn.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hn.common.AppConstants;
import com.hn.exceptions.NoRecordFoundException;
import com.hn.model.Story;
import com.hn.model.TopComment;
import com.hn.service.HnApiService;

import lombok.extern.log4j.Log4j2;

/**
 * Controller class for handling all the endpoints of the api
 * 
 * @author Rahul Midha
 *
 */
@Log4j2
@RestController
public class HnApiController {

	/**
	 * Injecting HnApiService Dependency
	 */
	@Autowired
	private HnApiService hnApiService;

	/**
	 * Get the top 10 stories ranked by score in the last 10 minutes.
	 * 
	 * @return List<Story>
	 * 
	 */
	@GetMapping("/top-stories")
	public List<Story> getTopStories() {
		log.info("Getting top 10 stories");
		/**
		 * Retrieve top stories list from service
		 */
		List<Story> topStories = hnApiService.getTopStories();
		if (topStories.isEmpty()) {
			throw new NoRecordFoundException(AppConstants.NO_STORY_FOUND_MESSAGE);
		}

		return topStories;
	}

	/**
	 * Get Top 10 parent comments on a given story A story has several comments and
	 * each comment has child comments. Return only the parent comments sorted by
	 * total number of comments
	 * 
	 * @param id
	 * @return List<TopComment>
	 */
	@GetMapping("/comments/{storyId}")
	public List<TopComment> getTopComments(@PathVariable int storyId) {
		log.info("Getting top 10 comments for Story with id: {}", storyId);
		/**
		 * Create a stream of SortedSet retrieved from getCommentsById in HnApiService
		 * and collect the top 10 comments into a list
		 */
		List<TopComment> topComments = hnApiService.getCommentsById(storyId).stream().limit(10)
				.collect(Collectors.toList());

		if (topComments.isEmpty()) {
			throw new NoRecordFoundException(AppConstants.NO_COMMENT_FOUND_MESSAGE);
		}

		return topComments;
	}

	/**
	 * Get past top stories served previously
	 * 
	 * @return Set<Story>
	 */
	@GetMapping("/past-stories")
	public Set<Story> getPastStories() {
		log.info("Getting past served stories");
		Set<Story> pastStories = hnApiService.getPastTopStories();
		if (pastStories.isEmpty()) {
			throw new NoRecordFoundException(AppConstants.NO_STORY_FOUND_MESSAGE);
		}
		return pastStories;
	}
}
