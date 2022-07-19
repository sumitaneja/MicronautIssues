package mvn.sample;


import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.rxjava2.http.client.RxHttpClient;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@Controller("/payload")
public class PayloadInputStream {



    @Post(uris = {
            "/stream"}
    )
    @Produces({MediaType.ALL})
    @Consumes({MediaType.ALL})
    @ExecuteOn(TaskExecutors.IO)
    public Single<HttpResponse> stream(HttpRequest httpRequest, @Body @Nullable InputStream payload) throws IOException, URISyntaxException {

        return Single.fromCallable(() -> streamInternal(httpRequest, payload));

    }

    @Post(uris = {
            "/binary"}
    )
    @Produces({MediaType.ALL})
    @Consumes({MediaType.ALL})
    @ExecuteOn(TaskExecutors.IO)
    public Single<HttpResponse> binary(HttpRequest httpRequest, @Body @Nullable InputStream payload) throws IOException, URISyntaxException {

        return Single.fromCallable(() -> binaryInternal(httpRequest, payload));

    }


    @Post(uris = {
            "/json"}
    )
    @Produces({MediaType.ALL})
    @Consumes({MediaType.ALL})
    @ExecuteOn(TaskExecutors.IO)
    public Single<HttpResponse> json(HttpRequest httpRequest, @Body @Nullable InputStream payload) throws IOException, URISyntaxException {

        return Single.fromCallable(() -> jsonInternal(httpRequest, payload));

    }

    private MutableHttpResponse streamInternal(HttpRequest httpRequest, InputStream payload) throws IOException, URISyntaxException, InterruptedException {


        InputStream respStream =new FileInputStream(Paths.get(PayloadInputStream.class.getClassLoader().
                getResource("largefile").toURI()).toFile());

        return HttpResponse.ok().body(respStream).contentType(MediaType.APPLICATION_OCTET_STREAM);

    }


    private MutableHttpResponse binaryInternal(HttpRequest httpRequest, InputStream payload) throws IOException, URISyntaxException, InterruptedException {

        InputStream respStream =new FileInputStream(Paths.get(PayloadInputStream.class.getClassLoader().
                getResource("largefile").toURI()).toFile());

        return HttpResponse.ok().body(respStream.readAllBytes()).contentType(MediaType.APPLICATION_OCTET_STREAM);


    }

    private MutableHttpResponse jsonInternal(HttpRequest httpRequest, InputStream payload) throws IOException, URISyntaxException, InterruptedException {

        InputStream respStream =new FileInputStream(Paths.get(PayloadInputStream.class.getClassLoader().
                getResource("largefile").toURI()).toFile());


        return HttpResponse.ok().body("{\"message\":\"hello\"}").contentType(MediaType.APPLICATION_JSON);

    }
}

