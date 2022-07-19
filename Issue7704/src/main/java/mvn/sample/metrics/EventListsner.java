package mvn.sample.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.config.NamingConvention;
import io.micronaut.http.context.event.HttpRequestReceivedEvent;
import io.micronaut.http.context.event.HttpRequestTerminatedEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Async;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EventListsner {


    @EventListener
    @Async
    public void onRequestReceived(HttpRequestReceivedEvent event) {

         System.out.println("RECEIVED EVENT " + event.getSource().getPath());



    }


    @EventListener
    @Async
    public void onRequestTerminated(HttpRequestTerminatedEvent event) {

        System.out.println("TERMINATED EVENT " + event.getSource().getPath());
    }
}
