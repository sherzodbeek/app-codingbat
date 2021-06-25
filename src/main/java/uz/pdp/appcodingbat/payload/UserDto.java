package uz.pdp.appcodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull(message = "Email shouldn't be empty!")
    private String email;

    @NotNull(message = "Password shouldn't be empty!")
    private String password;
}
