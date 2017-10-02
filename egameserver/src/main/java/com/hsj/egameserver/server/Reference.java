package com.hsj.egameserver.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Reference {

    @Autowired
    ApplicationContext context;

    public ApplicationContext getContext() {
        return context;
    }


}