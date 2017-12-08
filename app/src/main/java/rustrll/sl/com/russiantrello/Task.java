package rustrll.sl.com.russiantrello;

import java.text.DateFormat;

public class Task {

    public Task(int id, String name, String status, String dueDate, String moscow) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.dueDate = dueDate;
        this.moscow = moscow;
    }

    public Task (String name, String dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getMoscow() {
        return moscow;
    }

    public void setMoscow(String moscow) {
        this.moscow = moscow;
    }

    private int id;
    private String name;
    private String status;
    private String dueDate;
    private String moscow;

}
