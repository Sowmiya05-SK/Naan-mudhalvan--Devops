package org.example.hcmsworker.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Complaint {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty room = new SimpleStringProperty();
    private final StringProperty category = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    public Complaint() {}

    public Complaint(int id, String name, String room, String category, String description, String status) {
        this.id.set(id);
        this.name.set(name);
        this.room.set(room);
        this.category.set(category);
        this.description.set(description);
        this.status.set(status);
    }

    // id
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    // name
    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    // room
    public String getRoom() { return room.get(); }
    public void setRoom(String room) { this.room.set(room); }
    public StringProperty roomProperty() { return room; }

    // category
    public String getCategory() { return category.get(); }
    public void setCategory(String category) { this.category.set(category); }
    public StringProperty categoryProperty() { return category; }

    // description
    public String getDescription() { return description.get(); }
    public void setDescription(String description) { this.description.set(description); }
    public StringProperty descriptionProperty() { return description; }

    // status
    public String getStatus() { return status.get(); }
    public void setStatus(String status) { this.status.set(status); }
    public StringProperty statusProperty() { return status; }

    @Override
    public String toString() {
        return "Complaint{id=" + getId() + ", name='" + getName() + "', room='" + getRoom() + "', status='" + getStatus() + "'}";
    }
}