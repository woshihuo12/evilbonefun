package com.hsj.egameserver.server;

import org.slf4j.LoggerFactory;

import java.sql.*;

public class DatabaseUtils {
    private static DatabaseUtils _dinamicInstance = null;
    private static DatabaseUtils _staticInstance = null;

    private synchronized static void createDinamicInstance() {
        if (_dinamicInstance == null) {
            _dinamicInstance = new DatabaseUtils();
        }
    }

    public static DatabaseUtils getDinamicInstance() {
        if (_dinamicInstance == null) {
            createDinamicInstance();
        }
        return _dinamicInstance;
    }

    private synchronized static void createStaticInstance() {
        if (_staticInstance == null) {
            _staticInstance = new DatabaseUtils();
        }
    }

    public static DatabaseUtils getStaticInstance() {
        if (_staticInstance == null) {
            createStaticInstance();
        }
        return _staticInstance;
    }

    private Database dinamicDatabase;

    private DatabaseUtils() {
        dinamicDatabase = null;
    }

    public boolean checkDinamicDatabase() {
        if (dinamicDatabase != null) {
            return true;
        } else {
            return false;
        }
    }

    public void setDinamicDatabase(Database dinamicDatabase) {
        this.dinamicDatabase = dinamicDatabase;
    }

    private void releaseResultAndStatement(ResultSet rs, Statement st) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int auth(String username, String password) {
        if (!checkDinamicDatabase()) {
            return -1;
        } else {
            Connection dinamicConn = dinamicDatabase.getDinamicConn();
            if (dinamicConn == null) {
                return -1;
            }
            int accountId = -1;
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;
            String sql = "SELECT id FROM accounts WHERE username=? and password=?";
            try {
                preparedStatement = dinamicConn.prepareStatement(sql);
                preparedStatement.setObject(1, username);
                preparedStatement.setObject(2, password);
                rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    String s = rs.getString("id");
                    accountId = Integer.parseInt(s);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            } finally {
                releaseResultAndStatement(rs, preparedStatement);
            }
            return accountId;
        }
    }

    public int authAdmin(String username, String password) {
        if (!checkDinamicDatabase()) {
            return -1;
        }

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int level = -1;
        String sql = "SELECT level FROM accounts WHERE username=? and password=?";
        try {
            Connection dinamicConn = dinamicDatabase.getDinamicConn();
            if (dinamicConn == null) {
                return -1;
            }

            preparedStatement = dinamicConn.prepareStatement(sql);
            preparedStatement.setObject(1, username);
            preparedStatement.setObject(2, password);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String s = rs.getString("id");
                level = Integer.parseInt(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            releaseResultAndStatement(rs, preparedStatement);
        }
        return level;
    }

    public String getCharList(Client client) {
        if (!checkDinamicDatabase()) {
            return null;
        }

        long accountId = client.getAccountId();
        String charlist = "";
        int chars = 0;

//        try {
//            Statement stmt = dinamicDatabase.getDinamicConn().createStatement();
//
//            ResultSet rs = stmt.executeQuery("SELECT * FROM `characters`,`slots` WHERE `characters`.`accountid`=" + accountId + " AND `characters`.`id`=`slots`.`charid` ORDER BY `slot` ASC");
//            while (rs.next()) {
//                int slot = rs.getInt("slot");
//                boolean alreadyLogged = false;
//                java.util.Map<SocketChannel, Client> clients = Server.getInstance().getWorld().getClients();
//                synchronized (clients) {
//                    for (Client cl : clients.values()) {
//                        if (cl.equals(client))
//                            continue;
//                        if (cl.getAccountId() == client.getAccountId()) {
//                            Player player = cl.getPlayer();
//
//                            if (player != null && player.getSlot() == slot) {
//
//                                alreadyLogged = true;
//                            }
//                        }
//                    }
//                }
//                if (alreadyLogged)
//                    continue;
//
//                Equipment eq = loadEquipment(new Equipment(null), rs.getInt("id"));
//
//                charlist += "chars_exist " + slot + " "
//                        + (client.getVersion() >= 2000 ? rs.getString("id") + " " : "") // nga client have this extra value in the packet
//                        + rs.getString("name") + " "
//                        + rs.getString("race") + " "
//                        + rs.getString("sex") + " "
//                        + rs.getString("hair") + " "
//                        + rs.getString("level") + " "
//                        + 1 + " " //hp
//                        + 1 + " " //hp max
//                        + 1 + " " //mana
//                        + 1 + " " //mana max
//                        + 1 + " " //stamina
//                        + 1 + " " //stamina max
//                        + 1 + " " //electricity
//                        + 1 + " " //electricity max
//                        + rs.getString("strength") + " "
//                        + rs.getString("wisdom") + " "
//                        + rs.getString("dexterity") + " "
//                        + rs.getString("constitution") + " "
//                        + rs.getString("leadership") + " "
//                        + "0" + " " // unknown value
//                        + eq.getTypeId(Slot.HELMET) + " "
//                        + eq.getTypeId(Slot.CHEST) + " "
//                        + eq.getTypeId(Slot.PANTS) + " "
//                        + eq.getTypeId(Slot.SHOULDER) + " "
//                        + eq.getTypeId(Slot.BOOTS) + " "
//                        + eq.getTypeId(Slot.OFFHAND)
//                        + " 0\n"; //unknown value
//
//                //chars_exist 3 12341234 0 0 0 2 90 12 15 15 90 90 15 15 30 5 5 30 10 309 -1 -1 -1 -1 -1 1
//                // chars_exist [SlotNumber] [Name] [Race] [Sex] [HairStyle]
//                // [Level] [Vitality] [Stamina] [Magic] [Energy] [Vitality]
//                // [Stamina] [Magic] [Energy] [Strength] [Wisdom]
//                // [Dexterity] [Constitution] [Leadership] [HeadGear]
//                // [Chest] [Pants] [SoulderMount] [Feet] [Shield] 0
//                chars++;
//            }
//
//        } catch (SQLException e) {
//            LoggerFactory.getLogger(this.getClass()).warn("Exception", e);
//            return null;
//        }

        LoggerFactory.getLogger(DatabaseUtils.class).info("found " + chars
                + " char(s) for Account(" + accountId + ")");

        charlist += "chars_end 0 " + accountId + "\n";
        return charlist;
    }
}
