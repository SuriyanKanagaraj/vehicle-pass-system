package workflow.service;

import java.util.Optional;

import workflow.model.UserProfile;

public interface UserProfileService {

	Optional<UserProfile> findByLinkUsername(String username);

	UserProfile updateUserProfile(UserProfile userProfile);

}
