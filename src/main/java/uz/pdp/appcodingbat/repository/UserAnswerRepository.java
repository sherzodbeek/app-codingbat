package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcodingbat.entity.UserAnswers;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswers, Integer> {
}
