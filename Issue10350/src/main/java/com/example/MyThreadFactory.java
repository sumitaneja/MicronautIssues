package com.example;

import reactor.core.scheduler.NonBlocking;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class MyThreadFactory implements ThreadFactory, Supplier<String>, Thread.UncaughtExceptionHandler {
    private final String name;
    private final AtomicLong counterReference;
    private final boolean daemon;
    @Nullable
    private final BiConsumer<Thread, Throwable> uncaughtExceptionHandler;

    MyThreadFactory(String name, AtomicLong counterReference, boolean daemon, @Nullable BiConsumer<Thread, Throwable> uncaughtExceptionHandler) {
        this.name = name;
        this.counterReference = counterReference;
        this.daemon = daemon;
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    public final Thread newThread(@NonNull Runnable runnable) {
        String newThreadName = this.name + "-" + this.counterReference.incrementAndGet();
        Thread t =  new MyNonBlockingThread(runnable, newThreadName) ;
        if (this.daemon) {
            ((Thread)t).setDaemon(true);
        }

        if (this.uncaughtExceptionHandler != null) {
            ((Thread)t).setUncaughtExceptionHandler(this);
        }

        return (Thread)t;
    }

    public void uncaughtException(Thread t, Throwable e) {
        if (this.uncaughtExceptionHandler != null) {
            this.uncaughtExceptionHandler.accept(t, e);
        }
    }

    public final String get() {
        return this.name;
    }

    static final class MyNonBlockingThread extends Thread {
        public MyNonBlockingThread(Runnable target, String name) {
            super(target, name);
        }

        @Override
        public void interrupt() {
//            super.interrupt();
        }
    }
}
