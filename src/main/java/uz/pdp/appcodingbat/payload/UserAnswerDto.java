package uz.pdp.appcodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserAnswerDto {
    @NotNull(message = "User shouldn't be empty!")
    private Integer userId;

    @NotNull(message = "Question shouldn't be empty!")
    private Integer questionId;

    @NotNull(message = "Code shouldn't be empty!")
    private String code;

    private boolean completed;
}