package com.aipeel.springbatcher.batch;

import com.aipeel.springbatcher.entity.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TweetWriter implements ItemWriter {

    private static int counter = 0;
    private static final Logger logger = LoggerFactory.getLogger("TweetWriter");

    @Override
    public void write(List list) throws Exception {
        counter++;
        logger.info("Chunk Count: "+ counter);
        for(Object to: list){
            Tweet t =(Tweet) to;
            logger.info(t.getId()+" : "+t.getUser()+" : "+t.getTweet());
        }
    }
}
