package com.hsj.egameserver.server;

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


}
