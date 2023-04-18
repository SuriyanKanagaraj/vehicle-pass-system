package workflow.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import workflow.model.UserProfile;
import workflow.repository.UserProfileRepository;
import workflow.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService{

	private final UserProfileRepository userProfileRepository;

	@Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

	@Override
	public Optional<UserProfile> findByLinkUsername(String username) {
		return userProfileRepository.findByLinkUsername(username);
	}

	@Override
    public UserProfile updateUserProfile(UserProfile userProfile) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userProfile.setLinkUsername(auth.getName());
        return userProfileRepository.saveAndFlush(userProfile);
    }

}
