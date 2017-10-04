package com.hsj.egameserver.server;

import com.hsj.ecommon.ParsedItem;

public class ServerSetings {

    private long xp;

    private long lime;

    private long startLime;

    private long defaultMapId;

    private long defaultVersion;

    private long sessionRadius;

    private long dropExclusivity;

    private String welcomeMessage;

    private long spawnAttempts;

    private long dropTimeOut;

    private float criticalMultiplier; //% ammount of critical damage increase.

    private float criticalChance; //% chance to get a critical damage

    private float petEquipmentCompensation; //% of exp compensation when changing pet equipment at npc.

    private int petBreedTime; //Time it will take to breed a pet (in seconds).

    private float itemPlusByOne; //% chance to drop a +1 item.

    private float itemPlusByTwo; //% chance to drop a +2 item.

    private float mobMutantChance; //% chance to spawn mutant mob.

    private float mobMutantModifier; //modifier to aply on mob stats.

    private long mobRadiusArea; //mob area radius.

    private int mobsMovement;    //Enable/Disable movements from mobs.

    private int expPlayermMobDifference; //lvl difference between mob and player, after that difference exp starts dropping

    private int expLowerStep; //its % value

    private int closeAttackRadius;    //Minimum distance when close attack mobs will start attacking

    private int rangeAttackRadius;    //Minimum distance when range attack mobs will start attacking

    private float demolitionModifier; //% of damage increase of demolition attacks from Slayer weapon

    private int playerMaxLevel;

    private long inventoryLimeLimit; //Maximum lime allowed on inventory

    private long warehouseLimeLimit; //Maximum lime allowed on warehouse

    public ServerSetings() {
        loadFromReference();
    }

    public long getLime() {
        return lime;
    }

    public long getXp() {
        return xp;
    }

