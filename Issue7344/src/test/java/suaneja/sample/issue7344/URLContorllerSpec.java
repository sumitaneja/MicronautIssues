package suaneja.sample.issue7344;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

import org.junit.jupiter.api.Test;


import javax.inject.Inject;
import java.io.IOException;

@MicronautTest
public class URLContorllerSpec {
    @Inject
    EmbeddedServer server;

    @Inject
    @Client("/")
    HttpClient client;




    @Test
    void testHelloWorldResponse() throws IOException, InterruptedException {

        MutableHttpRequest req = HttpRequest.GET("/ic/api/integration/v1/flows/rest/automation/" +
                "AUTOMATION_SCHEDULED_USECASES/1.0/TESTORCHFLOW/data") .header("uber-trace-id", "123:234:345:456")
                .header("accept","application/xml")
                .header("X-B3-ParentSpanId", "1234")
                .header("X-B3-SpanId", "12345")
                .header("uberctx-level", "leveled").accept(MediaType.APPLICATION_JSON);
        req.bearerAuth("abcd");


        HttpResponse response = client.toBlocking()
                .exchange(req);

    }

    @Test
    void testHelloWorldResponseNOAccept() throws IOException, InterruptedException {

        MutableHttpRequest req = HttpRequest.GET("/ic/api/integration/v1/flows/rest/automation/" +
                "AUTOMATION_SCHEDULED_USECASES/1.0/TESTORCHFLOW/data") .header("uber-trace-id", "123:234:345:456")
                .header("accept","application/xml")
                .header("X-B3-ParentSpanId", "1234")
                .header("X-B3-SpanId", "12345")
                .header("uberctx-level", "leveled");
        req.bearerAuth("abcd");


        HttpResponse response = client.toBlocking()
                .exchange(req);

    }


    @Test
    void testHelloWorldResponseNoAUtomation() throws IOException, InterruptedException {

        MutableHttpRequest req = HttpRequest.GET("/ic/api/integration/v1/flows/rest/" +
                "TESTORCHFLOW/1.0/data") .header("uber-trace-id", "123:234:345:456")
                .header("accept","application/xml")
                .header("X-B3-ParentSpanId", "1234")
                .header("X-B3-SpanId", "12345")
                .header("uberctx-level", "leveled").accept(MediaType.APPLICATION_JSON);
        req.bearerAuth("abcd");


        HttpResponse response = client.toBlocking()
                .exchange(req);

    }

    @Test
    void testHelloWorldResponseNoAutomationNoAccept() throws IOException, InterruptedException {

        MutableHttpRequest req = HttpRequest.GET("/ic/api/integration/v1/flows/rest/" +
                "TESTORCHFLOW/1.0/data") .header("uber-trace-id", "123:234:345:456")
                .header("accept","application/xml")
                .header("X-B3-ParentSpanId", "1234")
                .header("X-B3-SpanId", "12345")
                .header("uberctx-level", "leveled");
        req.bearerAuth("abcd");


        HttpResponse response = client.toBlocking()
                .exchange(req);

    }
}
