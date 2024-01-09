package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.FilterChain;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;
@Filter(value = {"/**"})
public class RequestFilter implements HttpServerFilter {

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        Publisher<MutableHttpResponse<?>> respPublisher = chain.proceed(request);

        return Flowable.fromCallable(() -> true)
                .subscribeOn(Schedulers.io())
                .switchMap(aBoolean -> respPublisher)
                .doOnError(e-> {
                    System.out.println("error");
                    e.printStackTrace();
                });
    }

}
