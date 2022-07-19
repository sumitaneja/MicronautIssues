package mvn.sample;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.client.multipart.MultipartBody;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@MicronautTest
public class PayloadInputStreamSpec {
    @Inject
    EmbeddedServer server;

    @Inject
    @Client("/")
    HttpClient client;

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(5000);
    }

    @Test
    void testStreamResponse() throws IOException, URISyntaxException, InterruptedException {

        int max = 1;
        CountDownLatch latch = new CountDownLatch(max);

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < max; i++) {
            pool.submit(() -> {
                try {
                    File file = Paths.get(PayloadInputStreamSpec.class.getClassLoader().
                            getResource("largefile").toURI()).toFile();
                    MultipartBody multipartBody = MultipartBody.builder()
//                   .addPart("filename" , "someValue")
                            .addPart("myfile",
                                    file)
                            .build();
                    HttpRequest request = HttpRequest.POST("/payload/stream", multipartBody)
                            .contentType(MediaType.MULTIPART_FORM_DATA_TYPE);
                    HttpResponse response = client.toBlocking()
                            .exchange(request);
//                    System.out.println(response.getStatus());
//                    System.out.println(response.getHeaders().asMap());
//                    System.out.println(new String((byte[]) response.getBody(byte[].class).get()));

                } catch (HttpClientResponseException e) {
                    System.out.println(e.getStatus());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }

            });
        }
        latch.await();
    }

    @Test
    void testBinaryResponse() throws IOException, URISyntaxException, InterruptedException {

        int max = 1;
        CountDownLatch latch = new CountDownLatch(max);

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < max; i++) {
            pool.submit(() -> {
                try {
                    File file = Paths.get(PayloadInputStreamSpec.class.getClassLoader().
                            getResource("largefile").toURI()).toFile();
                    MultipartBody multipartBody = MultipartBody.builder()
//                   .addPart("filename" , "someValue")
                            .addPart("myfile",
                                    file)
                            .build();
                    HttpRequest request = HttpRequest.POST("/payload/binary", multipartBody)
                            .contentType(MediaType.MULTIPART_FORM_DATA_TYPE);
                    HttpResponse response = client.toBlocking()
                            .exchange(request);
//                    System.out.println(response.getStatus());
//                    System.out.println(response.getHeaders().asMap());
//                    System.out.println(new String((byte[]) response.getBody(byte[].class).get()));

                } catch (HttpClientResponseException e) {
                    System.out.println(e.getStatus());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }

            });
        }
        latch.await();
    }

    @Test
    void testJsonResponse() throws IOException, URISyntaxException, InterruptedException {

        int max = 1;
        CountDownLatch latch = new CountDownLatch(max);

        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < max; i++) {
            pool.submit(() -> {
                try {
                    File file = Paths.get(PayloadInputStreamSpec.class.getClassLoader().
                            getResource("largefile").toURI()).toFile();
                    MultipartBody multipartBody = MultipartBody.builder()
//                   .addPart("filename" , "someValue")
                            .addPart("myfile",
                                    file)
                            .build();
                    HttpRequest request = HttpRequest.POST("/payload/json", multipartBody)
                            .contentType(MediaType.MULTIPART_FORM_DATA_TYPE);
                    HttpResponse response = client.toBlocking()
                            .exchange(request);
//                    System.out.println(response.getStatus());
//                    System.out.println(response.getHeaders().asMap());
//                    System.out.println(new String((byte[]) response.getBody(byte[].class).get()));

                } catch (HttpClientResponseException e) {
                    System.out.println(e.getStatus());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }

            });
        }
        latch.await();
    }
}
