package Mirza;

public class AuditLogEntry {

    private String user;
    private String time;
    private String action;
    private String details;

    public AuditLogEntry(String user, String time, String action, String details) {
        this.user = user;
        this.time = time;
        this.action = action;
        this.details = details;
    }

    public String getUser() {
        return user;
    }

    public String getTime() {
        return time;
    }

    public String getAction() {
        return action;
    }

    public String getDetails() {
        return details;
    }
}
