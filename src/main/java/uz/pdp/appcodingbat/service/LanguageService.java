package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Language;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.LanguageDto;
import uz.pdp.appcodingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;


    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Language getLanguage(Integer id) {
        return languageRepository.findById(id).orElseGet(Language::new);
    }

    public ApiResponse addLanguage(LanguageDto languageDto) {
        boolean existsByName = languageRepository.existsByName(languageDto.getName());
        if(existsByName)
            return new ApiResponse("This programming language already exists", false);
        Language language = new Language();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Programming langugae added!", true);
    }

    public ApiResponse editLanguage(Integer id, LanguageDto languageDto) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if(!optionalLanguage.isPresent())
            return new ApiResponse("Programming language not found!", false);
        boolean existsByNameAndIdNot = languageRepository.existsByNameAndIdNot(languageDto.getName(), id);
        if(existsByNameAndIdNot)
            return new ApiResponse("This programming language already exists!", false);
        Language language = optionalLanguage.get();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Porgramming language edited!", true);
    }

    public ApiResponse deleteLanguage(Integer id) {
        boolean existsById = languageRepository.existsById(id);
        if(!existsById)
            return new ApiResponse("Programming language not found!", false);
        languageRepository.deleteById(id);
        return new ApiResponse("Programming language deleted!", true);
    }
}
