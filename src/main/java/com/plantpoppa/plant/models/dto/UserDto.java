package com.plantpoppa.plant.models.dto;

import lombok.Data;

@Data
public class UserDto {
    private String uuid;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String zip;
    private String role;

    public UserDto(String uuid, String firstname, String lastname, String email, String phone, String zip, String role) {
        this.uuid = uuid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.zip = zip;
        this.role = role;
    }

    public static class UserDtoBuilder{
        private String uuid;
        private String firstname;
        private String lastname;
        private String email;
        private String phone;
        private String zip;
        private String role;

        public UserDtoBuilder() {
        }

        public UserDtoBuilder uuid(String uuid){
            this.uuid = uuid;
            return this;
        }

        public UserDtoBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public UserDtoBuilder lastname(String lastname){
            this.lastname = lastname;
            return this;
        }

        public UserDtoBuilder email(String email){
            this.email = email;
            return this;
        }

        public UserDtoBuilder phone(String phone){
            this.phone = phone;
            return this;
        }

        public UserDtoBuilder zip(String zip){
            this.zip = zip;
            return this;
        }

        public  UserDtoBuilder role(String role) {
            this.role = role;
            return this;
        }

        public UserDto build() {
            return new UserDto(
                    this.uuid,
                    this.firstname,
                    this.lastname,
                    this.email,
                    this.phone,
                    this.zip,
                    this.role
            );
        }

    }
}

