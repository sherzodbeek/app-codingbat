package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcodingbat.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
