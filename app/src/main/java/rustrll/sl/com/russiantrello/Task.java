package rustrll.sl.com.russiantrello;

public class Task {

    private String name;
    private String dueDate;

    public Task(String name, String dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public String getDueDate() {
        return dueDate;
    }
}
