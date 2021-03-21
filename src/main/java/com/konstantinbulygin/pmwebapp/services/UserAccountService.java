package com.konstantinbulygin.pmwebapp.services;

import com.konstantinbulygin.pmwebapp.dao.UserAccountRepository;
import com.konstantinbulygin.pmwebapp.entities.UserAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccount save(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    public List<UserAccount> findAll() {
        return userAccountRepository.findAll();
    }

    public UserAccount findByUserId(long id) {
        return userAccountRepository.findByUserId(id);
    }

    public void delete(long id) {
        userAccountRepository.deleteById(id);
    }

    public String getUserRole(long id) {
        return userAccountRepository.getUserRole(id);
    }
}
