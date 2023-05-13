package com.example.demo1mongo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "someCollection")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SomeCollection {
    @Id
    private String id;
    
    private String attr1;
    private String attr2;
    private String attr3;
    private String attr4;
    private String attr5;
    private String attr6;
}
