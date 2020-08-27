package com.practice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import javax.persistence.*;
import java.io.IOException;
import java.time.ZonedDateTime;

@Data
@Entity
public class News {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty
    private String author;

    @Column(columnDefinition = "text")
    @JsonProperty
    private String title;

    @Column(columnDefinition = "text")
    @JsonProperty
    private String text;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @JsonProperty
    private ZonedDateTime published;

    @JsonProperty
    private String sentiment;

    @JsonProperty
    private String name;

    @Column(columnDefinition = "text")
    @JsonProperty
    private String json;
}
