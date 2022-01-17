package com.aipeel.springbatcher.batch;

import com.aipeel.springbatcher.entity.Tweet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTask
@EnableBatchProcessing
public class BatchConfig {

    private final EntityManagerFactory entityManagerFactory;
    private ItemWriter itemWriter;
    private Long chunkSize = 10L;
    private StepBuilderFactory stepBuilder;
    private DataSource dataSource;

    public BatchConfig(EntityManagerFactory entityManagerFactory,
                       ItemWriter itemWriter, DataSource dataSource, StepBuilderFactory stepBuilder) {
        this.entityManagerFactory = entityManagerFactory;
        this.itemWriter = itemWriter;
        this.dataSource = dataSource;
        this.stepBuilder = stepBuilder;
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilder, Step step){
        return jobBuilder.get("ai-peel-job")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    @JobScope
    public Step getStep( @Value("#{jobParameters['CHUNK']}") Long chunkSize){
        TaskletStep step = stepBuilder.get("ai-peel-step")
                .chunk(Math.toIntExact(chunkSize))
                .reader(getItemReader(dataSource))
                .writer(itemWriter)
                .build();
        return step;
    }


    public JpaPagingItemReader getItemReader(DataSource dataSource){
        JpaPagingItemReaderBuilder<Tweet> builder = new JpaPagingItemReaderBuilder<Tweet>();
        return builder.name("get-item-reader")
                .queryString("Select t from Tweet t")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(10)
                .saveState(false)
                .build();

    }

}
