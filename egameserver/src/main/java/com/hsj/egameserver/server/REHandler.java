package com.hsj.egameserver.server;

import org.slf4j.LoggerFactory;

public class REHandler implements Runnable {

    private Runnable delegate;

    public REHandler(Runnable delegate) {
        this.delegate = delegate;
    }

    public void run() {
        try {
            delegate.run();
        } catch (RuntimeException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
        }
    }
}