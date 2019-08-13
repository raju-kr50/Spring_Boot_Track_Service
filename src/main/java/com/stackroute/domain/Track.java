package com.stackroute.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



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

}
