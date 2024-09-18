package com.cowork.project_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.AUTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String description;
    private String category;
    private List<String> tags = new ArrayList<>();
    @OneToOne(mappedBy = "project" , cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private Chat chat;
    @ManyToOne
    private  User owner;
    @OneToMany(mappedBy = "project" , cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Issue> issues = new ArrayList<>();
    @ManyToMany
    private List<User> team = new ArrayList<>();
// new fields
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dueDate;

    @Enumerated(value = STRING)
    private Status status;
}
