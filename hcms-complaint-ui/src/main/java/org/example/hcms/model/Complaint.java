package org.example.hcms.model;

public class Complaint {
    private int id;
    private String name;
    private String room;
    private String category;
    private String description;
    private String status;

    public Complaint() {}

    public Complaint(int id, String name, String room, String category, String description, String status) {
        this.id = id; this.name = name; this.room = room; this.category = category; this.description = description; this.status = status;
    }

    public Complaint(String name, String room, String category, String description) {
        this(0, name, room, category, description, "OPEN");
    }

    // getters and setters
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getRoom(){return room;} public void setRoom(String room){this.room=room;}
    public String getCategory(){return category;} public void setCategory(String category){this.category=category;}
    public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
}
