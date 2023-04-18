package workflow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import workflow.model.VehicleApplication;
import workflow.repository.VehicleApplicationRepository;
import workflow.service.VehicleApplicationService;

@Service
public class VehicleApplicationServiceImpl implements VehicleApplicationService {

	private final VehicleApplicationRepository vehicleApplicationRepository;

	@Autowired
    public VehicleApplicationServiceImpl(VehicleApplicationRepository vehicleApplicationRepository) {
        this.vehicleApplicationRepository = vehicleApplicationRepository;
    }

	@Override
	public List<VehicleApplication> findAll() {
		return vehicleApplicationRepository.findAll();
	}

	@Override
	public List<VehicleApplication> findAllByLinkUsername(String userName) {
		return vehicleApplicationRepository.findAllByLinkUsername(userName);
	}

	@Override
	public List<VehicleApplication> findAllByStage(Integer stage) {
		return vehicleApplicationRepository.findAllByStage(stage);
	}

	@Override
	public void saveAndFlush(VehicleApplication vehicleApplication) {
		vehicleApplicationRepository.saveAndFlush(vehicleApplication);
	}

}
