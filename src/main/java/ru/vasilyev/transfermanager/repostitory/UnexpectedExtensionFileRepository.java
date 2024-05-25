package ru.vasilyev.transfermanager.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasilyev.transfermanager.entity.FailedFileEntity;

public interface UnexpectedExtensionFileRepository extends JpaRepository <FailedFileEntity, Long> {
}
