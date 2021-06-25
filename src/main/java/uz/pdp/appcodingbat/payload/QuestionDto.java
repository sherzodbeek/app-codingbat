package uz.pdp.appcodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QuestionDto {
    @NotNull(message = "Name shouldn't be emmpty!")
    private String name;

    @NotNull(message = "Task shouldn't be emmpty!")
    private String task;

    private String solution;

    @NotNull(message = "Test case shouldn't be emmpty!")
    private String testCase;

    @NotNull(message = "Question topic shouldn't be emmpty!")
    private Integer questionTopicId;
}