    public void loadFromReference() {
        ParsedItem server = Reference.getInstance().getServerReference().getItem("Server");
        if (server == null) {
            // cant find Item in the reference continue to load defaults:
            setXp(1);
            setLime(1);
            setStartLime(100);
            setDefaultMapId(4);
            setDefaultVersion(2000);
            setSessionRadius(300);
            setDropExclusivity(10);
            setSpawnAttempts(1);
            setDropTimeOut(600);
            setCriticalMultiplier(1);
            setCriticalChance((float) 0.5);
            setPetEquipmentCompensation((float) 0.3);
            setItemPlusByOne((float) 0.05);
            setItemPlusByTwo((float) 0.01);
            setMobMutantChance((float) 0.1);
            setMobRadiusArea(10);
            setPetBreedTime(72000);
            setMobsMovement(1);
            setCloseAttackRadius(20);
            setRangeAttackRadius(100);
            setDemolitionModifier(0.5f);
            setPlayerMaxLevel(0);
            setInventoryLimeLimit(1200000000l);
            setWarehouseLimeLimit(10000000000l);
            setWelcomeMessage("Hey, welcome on the Reunion Testserver");
        } else {
            if (server.checkMembers(new String[]{"xp"})) {
                setXp(Long.parseLong(server.getMemberValue("xp")));
            } else {
                setXp(1);
            }
            if (server.checkMembers(new String[]{"lime"})) {
                setLime(Long.parseLong(server.getMemberValue("lime")));
            } else {
                setLime(1);
            }
            if (server.checkMembers(new String[]{"DefaultMap"})) {
                setDefaultMapId(Long.parseLong(server.getMemberValue("DefaultMap")));
            } else {
                setDefaultMapId(4);
            }
            if (server.checkMembers(new String[]{"StartLime"})) {
                setStartLime(Long.parseLong(server.getMemberValue("StartLime")));
            } else {
                setStartLime(100);
            }
            if (server.checkMembers(new String[]{"Version"})) {
                setDefaultVersion(Long.parseLong(server.getMemberValue("Version")));
            } else {
                setDefaultVersion(2000);
            }
            if (server.checkMembers(new String[]{"WelcomeMsg"})) {
                setWelcomeMessage(server.getMemberValue("WelcomeMsg"));
            } else {
                setWelcomeMessage("Hey, welcome on the Reunion Testserver");
            }
            if (server.checkMembers(new String[]{"SessionRadius"})) {
                setSessionRadius(Long.parseLong(server.getMemberValue("SessionRadius")));
            } else {
                setSessionRadius(300);
            }
            if (server.checkMembers(new String[]{"DropExclusivity"})) {
                setDropExclusivity(Long.parseLong(server.getMemberValue("DropExclusivity")));
            } else {
                setDropExclusivity(10);
            }
            if (server.checkMembers(new String[]{"SpawnAttempts"})) {
                setSpawnAttempts(Long.parseLong(server.getMemberValue("SpawnAttempts")));
            } else {
                setSpawnAttempts(1);
            }
            if (server.checkMembers(new String[]{"DropTimeOut"})) {
                setDropTimeOut(Long.parseLong(server.getMemberValue("DropTimeOut")));
            } else {
                setDropTimeOut(600);
            }
            if (server.checkMembers(new String[]{"CriticalMultiplier"})) {
                setCriticalMultiplier(Float.parseFloat(server.getMemberValue("CriticalMultiplier")));
            } else {
                setCriticalMultiplier(1);
            }
            if (server.checkMembers(new String[]{"CriticalChance"})) {
                setCriticalChance(Float.parseFloat(server.getMemberValue("CriticalChance")));
            } else {
                setCriticalChance((float) 0.5);
            }
            if (server.checkMembers(new String[]{"PetEquipmentCompensation"})) {
                setPetEquipmentCompensation(Float.parseFloat(server.getMemberValue("PetEquipmentCompensation")));
            } else {
                setPetEquipmentCompensation((float) 0.3);
            }
            if (server.checkMembers(new String[]{"ItemPlusByOne"})) {
                setItemPlusByOne(Float.parseFloat(server.getMemberValue("ItemPlusByOne")));
            } else {
                setItemPlusByOne((float) 0.05);
            }
            if (server.checkMembers(new String[]{"ItemPlusByTwo"})) {
                setItemPlusByTwo(Float.parseFloat(server.getMemberValue("ItemPlusByTwo")));
            } else {
                setItemPlusByTwo((float) 0.01);
            }
            if (server.checkMembers(new String[]{"MobMutantChance"})) {
                setMobMutantChance(Float.parseFloat(server.getMemberValue("MobMutantChance")));
            } else {
                setMobMutantChance((float) 0.1);
            }
            if (server.checkMembers(new String[]{"MobRadiusArea"})) {
                setMobRadiusArea(Long.parseLong(server.getMemberValue("MobRadiusArea")));
            } else {
                setMobRadiusArea(10);
            }
            if (server.checkMembers(new String[]{"MobMutantModifier"})) {
                setMobMutantModifier(Float.parseFloat(server.getMemberValue("MobMutantModifier")));
            } else {
                setMobMutantModifier((float) 0.5);
            }
            if (server.checkMembers(new String[]{"PetBreedTime"})) {
                setPetBreedTime(Integer.parseInt(server.getMemberValue("PetBreedTime")));
            } else {
                setPetBreedTime(72000);
            }
            if (server.checkMembers(new String[]{"MobsMovement"})) {
                setMobsMovement(Integer.parseInt(server.getMemberValue("MobsMovement")));
            } else {
                setMobsMovement(1);
            }

            if (server.checkMembers(new String[]{"ExpPlayerMobDifference"})) {
                setExpPlayerMobDifference(Integer.parseInt(server.getMemberValue("ExpPlayerMobDifference")));
            } else {
                setExpPlayerMobDifference(5);
            }

            if (server.checkMembers(new String[]{"ExpLowerStep"})) {
                setExpLowerStep(Integer.parseInt(server.getMemberValue("ExpLowerStep")));
            } else {
                setExpLowerStep(5);
            }

            if (server.checkMembers(new String[]{"CloseAttackRadius"})) {
                setCloseAttackRadius(Integer.parseInt(server.getMemberValue("CloseAttackRadius")));
            } else {
                setCloseAttackRadius(20);
            }

            if (server.checkMembers(new String[]{"RangeAttackRadius"})) {
                setRangeAttackRadius(Integer.parseInt(server.getMemberValue("RangeAttackRadius")));
            } else {
                setRangeAttackRadius(100);
            }

            if (server.checkMembers(new String[]{"DemolitionModifier"})) {
                setDemolitionModifier(Float.parseFloat(server.getMemberValue("DemolitionModifier")));
            } else {
                setDemolitionModifier(0.5f);
            }

            if (server.checkMembers(new String[]{"PlayerMaxLevel"})) {
                setPlayerMaxLevel(Integer.parseInt(server.getMemberValue("PlayerMaxLevel")));
            } else {
                setPlayerMaxLevel(0);
            }

            if (server.checkMembers(new String[]{"InventoryLimeLimit"})) {
                setInventoryLimeLimit(Long.parseLong(server.getMemberValue("InventoryLimeLimit")));
            } else {
                setInventoryLimeLimit(1200000000l);
            }

            if (server.checkMembers(new String[]{"WarehouseLimeLimit"})) {
                setWarehouseLimeLimit(Long.parseLong(server.getMemberValue("WarehouseLimeLimit")));
            } else {
                setWarehouseLimeLimit(10000000000l);
            }
        }
    }

    public void setLime(long lime) {
        this.lime = lime;
    }

    public void setPlayerMaxLevel(int level) {
        this.playerMaxLevel = level;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }

