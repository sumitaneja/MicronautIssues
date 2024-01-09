package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainHandoverReactor {


    public static MutableHttpResponse addHandover(AtomicBoolean aborted, String id, HttpRequest httpRequest) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        System.out.println("staring task");
        Thread.sleep(13000l);
        System.out.println("completed task");
        if (aborted.get() ) {
            System.out.println("Now Processing complete");
            return HttpResponse.ok().body("\"message\":\"Response Generated for ID: "+ id+"\"" );
        } else {
            System.out.println("FInal Processing complete");
            return HttpResponse.ok().body("\"message\":\"This is the final response\"");
        }
    }

    static Mono<MutableHttpResponse> fallback(AtomicBoolean aborted, String id, TimeoutException t){
        {
            aborted.set(true);
            return Mono.just(HttpResponse.ok().body("\"message\":\"Short circuit Response Location: "+ id+"\"" ));
        }
    }

    static final void defaultUncaughtException(Thread t, Throwable e) {
        System.out.println("Scheduler worker in group " + t.getThreadGroup().getName() + " failed with an uncaught exception");
        e.printStackTrace();
    }


}
