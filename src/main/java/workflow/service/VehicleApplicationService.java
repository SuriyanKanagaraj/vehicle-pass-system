package workflow.service;

import java.util.List;

import workflow.model.VehicleApplication;

public interface VehicleApplicationService {

	List<VehicleApplication> findAll();

	List<VehicleApplication> findAllByLinkUsername(String userName);

	List<VehicleApplication> findAllByStage(Integer stage);

	void saveAndFlush(VehicleApplication vehicleApplication);

}
