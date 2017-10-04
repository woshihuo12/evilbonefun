package com.hsj.egameserver.game;

import com.hsj.egameserver.server.PacketFactory.Type;

public abstract class LivingObject extends WorldObject {

    private LivingObject target;
    private Position targetPosition;
    private String name;
    private long hp;
    private long maxHp;
    private int level;

    //players: 0-walking 1-running
    //npc: 0-stoped 1-moving
    private boolean running;

    // 0-normal; 1-critical; 2-demolition; 3-super critical; 4-Explosion;
    private int dmgType;

    public LivingObject getTarget() {
        return target;
    }

    public void setTarget(LivingObject target) {
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getHp() {
        return hp;
    }

    public void setHp(long hp) {
        this.hp = hp;
    }

    public long getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(long maxHp) {
        this.maxHp = maxHp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDmgType() {
        return dmgType;
    }

    public void setDmgType(int dmgType) {
        this.dmgType = dmgType;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Position targetPosition) {
        this.targetPosition = targetPosition;
    }

    public int getPercentageHp() {
        double percentageHp = this.getHp() * 100 / this.getMaxHp();
        return (int) ((percentageHp < 1 && percentageHp > 0) ? 1 : percentageHp);
    }

    public void walk(Position position, boolean isRuning) {
        //setIsRunning(isRunning);
        synchronized (this) {
            setPosition(position);
            setTargetPosition(position.clone());
        }
        getInterested().sendPacket(Type.WALK, this, position);

    }

}
