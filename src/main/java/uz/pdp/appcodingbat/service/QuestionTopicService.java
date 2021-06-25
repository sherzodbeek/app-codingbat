package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Language;
import uz.pdp.appcodingbat.entity.QuestionTopic;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.QuestionTopicDto;
import uz.pdp.appcodingbat.repository.LanguageRepository;
import uz.pdp.appcodingbat.repository.QuestionTopicRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionTopicService {
    @Autowired
    QuestionTopicRepository questionTopicRepository;

    @Autowired
    LanguageRepository languageRepository;

    public List<QuestionTopic> getAllQuestionTopics() {
        return questionTopicRepository.findAll();
    }

    public QuestionTopic getQuestionTopic(Integer id) {
        return questionTopicRepository.findById(id).orElseGet(QuestionTopic::new);
    }

    public ApiResponse addQuestionTopic(QuestionTopicDto questionTopicDto) {
        Optional<Language> optionalLanguage = languageRepository.findById(questionTopicDto.getLanguageId());
        if(optionalLanguage.isEmpty()) {
            return new ApiResponse("Programming language not found!", false);
        }
        Language language = optionalLanguage.get();
        QuestionTopic questionTopic = new QuestionTopic();
        questionTopic.setName(questionTopicDto.getName());
        questionTopic.setTopicInfo(questionTopic.getTopicInfo());
        questionTopic.setLanguage(language);
        questionTopicRepository.save(questionTopic);
        return new ApiResponse("Question topic added!", true);
    }

    public ApiResponse editQuestionTopic(Integer id, QuestionTopicDto questionTopicDto) {
        Optional<QuestionTopic> optionalQuestionTopic = questionTopicRepository.findById(id);
        if(optionalQuestionTopic.isEmpty())
            return new ApiResponse("Question topic not found!", false);
        Optional<Language> optionalLanguage = languageRepository.findById(questionTopicDto.getLanguageId());
        if(optionalLanguage.isEmpty()) {
            return new ApiResponse("Programming language not found!", false);
        }
        QuestionTopic questionTopic = optionalQuestionTopic.get();
        Language language = optionalLanguage.get();
        questionTopic.setName(questionTopicDto.getName());
        questionTopic.setTopicInfo(questionTopicDto.getTopicInfo());
        questionTopic.setLanguage(language);
        questionTopicRepository.save(questionTopic);
        return new ApiResponse("Question topic edited!",false);
    }

    public ApiResponse deleteQuestionTopic(Integer id) {
        boolean existsById = questionTopicRepository.existsById(id);
        if(!existsById)
            return new ApiResponse("Programming language not found!", false);
        questionTopicRepository.deleteById(id);
        return new ApiResponse("Programming language deleted!", true);
    }
}
