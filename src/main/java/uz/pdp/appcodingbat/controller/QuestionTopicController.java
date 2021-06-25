package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Question;
import uz.pdp.appcodingbat.entity.QuestionTopic;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.QuestionTopicDto;
import uz.pdp.appcodingbat.service.QuestionTopicService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questionTopic")
public class QuestionTopicController {
    @Autowired
    QuestionTopicService questionTopicService;

    @GetMapping
    public ResponseEntity<?> getAllQuestionTopics() {
        List<QuestionTopic> questionTopics = questionTopicService.getAllQuestionTopics();
        return ResponseEntity.ok(questionTopics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionTopic(@PathVariable Integer id) {
        QuestionTopic questionTopic = questionTopicService.getQuestionTopic(id);
        return ResponseEntity.ok(questionTopic);
    }

    @PostMapping
    public ResponseEntity<?> addQuestionTopic(@Valid @RequestBody QuestionTopicDto questionTopicDto) {
        ApiResponse apiResponse = questionTopicService.addQuestionTopic(questionTopicDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editQuestionTopic(@PathVariable Integer id, @Valid QuestionTopicDto questionTopicDto) {
        ApiResponse apiResponse = questionTopicService.editQuestionTopic(id, questionTopicDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionTopic(@PathVariable Integer id) {
        ApiResponse apiResponse = questionTopicService.deleteQuestionTopic(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
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
