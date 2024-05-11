package ru.vasilyev.transfermanager.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vasilyev.transfermanager.entity.UnexpectedExtensionFileEntity;

public interface UnexpectedExtensionFileRepository extends JpaRepository <UnexpectedExtensionFileEntity, Long> {
}
