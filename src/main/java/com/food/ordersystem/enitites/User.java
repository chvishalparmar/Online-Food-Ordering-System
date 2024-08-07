package com.food.ordersystem.enitites;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String roles;
    private String name;
    private String address;
    private String email;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , orphanRemoval = true)
    @JsonIgnore
    private List<Review> reviews;
  
}
