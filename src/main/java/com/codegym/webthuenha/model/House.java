package com.codegym.webthuenha.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(columnDefinition = "nvarchar(200)")
    @NotEmpty
    private String houseName;
    @Column(columnDefinition = "nvarchar(800)")
    @NotEmpty
    private String houseAddress;
    private int bedrooms;
    private int bathrooms;
    @Column(columnDefinition = "nvarchar(1000)")
    private String description;

    private long rent;

    @OneToMany
    @JoinTable(name = "houses_image", joinColumns = {@JoinColumn(name = "house_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private List<Image> image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "house_status_id")
    private HouseStatus status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
