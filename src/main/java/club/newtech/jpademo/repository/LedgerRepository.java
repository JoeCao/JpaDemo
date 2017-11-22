package club.newtech.jpademo.repository;


import club.newtech.jpademo.domain.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerRepository extends JpaRepository<Ledger, String> {
}
