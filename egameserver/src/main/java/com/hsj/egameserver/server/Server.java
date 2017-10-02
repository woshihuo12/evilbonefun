package com.hsj.egameserver.server;

import com.hsj.egameserver.events.EventDispatcher;
import com.hsj.egameserver.events.server.ServerStartEvent;
import com.hsj.egameserver.events.server.ServerStopEvent;
import com.hsj.egameserver.protocol.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Random;

@DependsOn("database")
@Lazy(false)
@Service
public class Server extends EventDispatcher implements ApplicationContextAware {

    public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext.xml");
        ((AbstractApplicationContext) context).registerShutdownHook();
        Server server = Server.getInstance();

        try {
            System.gc();
            synchronized (server) {
              //  server.wait();
            }
        } catch (Exception e) {
            logger.error("Exception", e);
        }
    }

    public static enum State {
        LOADMING,
        RUNNING,
        CLOSING,
    }

    private static Server _instance = null;
    private static ApplicationContext context;

    public PacketParser getPacketParser() {
        return packetParser;
    }

    private PacketParser packetParser;

    @Autowired
    Reference reference;

    @Autowired
    Database database;

    @Autowired
    Network network;

    public World getWorld() {
        return world;
    }

    @Autowired
    World world;

    public Reference getReference() {
        return reference;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabaseModule(Database databaseModule) {
        database = databaseModule;
    }

    public Network getNetwork() {
        return network;
    }

    private static Random rand = new Random(System.currentTimeMillis());
    public static Logger logger = LoggerFactory.getLogger(Server.class);

    public static Random getRand() {
        return rand;
    }

    private State state = State.LOADMING;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private Server() {
    }

    public synchronized static Server getInstance() {
        if (_instance == null) {
            try {
                _instance = context.getBean(Server.class);
            } catch (BeansException e) {
                logger.error("Exception", e);
                return null;
            }
        }
        return _instance;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    @PostConstruct
    public void initIt() throws Exception {
        Thread.currentThread().setName("main");
        logger.info("服务器开始启动.");
        Protocol.load();
        logger.info("加载服务器配置数据");
        packetParser = new PacketParser();
        fireEvent(createEvent(ServerStartEvent.class, this));
        setState(State.RUNNING);
    }

    @PreDestroy
    public void cleanUp() {
        setState(State.CLOSING);
        fireEvent(createEvent(ServerStopEvent.class, this));
        logger.info("服务器关闭.");
        EventDispatcher.shutDown();
        database.stop();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Server.context = applicationContext;
    }
}
