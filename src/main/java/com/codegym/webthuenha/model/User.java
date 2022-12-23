package com.codegym.webthuenha.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    @Size(min = 6)
    private String username;
    @NotNull
    @NotEmpty
    @Size(min = 6)
    private String password;
    @Column(columnDefinition = "nvarchar(800)")
    private String fullName;
    @Column(columnDefinition = "nvarchar(800)")
    private String avatar;
    @Column(columnDefinition = "nvarchar(800)")
    private String userAddress;
    @Email
    private String email;
    @Pattern(regexp = "(09|03|07|08|05)+([0-9]{8})\\b")
    private String phoneNumber; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToMany
    @JoinTable(name = "favourite", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "house_id")})
    private List<House> favouriteHouse;
}
