package workflow.service;

import java.util.List;

import workflow.model.Approver;

public interface ApproverService {

	List<Approver> findAll();

	void saveAndFlush(List<Approver> approverList);
}