    public long getStartLime() {
        return startLime;
    }

    public void setStartLime(long startLime) {
        this.startLime = startLime;
    }

    public long getDefaultMapId() {
        return defaultMapId;
    }

    public void setDefaultMapId(long defaultMapId) {
        this.defaultMapId = defaultMapId;
    }

    public long getDefaultVersion() {
        return defaultVersion;
    }

    public void setDefaultVersion(long defaultVersion) {
        this.defaultVersion = defaultVersion;
    }

    public long getSessionRadius() {
        return sessionRadius;
    }

    public int getPlayerMaxLevel() {
        return playerMaxLevel;
    }

    public void setSessionRadius(long sessionRadius) {
        this.sessionRadius = sessionRadius;
    }

    public long getDropExclusivity() {
        return dropExclusivity;
    }

    public void setDropExclusivity(long dropExclusivity) {
        this.dropExclusivity = dropExclusivity;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public long getSpawnAttempts() {
        return spawnAttempts;
    }

    public void setSpawnAttempts(long spawnAttempts) {
        this.spawnAttempts = spawnAttempts;
    }

    public long getDropTimeOut() {
        return dropTimeOut;
    }

    public void setDropTimeOut(long dropTimeOut) {
        this.dropTimeOut = dropTimeOut;
    }

    public float getCriticalMultiplier() {
        return criticalMultiplier;
    }

    public void setCriticalMultiplier(float criticalMultiplier) {
        this.criticalMultiplier = criticalMultiplier;
    }

    public float getCriticalChance() {
        return criticalChance;
    }

    public void setCriticalChance(float criticalChance) {
        this.criticalChance = criticalChance;
    }

    public float getPetEquipmentCompensation() {
        return petEquipmentCompensation;
    }

    public void setPetEquipmentCompensation(float petEquipmentCompensation) {
        this.petEquipmentCompensation = petEquipmentCompensation;
    }

    public int getExpPlayerMobDifference() {
        return expPlayermMobDifference;
    }

    public void setExpPlayerMobDifference(int mobPlayerDiff) {
        this.expPlayermMobDifference = mobPlayerDiff;
    }

    public int getExpLowerStep() {
        return expLowerStep;
    }

    public void setExpLowerStep(int expLowerStep) {
        this.expLowerStep = expLowerStep;
    }

    public float getItemPlusByOne() {
        return itemPlusByOne;
    }

    public void setItemPlusByOne(float itemPlusByOne) {
        this.itemPlusByOne = itemPlusByOne;
    }

    public float getItemPlusByTwo() {
        return itemPlusByTwo;
    }

    public void setItemPlusByTwo(float itemPlusByTwo) {
        this.itemPlusByTwo = itemPlusByTwo;
    }

    public float getMobMutantChance() {
        return mobMutantChance;
    }

    public void setMobMutantChance(float mobMutantChance) {
        this.mobMutantChance = mobMutantChance;
    }

    public long getMobRadiusArea() {
        return mobRadiusArea;
    }

    public void setMobRadiusArea(long mobRadiusArea) {
        this.mobRadiusArea = mobRadiusArea;
    }

    public float getMobMutantModifier() {
        return mobMutantModifier;
    }

    public void setMobMutantModifier(float mobMutantModifier) {
        this.mobMutantModifier = mobMutantModifier;
    }

    public int getPetBreedTime() {
        return petBreedTime;
    }

    public void setPetBreedTime(int petBreedTime) {
        this.petBreedTime = petBreedTime;
    }

    public int getMobsMovement() {
        return mobsMovement;
    }

    public void setMobsMovement(int mobsMovement) {
        this.mobsMovement = mobsMovement;
    }

    public int getCloseAttackRadius() {
        return closeAttackRadius;
    }

    public void setCloseAttackRadius(int closeAttackRadius) {
        this.closeAttackRadius = closeAttackRadius;
    }

    public int getRangeAttackRadius() {
        return rangeAttackRadius;
    }

    public void setRangeAttackRadius(int rangeAttackRadius) {
        this.rangeAttackRadius = rangeAttackRadius;
    }

    public float getDemolitionModifier() {
        return demolitionModifier;
    }

    public void setDemolitionModifier(float demolitionModifier) {
        this.demolitionModifier = demolitionModifier;
    }

    public long getInventoryLimeLimit() {
        return inventoryLimeLimit;
    }

    public void setInventoryLimeLimit(long inventoryLimeLimit) {
        this.inventoryLimeLimit = inventoryLimeLimit;
    }

    public long getWarehouseLimeLimit() {
        return warehouseLimeLimit;
    }

    public void setWarehouseLimeLimit(long warehouseLimeLimit) {
        this.warehouseLimeLimit = warehouseLimeLimit;
    }
}

