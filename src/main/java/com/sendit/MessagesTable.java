package com.sendit;


import jakarta.persistence.*;


@Entity
@Table(name = "messages")
public class MessagesTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "message")
    private String message;

    @Column(name = "abbreviation")
    private String abbreviation;

    @Column(name = "user_id")
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
