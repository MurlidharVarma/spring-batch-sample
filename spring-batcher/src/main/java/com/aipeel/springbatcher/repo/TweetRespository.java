package com.aipeel.springbatcher.repo;

import com.aipeel.springbatcher.entity.Tweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRespository extends CrudRepository<Tweet, Integer> {
}
