package com.konstantinbulygin.pmwebapp.services;

import com.konstantinbulygin.pmwebapp.dao.UserAccountRepository;
import com.konstantinbulygin.pmwebapp.entities.UserAccount;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccount save(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }
}
