# HnApiServerApplication
Spring Boot Project that provides APIs using the Hacker News API hosted on Firebase. (https://github.com/HackerNews/API)

This project contains following endpoints:

/top-stories: returns the top 10 stories ranked by score in the last 10 minutes.

/past-stories:returns all the past top stories that were served previously

/comments/{storyId} — returns the top 10 parent comments on a given story (sorted by the total number of comments (including child comments) per thread). (Sample storyId: 9129199)


The DockerFiles folder contains 3 files that can be run using docker commands in following order:

docker build -t hnapiserverapp .

docker-compose up


That's it. Now Api can be hit on localhost:8080.

Please wait for some time when hitting /top-stories or /comments api as it filters out a lot of data. Depending upon internet bandwidth it may take 30seconds or more.

After one hit, /top-stories will display cached data for 10 minutes. Caching is implemented using Redis. So, the docker-compose creates a redis image contained in the same container as hnapiserverapp.

Note- This project is built using Java SE 13.0.2 version.
