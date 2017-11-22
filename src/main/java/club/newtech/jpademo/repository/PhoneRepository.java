package club.newtech.jpademo.repository;

import club.newtech.jpademo.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
