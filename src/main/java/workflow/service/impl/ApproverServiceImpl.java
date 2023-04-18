package workflow.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import workflow.model.Approver;
import workflow.repository.ApproverRepository;
import workflow.service.ApproverService;

@Service
public class ApproverServiceImpl implements ApproverService {

	private final ApproverRepository approverRepository;

	@Autowired
    public ApproverServiceImpl(ApproverRepository approverRepository) {
        this.approverRepository = approverRepository;
    }

	@Override
	public List<Approver> findAll() {
		return approverRepository.findAll();
	}

	@Override
	public void saveAndFlush(List<Approver> approverList) {
		approverList.stream().forEach(approver -> {
			approverRepository.saveAndFlush(approver);
		});
	}
	
}
