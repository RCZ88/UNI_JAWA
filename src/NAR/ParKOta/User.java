package NAR.ParKOta;

import java.util.ArrayList;

public class User {
    public static final String ADMIN = "admin";
    public static final String USER = "user";

    String username;
    String password;
    String role;

    ArrayList<TransactionLog> transactionLogs = new ArrayList<>();
    TransactionLog currentLog;

    public User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void parkOut(int duration, int cost){
        currentLog.setCost(cost);
        currentLog.setDuration(duration);
        transactionLogs.add(currentLog);
        currentLog = null;
    }
    public void addTLog(TransactionLog tl){
        currentLog = tl;
    }
}
