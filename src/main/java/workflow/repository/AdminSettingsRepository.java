package workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import workflow.model.AdminSettings;

public interface AdminSettingsRepository extends JpaRepository<AdminSettings, Long> {

    List<AdminSettings> findAll();

}
