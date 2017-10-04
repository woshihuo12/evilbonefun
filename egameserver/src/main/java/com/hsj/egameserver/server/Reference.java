package com.hsj.egameserver.server;

import com.hsj.ecommon.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class Reference {

    @Autowired
    ApplicationContext context;

    public ApplicationContext getContext() {
        return context;
    }

    private synchronized static void createInstance() {
        if (_instance == null) {
            _instance = new Reference();
        }
    }

    public static Reference getInstance() {
        if (_instance == null) {
            throw new RuntimeException();
            //createInstance();
        }
        return _instance;
    }

    private Parser itemReference;
    private Parser mobReference;
    private Parser expReference;
    private Parser npcReference;
    private Parser dropListReference;
    private Parser skillReference;

    private Parser mapReference;
    private Parser mapConfigReference;

    private Parser serverReference;
    private static Reference _instance = null;

    public Reference() {
        itemReference = new Parser();
        mobReference = new Parser();
        expReference = new Parser();
        mapReference = new Parser();
        mapConfigReference = new Parser();
        npcReference = new Parser();
        serverReference = new Parser();
        dropListReference = new Parser();
        skillReference = new Parser();
        _instance = this;
    }

    public void clear() {
        mobReference.clear();
        itemReference.clear();
        mapReference.clear();
        mapConfigReference.clear();
        expReference.clear();
        npcReference.clear();
        serverReference.clear();
        dropListReference.clear();
        skillReference.clear();
    }

    public Parser getExpReference() {
        return expReference;
    }

    public Parser getItemReference() {
        return itemReference;
    }

    public Parser getMapReference() {
        return mapReference;
    }

    public Parser getMapConfigReference() {
        return mapConfigReference;
    }

    public Parser getMobReference() {
        return mobReference;
    }

    public Parser getNpcReference() {
        return npcReference;
    }

    public Parser getServerReference() {
        return serverReference;
    }

    public Parser getDropListReference() {
        return dropListReference;
    }

    public Parser getSkillReference() {
        return skillReference;
    }

    public void setSkillReference(Parser skillReference) {
        this.skillReference = skillReference;
    }

    @PostConstruct
    public void Load() {
        clear();
        try {
            serverReference.parse(getConfigDataResource("Settings.dta"));
            mapConfigReference.parse(getConfigDataResource("Maps.dta"));
            itemReference.parse(getDataResource("Items.dta"));
            mobReference.parse(getDataResource("Mob.dta"));
            expReference.parse(getDataResource("ExpTable.dta"));
            mapReference.parse(getDataResource("Maps.dta"));
            npcReference.parse(getDataResource("Npc.dta"));
            dropListReference.parse(getDataResource("DropList.dta"));
            skillReference.parse(getDataResource("Skills.dta"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static InputStream getResourceInputStream(String path) {
        ApplicationContext context = getInstance().getContext();
        try {
            if (context != null) {
                return context.getResource(path).getInputStream();
            } else {
                return new FileInputStream(path);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static InputStream getConfigDataResource(String filename) {
        String path = new File("classpath:config", filename).getPath();
        return getResourceInputStream(path);
    }

    public static InputStream getDataResource(String filename) {
        String path = getDataPathFile(filename);
        return getResourceInputStream(path);
    }

    public static String getDataPathFile(String filename) {
        String dataPath = getInstance().getServerReference().getItem("Server").getMemberValue("DataPath");
        String path = new File(dataPath, filename).getPath();
        return path;
    }
}