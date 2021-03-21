package com.konstantinbulygin.pmwebapp.dao;

import com.konstantinbulygin.pmwebapp.entities.UserAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    @Override
    List<UserAccount> findAll();

    UserAccount findByUserId(long id);

    @Query(nativeQuery = true, value = "SELECT role FROM user_accounts WHERE user_accounts.user_id = :id")
    String getUserRole(long id);

}
