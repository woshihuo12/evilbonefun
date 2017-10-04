package com.hsj.egameserver.server;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class Database {

    // reunion database
    private Connection dinamicConn = null;

    public Connection getDinamicConn() {
        return dinamicConn;
    }

    @Autowired
    private DataSource dataSource;

    public Database() {
    }

    public void connectDinamic() throws Exception {
        DatabaseUtils.getDinamicInstance().setDinamicDatabase(this);
        dinamicConn = dataSource.getConnection();
        LoggerFactory.getLogger(Database.class).info("Dinamic " + getClass().getSimpleName() + " connection established.");
    }

    @PostConstruct
    public void start() {
        try {
            connectDinamic();
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).warn("Exception:", e);
        }
    }

    @PreDestroy
    public void stop() {
        if (dinamicConn == null) {
            return;
        }
        try {
            dinamicConn.close();
            LoggerFactory.getLogger(Database.class).info(getClass().getName() + " connection terminated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
