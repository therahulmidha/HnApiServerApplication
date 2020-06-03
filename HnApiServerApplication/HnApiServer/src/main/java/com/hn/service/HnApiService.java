package com.hn.service;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

import com.hn.model.Story;
import com.hn.model.TopComment;

/**
 * Interface for defininf methods required by HnApiController for handling
 * requests
 * 
 * @author Rahul Midha
 *
 */
public interface HnApiService {
	/**
	 * Get the Top 10 stories
	 * @return List<Story>
	 */
	List<Story> getTopStories();

	/**
	 * Get top parent comments for a provided storyId
	 * @param storyId
	 * @return SortedSet<TopComment>
	 */
	SortedSet<TopComment> getCommentsById(int storyId);

	/**
	 * Get all the previously served top stories
	 * @return Set<Story>
	 */
	Set<Story> getPastTopStories();
}
