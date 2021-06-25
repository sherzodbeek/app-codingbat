package uz.pdp.appcodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QuestionTopicDto {
    @NotNull(message = "Name shouldn't be empty!")
    private String name;

    @NotNull(message = "Topic Info shouldn't be empty!")
    private String topicInfo;

    @NotNull(message = "Programming language shouldn't be empty!")
    private Integer languageId;

}
