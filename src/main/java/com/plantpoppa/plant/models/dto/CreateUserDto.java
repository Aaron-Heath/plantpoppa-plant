package com.plantpoppa.plant.models.dto;

import com.plantpoppa.plant.models.UserEntity;
import lombok.Data;

@Data
public class CreateUserDto {
    String email;
    String password;
    String firstName;
    String lastName;
    String phone;
    String zip;

    public UserEntity toUser() {
        UserEntity user =  new UserEntity();
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setPhone(this.phone);
        user.setZip(this.zip);
        return user;
    }
}
