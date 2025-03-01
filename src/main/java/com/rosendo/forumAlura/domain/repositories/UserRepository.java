package com.rosendo.forumAlura.domain.repositories;

import com.rosendo.forumAlura.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query("SELECT u FROM UserModel u WHERE u.userName =:userName")
    UserModel findByUsername(@Param("userName") String userName);
}
