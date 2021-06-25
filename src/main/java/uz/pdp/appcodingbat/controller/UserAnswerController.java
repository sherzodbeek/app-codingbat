package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.UserAnswers;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.UserAnswerDto;
import uz.pdp.appcodingbat.service.UserAnswerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userAnswer")
public class UserAnswerController {
    @Autowired
    UserAnswerService userAnswerService;

    @GetMapping
    public ResponseEntity<?> getAllUserAnswers() {
        List<UserAnswers> userAnswers = userAnswerService.getUserAnswers();
        return ResponseEntity.ok(userAnswers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserAnswer(@PathVariable Integer id) {
        UserAnswers userAnswers = userAnswerService.getUserAnswer(id);
        return ResponseEntity.ok(userAnswers);
    }

    @PostMapping
    public ResponseEntity<?> addUserAnswer(@Valid @RequestBody UserAnswerDto userAnswerDto) {
        ApiResponse apiResponse = userAnswerService.addUserAnswer(userAnswerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUserAnswer(@PathVariable Integer id, @Valid @RequestBody UserAnswerDto userAnswerDto) {
        ApiResponse apiResponse = userAnswerService.editUserAnswer(id, userAnswerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserAnswer(@PathVariable Integer id) {
        ApiResponse apiResponse = userAnswerService.deleteUserAnswer(id);
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
