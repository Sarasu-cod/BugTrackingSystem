import java.io.Serializable;

public class Bug implements Serializable {
    private String id;
    private String title;
    private String description;
    private String severity;
    private String status;
    private String assignedTo;
    private String reportedBy;
    private String date;

    public Bug(String id, String title, String description, String severity, String status, String assignedTo, String reportedBy, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.status = status;
        this.assignedTo = assignedTo;
        this.reportedBy = reportedBy;
        this.date = date;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getSeverity() { return severity; }
    public String getStatus() { return status; }
    public String getAssignedTo() { return assignedTo; }
    public String getReportedBy() { return reportedBy; }
    public String getDate() { return date; }
    
    @Override
    public String toString() {
        return id + "|" + title + "|" + description + "|" + severity + "|" + status + "|" + assignedTo + "|" + reportedBy + "|" + date;
    }
}