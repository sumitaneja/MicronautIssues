package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import static reactor.core.scheduler.Schedulers.DEFAULT_BOUNDED_ELASTIC_QUEUESIZE;
import static reactor.core.scheduler.Schedulers.DEFAULT_BOUNDED_ELASTIC_SIZE;

@Controller
public class TestController {

    private Scheduler scheduler;

    @PostConstruct
    public void initialize(){
//        ThreadFactory tf = new MyThreadFactory("adapter-elastic",
//                new AtomicLong(), true,
//                MainHandoverReactor::defaultUncaughtException);

//        scheduler = Schedulers.newBoundedElastic(DEFAULT_BOUNDED_ELASTIC_SIZE,
//                DEFAULT_BOUNDED_ELASTIC_QUEUESIZE, tf, 60);
        scheduler = Schedulers.boundedElastic();

    }

    @Post(uri = "/endpoint/outbound/{endpointId}/request")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ExecuteOn(TaskExecutors.IO)
    public Mono<MutableHttpResponse> postData(@PathVariable("endpointId") String endpointId, HttpRequest httpRequest){

        AtomicBoolean aborted = new AtomicBoolean(false);
        httpRequest.setAttribute("aborted", aborted);
        String  id = UUID.randomUUID().toString();
        System.out.println(Thread.currentThread().getName());
        return Mono.fromCallable(() -> MainHandoverReactor.addHandover(aborted,id, httpRequest))
                .subscribeOn(scheduler)
//                .timeout(Duration.ofMillis(3000l))
                .timeout(Duration.ofMillis(15000l))
                .onErrorResume(TimeoutException.class, t-> MainHandoverReactor.fallback(aborted,id,t));


    }



}
