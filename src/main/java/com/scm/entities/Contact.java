package com.scm.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Contact {

    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    
    @Column(columnDefinition = "text")
    private String picture;
    
    @Column(columnDefinition = "text")
    private String description;
    private boolean favorite=false;
    private String websiteLink;
    private String linkedinLink;

    private String cloudinaryImagePublicId;

    @ManyToOne
    @JsonIgnore // use to handler recursive data loading(because contact belong to user and user belong to contact) JsonIgnore skip user info, but if we want also fetch user info then we use DTO(Data Transfer Object)
    private User user;

    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();
}
