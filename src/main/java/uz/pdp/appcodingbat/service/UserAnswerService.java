package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Question;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.entity.UserAnswers;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.UserAnswerDto;
import uz.pdp.appcodingbat.repository.QuestionRepository;
import uz.pdp.appcodingbat.repository.UserAnswerRepository;
import uz.pdp.appcodingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserAnswerService {
    @Autowired
    UserAnswerRepository userAnswerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;


    public List<UserAnswers> getUserAnswers() {
        return userAnswerRepository.findAll();
    }

    public UserAnswers getUserAnswer(Integer id) {
        return userAnswerRepository.findById(id).orElseGet(UserAnswers::new);
    }

    public ApiResponse addUserAnswer(UserAnswerDto userAnswerDto) {
        Optional<User> optionalUser = userRepository.findById(userAnswerDto.getUserId());
        if(optionalUser.isEmpty())
            return new ApiResponse("User not found!",false);
        Optional<Question> optionalQuestion = questionRepository.findById(userAnswerDto.getQuestionId());
        if(optionalQuestion.isEmpty())
            return new ApiResponse("Question not found!", false);
        User user = optionalUser.get();
        Question question = optionalQuestion.get();
        UserAnswers userAnswers = new UserAnswers();
        userAnswers.setUser(user);
        userAnswers.setQuestion(question);
        userAnswers.setCode(userAnswerDto.getCode());
        userAnswerDto.setCompleted(userAnswerDto.isCompleted());
        userAnswerRepository.save(userAnswers);
        return new ApiResponse("User answer added!", true);
    }

    public ApiResponse editUserAnswer(Integer id, UserAnswerDto userAnswerDto) {
        Optional<UserAnswers> optionalUserAnswers = userAnswerRepository.findById(id);
        if(optionalUserAnswers.isEmpty())
            return new ApiResponse("User answer not found!", false);
        Optional<User> optionalUser = userRepository.findById(userAnswerDto.getUserId());
        if(optionalUser.isEmpty())
            return new ApiResponse("User not found!",false);
        Optional<Question> optionalQuestion = questionRepository.findById(userAnswerDto.getQuestionId());
        if(optionalQuestion.isEmpty())
            return new ApiResponse("Question not found!", false);
        User user = optionalUser.get();
        Question question = optionalQuestion.get();
        UserAnswers userAnswers = optionalUserAnswers.get();
        userAnswers.setUser(user);
        userAnswers.setQuestion(question);
        userAnswers.setCode(userAnswerDto.getCode());
        userAnswers.setCompleted(userAnswerDto.isCompleted());
        userAnswerRepository.save(userAnswers);
        return new ApiResponse("User asnwer edited!", true);
    }

    public ApiResponse deleteUserAnswer(Integer id) {
        boolean existsById = userAnswerRepository.existsById(id);
        if(!existsById)
            return new ApiResponse("User answer not found!", false);
        userAnswerRepository.deleteById(id);
        return new ApiResponse("User deleted!", false);
    }
}
