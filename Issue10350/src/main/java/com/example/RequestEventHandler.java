package com.example;

import io.micronaut.context.event.ApplicationEvent;
import io.micronaut.http.context.event.HttpRequestTerminatedEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Async;
import jakarta.inject.Singleton;

import java.util.concurrent.atomic.AtomicBoolean;

@Singleton
public class RequestEventHandler {

    @EventListener
    @Async
    public void onRequestTerminated(ApplicationEvent event) {
        System.out.println("EVENT" + event.toString());
//        ((AtomicBoolean)event.getSource().getAttribute("aborted").get()).set(false);
    }

}
