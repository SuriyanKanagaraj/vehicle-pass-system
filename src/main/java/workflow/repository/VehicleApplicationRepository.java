package workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import workflow.model.VehicleApplication;

public interface VehicleApplicationRepository extends JpaRepository<VehicleApplication, Long> {

    List<VehicleApplication> findAllByLinkUsername(@Param("linkUsername") String username);

    List<VehicleApplication> findAllByStage(@Param("stage") Integer stage);
}
