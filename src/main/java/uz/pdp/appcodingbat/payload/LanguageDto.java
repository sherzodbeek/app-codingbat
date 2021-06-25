package uz.pdp.appcodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LanguageDto {
    @NotNull(message = "Name shouldn't bew empty")
    private String name;
}
