package workflow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import workflow.model.AdminSettings;
import workflow.repository.AdminSettingsRepository;
import workflow.service.AdminSettingsService;

@Service
public class AdminSettingsServiceImpl implements AdminSettingsService {

	private final AdminSettingsRepository adminSettingsRepository;

	@Autowired
    public AdminSettingsServiceImpl(AdminSettingsRepository adminSettingsRepository) {
        this.adminSettingsRepository = adminSettingsRepository;
    }

	@Override
	public List<AdminSettings> findAll() {
		return adminSettingsRepository.findAll();
	}

	@Override
	public void saveAndFlush(AdminSettings adminSettings) {
		adminSettingsRepository.saveAndFlush(adminSettings);
	}
	
}
