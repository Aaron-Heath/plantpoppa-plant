package com.plantpoppa.plant.models;

import com.plantpoppa.plant.models.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Column(name = "user_id")
    private @Id int userId;

    private String uuid = String.valueOf(UUID.randomUUID());

    @Column(name = "firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    private String email;

    @Column(name="pw_hash")
    private String password;

    private String phone;

    private String zip;

    private byte[] salt;

    private String role;


    // Full constructor
    public UserEntity(int user_id, String uuid, String email, String password, String firstName, String lastName, String phone, String zip, byte[] salt, String role) {
        this.userId = user_id;
        // Add if condition for UserBuilder user creation
        if(uuid == null) {
            this.uuid = String.valueOf(UUID.randomUUID());
        } else {
            this.uuid = uuid;
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.zip = zip;
        this.salt = salt;
        this.role = role;
    }

    // Constructor without id
    public UserEntity(String uuid, String email, String password, String firstName, String lastName, String phone, String zip, byte[] salt, String role) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.zip = zip;
        this.salt = salt;
        this.role = role;
    }
    //Constructor without pw_hash and without salt
    public UserEntity(int userId, String email, String firstName, String lastName, String phone, String zip, String role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.zip = zip;
        this.role = role;
    }

    //Constructor with email/password
    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }
    // Empty Constructor
    public UserEntity() {
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserBuilder toBuilder() {
        return new UserBuilder()
                .user_id(this.userId)
                .firstname(this.firstName)
                .lastname(this.lastName)
                .email(this.email)
                .pw_hash(this.password)
                .phone(this.phone)
                .zip(this.zip)
                .salt(this.salt);
    }


    public static class UserBuilder {
        private int user_id;
        private String uuid;
        private String firstname;
        private String lastname;
        private String email;
        private String pw_hash;
        private String phone;
        private String zip;
        private byte[] salt;
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
            this.pw_hash = pw_hash;
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
            this.salt = salt;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this.user_id,
                    this.uuid,
                    this.email,
                    this.pw_hash,
                    this.firstname,
                    this.lastname,
                    this.phone,
                    this.zip,
                    this.salt,
                    this.role);
        }


    }

}

