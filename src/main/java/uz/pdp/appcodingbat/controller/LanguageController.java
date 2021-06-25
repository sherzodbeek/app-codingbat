package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Language;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.LanguageDto;
import uz.pdp.appcodingbat.service.LanguageService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/language")
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @GetMapping
    public ResponseEntity<?> getAllLanguages() {
        List<Language> allLanguages = languageService.getAllLanguages();
        return ResponseEntity.ok(allLanguages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLanguage(@PathVariable Integer id) {
        Language language = languageService.getLanguage(id);
        return ResponseEntity.ok(language);
    }

    @PostMapping
    public ResponseEntity<?> addLanguage(@Valid @RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.addLanguage(languageDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editLanguage(@PathVariable Integer id, @Valid @RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.editLanguage(id, languageDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable Integer id) {
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
