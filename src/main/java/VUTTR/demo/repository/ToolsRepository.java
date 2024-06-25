package VUTTR.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import VUTTR.demo.entity.Tools;

public interface ToolsRepository extends JpaRepository<Tools, Long> {
    Optional<Tools> findByTitle(String title);
    Optional<Tools> findByLink(String link);
    List<Tools> findByTags(String tag);

}
