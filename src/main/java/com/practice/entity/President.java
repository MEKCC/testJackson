package com.practice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class President {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    @Column(name = "new_name")
    private String name;

    @JsonProperty("age")
    private String age;

    @JsonIgnore
    @JsonProperty
    @OneToMany(mappedBy = "president", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Country> country = new ArrayList<>();

}
