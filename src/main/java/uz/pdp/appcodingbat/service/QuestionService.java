package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Question;
import uz.pdp.appcodingbat.entity.QuestionTopic;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.QuestionDto;
import uz.pdp.appcodingbat.repository.QuestionRepository;
import uz.pdp.appcodingbat.repository.QuestionTopicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionTopicRepository questionTopicRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        return questionRepository.findById(id).orElseGet(Question::new);
    }

    public ApiResponse addQuestion(QuestionDto questionDto) {
        Optional<QuestionTopic> optionalQuestionTopic = questionTopicRepository.findById(questionDto.getQuestionTopicId());
        if(optionalQuestionTopic.isEmpty())
            return new ApiResponse("Question topic not found!", false);
        QuestionTopic questionTopic = optionalQuestionTopic.get();
        Question question = new Question();
        question.setName(questionDto.getName());
        question.setTask(questionDto.getTask());
        question.setSolution(questionDto.getSolution());
        question.setTestCase(questionDto.getTestCase());
        question.setQuestionTopic(questionTopic);
        questionRepository.save(question);
        return new ApiResponse("Question added!", true);
    }

    public ApiResponse editQuestion(Integer id, QuestionDto questionDto) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isEmpty())
            return new ApiResponse("Question not found", false);
        Optional<QuestionTopic> optionalQuestionTopic = questionTopicRepository.findById(questionDto.getQuestionTopicId());
        if(optionalQuestionTopic.isEmpty())
            return new ApiResponse("Question topic not found!", false);
        QuestionTopic questionTopic = optionalQuestionTopic.get();
        Question question = optionalQuestion.get();
        question.setQuestionTopic(questionTopic);
        question.setName(questionDto.getName());
        question.setSolution(questionDto.getSolution());
        question.setTask(questionDto.getTask());
        question.setTestCase(questionDto.getTestCase());
        questionRepository.save(question);
        questionRepository.save(question);
        return new ApiResponse("Question edited!", true);
    }

    public ApiResponse deleteQuestion(Integer id) {
        boolean existsById = questionRepository.existsById(id);
        if(!existsById)
            return new ApiResponse("Question not found!", false);
        questionRepository.deleteById(id);
        return new ApiResponse("Question deleted!", true);
    }
}
