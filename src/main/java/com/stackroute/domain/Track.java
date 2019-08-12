package com.stackroute.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;



@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Track {

    // All the required variables declared
    @Id
    private int id;
    private String name;
    private String comments;


//    // No-arg constructor
//    public Track() {
//    }
//
//    //Parameterized constructor
//    public Track(int id, String name, String comments) {
//        this.id = id;
//        this.name = name;
//        this.comments = comments;
//    }
//
//    // Getters and setters of the declared variables
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getComments() {
//        return comments;
//    }
//
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
//
//    // to-string method
//    @Override
//    public String toString() {
//        return "Track{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", comments='" + comments + '\'' +
//                '}';
//    }
}
