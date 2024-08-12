package com.plantpoppa.plant.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plantpoppa.plant.models.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private @Id int id;

    @Column(unique = true)
    private String uuid = String.valueOf(UUID.randomUUID());

    @Column(name = "firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @Column(name="password")
    @NotNull
    @JsonIgnore
    private String password;
    private String phone;
    private String zip;
    private String role;

    @OneToMany(mappedBy = "user")
    private Set<UserPlant> userPlants;

    public UserDto toDto() {
        return new UserDto.UserDtoBuilder()
                .uuid(this.getUuid())
                .firstname(this.getFirstName())
                .lastname(this.getLastName())
                .email(this.getEmail())
                .phone(this.getPhone())
                .zip(this.getZip())
                .role(this.getRole()).build();
    }

    public UserBuilder toBuilder() {
        return new UserBuilder()
                .user_id(this.id)
                .firstname(this.firstName)
                .lastname(this.lastName)
                .email(this.email)
                .pw_hash(this.password)
                .phone(this.phone)
                .zip(this.zip);
    }


    public static class UserBuilder {
        private int user_id;
        private String uuid;
        private String firstname;
        private String lastname;
        private String email;
        private String password;
        private String phone;
        private String zip;
        private String role;

        // Empty Constructor
        public UserBuilder() {
        }

        public UserBuilder user_id(int user_id) {
            this.user_id = user_id;
            return this;
        }

        public UserBuilder uuid(String uuid){
            this.uuid = uuid;
            return this;
        }

        public UserBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public UserBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder pw_hash(String pw_hash) {
            this.password = pw_hash;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder zip(String zip) {
            this.zip = zip;
            return this;
        }

        public UserBuilder salt(byte[] salt) {
            return this;
        }

        public UserEntity build() {
            UserEntity user = new UserEntity();
            user.setId(this.user_id);
            user.setUuid(this.uuid);
            user.setEmail(this.email);
            user.setPassword(this.password);
            user.setFirstName(this.firstname);
            user.setLastName(this.lastname);
            user.setRole(this.role);
            user.setZip(this.zip);
            user.setPhone(this.phone);

            return user;
        }


    }

}

