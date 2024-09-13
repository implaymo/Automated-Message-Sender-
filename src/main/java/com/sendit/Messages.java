package com.sendit;

public class Messages {
    private int id;
    private String message;
    private String abbreviation;
    private int userId;


    // Getters
    public int getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }
    public String getAbbreviation() {
        return abbreviation;
    }
    public int getUserId() {
        return userId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public void setAbbreviation(String abbreviation){
        this.abbreviation = abbreviation;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }

}
