package io.spring.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBulderFactory;
    private final StepBuilderFactory stepBulderFactory;

    @Bean
    public Job helloJob(){
        return jobBulderFactory.get("helloJob")
                .start(helloStep1())
                .next(helloStep2())
                .build();
    }


    @Bean
    public Step helloStep1() {
        return stepBulderFactory.get("helloStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                        System.out.println("===================");
                        System.out.println(" >> Hello Spring Batch!!");
                        System.out.println("===================");

                        return RepeatStatus.FINISHED; // 한번 실행 후 멈추는 용도. null이면 무한반복임
                    }
                })
                .build();
    }

    @Bean
    public Step helloStep2() {
        return stepBulderFactory.get("helloStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

                        System.out.println("===================");
                        System.out.println(" >> step2 was executed");
                        System.out.println("===================");

                        return RepeatStatus.FINISHED; // 한번 실행 후 멈추는 용도. null이면 무한반복임
                    }
                })
                .build();    }

}
