package org.example.hcms.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Complaint {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty room = new SimpleStringProperty();
    private final StringProperty category = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty worker = new SimpleStringProperty();

    public Complaint() {}

    public Complaint(int id, String name, String room, String category, String description, String status, String worker) {
        this.id.set(id);
        this.name.set(name);
        this.room.set(room);
        this.category.set(category);
        this.description.set(description);
        this.status.set(status);
        this.worker.set(worker);
    }

    // ID
    public int getId() { return id.get(); }
    public void setId(int value) { id.set(value); }
    public IntegerProperty idProperty() { return id; }

    // Name
    public String getName() { return name.get(); }
    public void setName(String value) { name.set(value); }
    public StringProperty nameProperty() { return name; }

    // Room
    public String getRoom() { return room.get(); }
    public void setRoom(String value) { room.set(value); }
    public StringProperty roomProperty() { return room; }

    // Category
    public String getCategory() { return category.get(); }
    public void setCategory(String value) { category.set(value); }
    public StringProperty categoryProperty() { return category; }

    // Description
    public String getDescription() { return description.get(); }
    public void setDescription(String value) { description.set(value); }
    public StringProperty descriptionProperty() { return description; }

    // Status
    public String getStatus() { return status.get(); }
    public void setStatus(String value) { status.set(value); }
    public StringProperty statusProperty() { return status; }

    // Worker
    public String getWorker() { return worker.get(); }
    public void setWorker(String value) { worker.set(value); }
    public StringProperty workerProperty() { return worker; }
}
