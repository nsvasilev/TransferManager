package ru.vasilyev.transfermanager.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasilyev.transfermanager.entity.BankUserEntity;

/**
 * TODO: вроде норм
 */
public interface BankUserRepository extends JpaRepository<BankUserEntity, Long> {

}
