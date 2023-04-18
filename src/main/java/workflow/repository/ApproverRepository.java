package workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import workflow.model.Approver;

public interface ApproverRepository extends JpaRepository<Approver, Long> {

    List<Approver> findAll();

}
