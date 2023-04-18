package workflow.service;

import java.util.List;

import workflow.model.AdminSettings;

public interface AdminSettingsService {

	List<AdminSettings> findAll();

	void saveAndFlush(AdminSettings adminSettings);

}
