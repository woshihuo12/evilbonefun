package com.hsj.egameserver.server;

import com.hsj.ecommon.StringBuilderCache;
import com.hsj.egameserver.game.LivingObject;
import com.hsj.egameserver.game.Player;
import com.hsj.egameserver.game.Position;
import com.hsj.egameserver.game.WorldObject;

import java.net.InetSocketAddress;

public class PacketFactory {

    public PacketFactory() {
        super();
    }

    private static String getObjectType(WorldObject object) {
//        if (object instanceof Player) {
//            return "c";
//        } else if (object instanceof RoamingItem) {
//            return "item";
//        } else if (object instanceof Npc) {
//            return "n";
//        } else if (object instanceof Pet) {
//            return "p";
//        }
        throw new RuntimeException("Invalid Object: " + object);
    }

    public static enum Type {
        FAIL,
        INFO,
        OK,
        OUT,
        GO_WORLD,
        GOTO,
        PARTY_DISBAND,
        HOUR,
        IN_CHAR,
        SAY,
        IN_ITEM,
        DROP,
        IN_NPC,
        AT,
        PLACE,
        S_CHAR,
        WALK,
        SOCIAL,
        COMBAT,
        JUMP,
        LEVELUP,
        STATUS,
        EFFECT,
        CHAR_REMOVE,
        CHAR_WEAR,
        ATTACK,
        ATTACK_VITAL,
        SKILLLEVEL,
        PICKUP,
        PICK,
        SHOP_RATE,
        SHOP_ITEM,
        SUCCESS,
        MSG,
        STASH,
        STASH_TO,
        STASH_FROM,
        STASH_GET,
        STASH_PUT,
        STASH_END,
        INVEN,
        SKILLLEVEL_ALL,
        A_,
        SKILL,
        MULTI_SHOT,
        QUICK,
        WEARING,
        UPGRADE,
        QT,
        KILL,
        Q_EX,
        WISPER,
        SECONDATTACK,
        SAV,
        K,
        ICHANGE,
        CHIP_EXCHANGE,
        SKY,
        UPDATE_ITEM,
        USQ,                // old 2007 client
        UQ_ITEM,
        MT_ITEM,
        AV,
        PSTATUS,
        MYPET,
        PARTY_REQUEST,
        PARTY_SECESSION,
        PARTY_LIST,
        PARTY_MEMBER,
        PARTY_INFO,
        PARTY_CHANGE,
        P_KEEP,
        IN_PET,
        EXTRA,
        G_POS_START,
        G_POS_BODY,
        G_POS_END,
        EVENTNOTICE,
        GUILD_SAY,
        GUILD_LEVEL,
        GUILD_GRADE,
        GUILD_NAME,
        EXCH,
        EXCH_ASK,
        EXCH_START,
        EXCH_INVEN_TO,
        EXCH_INVEN_FROM,
        EXCH_MONEY,
        U_SHOP,
        PICK_EXTRA,
        WORKED
    }

    public static String createPacket(Type packetType, Object... args) {
        StringBuilder tmpSb = StringBuilderCache.Acquire();
        switch (packetType) {
            case FAIL:
                tmpSb.append("fail");
                for (Object o : args) {
                    tmpSb.append(" ").append(o);
                }
                break;
            case GO_WORLD:
                tmpSb.append("go_world");
                if (args.length > 0) {
                    LocalMap map = (LocalMap) args[0];
                    int unknown = args.length > 1 ? (Integer) args[1] : 0;
                    InetSocketAddress address = map.getAddress();
                    tmpSb.append(" ").append(address.getAddress().getHostAddress()).append(" ")
                            .append(address.getPort()).append(" ")
                            .append(map.getId()).append(" ")
                            .append(unknown);
                }
                break;
            case WALK:
                if (args.length > 1) {
                    LivingObject livingObject = (LivingObject) args[0];
                    Position position = (Position) args[1];

                    tmpSb.append("w ").append(getObjectType(livingObject)).append(" ")
                            .append(livingObject.getEntityId()).append(" ").append(position.getX())
                            .append(" ").append(position.getY()).append(" ").append(position.getZ())
                            .append(" ").append((livingObject.isRunning() ? 1 : 0));
                }
                break;
            default:
                break;
        }

        return StringBuilderCache.GetStringAndRelease(tmpSb);
    }
}
