package com.dominiknagy.tinyexpense.TinyExpense.implementations;

import com.dominiknagy.tinyexpense.TinyExpense.entities.account.User;
import com.dominiknagy.tinyexpense.TinyExpense.entities.account.UserProfile;
import com.dominiknagy.tinyexpense.TinyExpense.entities.enums.Currency;
import com.dominiknagy.tinyexpense.TinyExpense.utility.Mapper;
import com.dominiknagy.tinyexpense.TinyExpense.repositories.ProfileRepository;
import com.dominiknagy.tinyexpense.TinyExpense.responses.UserProfileResponse;
import com.dominiknagy.tinyexpense.TinyExpense.services.ProfileService;
import com.dominiknagy.tinyexpense.TinyExpense.utility.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;


    @Override
    public UserProfileResponse retrieveUserProfileByEmail(String userEmail) {
        UserProfile userProfile = profileRepository.findByUserEmail(userEmail).orElseThrow();
        return Mapper.mapUserProfileResponse(userProfile);
    }

    @Override
    public UserProfileResponse retrieveUserProfile() {
        UserProfile userProfile = profileRepository.findByUser(UserUtils.authedUser()).orElseThrow();
        return Mapper.mapUserProfileResponse(userProfile);
    }

    @Override
    public UserProfileResponse createUserProfile(User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setCurrency(Currency.EUR);

        userProfile = profileRepository.save(userProfile);

        return Mapper.mapUserProfileResponse(userProfile);
    }
}
