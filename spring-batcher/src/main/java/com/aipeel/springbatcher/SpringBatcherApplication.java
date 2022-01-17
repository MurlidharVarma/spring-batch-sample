package com.aipeel.springbatcher;

import com.aipeel.springbatcher.entity.Tweet;
import com.aipeel.springbatcher.repo.TweetRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringBatcherApplication {

	private static final Logger LOG = LoggerFactory.getLogger(SpringBatcherApplication.class);

	@Autowired
	public TweetRespository repo;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatcherApplication.class, args);
	}

	@PostConstruct
	public void insertRecords (){
		for (int i=0; i<=1000; i++){
			this.repo.save(new Tweet(i,"User "+i, "Tweet "+i));
		}
		LOG.info("Inserted Records: " + this.repo.count());
	}

}
