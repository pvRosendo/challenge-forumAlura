package com.rosendo.forumAlura.domain.services;

import com.rosendo.forumAlura.domain.dtos.CreateAccountCredentialsDto;
import com.rosendo.forumAlura.domain.models.UserModel;
import com.rosendo.forumAlura.domain.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServices implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    public UserServices(UserRepository repository) {
        this.userRepository = repository;
    }

    @Transactional
    public CreateAccountCredentialsDto createUser(CreateAccountCredentialsDto credentials) {
        var user = new UserModel();
        var passwordEncoded = passwordEncoder(credentials.password());

//        if(userRepository.findByUsername(credentials.username()) != null){
//            throw new RuntimeException("This username is already in use!");
//        }else{
//        }
        user.setUserName(credentials.username());
        user.setPassword(passwordEncoded);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        userRepository.save(user);

        setUserPermissions(user.getId(), credentials.permission());
        return credentials;
    }

    @Transactional
    public void setUserPermissions(long userId, long permissionId) {
        String sql = "INSERT INTO tb_user_permission (id_user, id_permission) VALUES (:userId, :permissionId)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("userId", userId);
        query.setParameter("permissionId", permissionId);
        query.executeUpdate();
    }

    public String passwordEncoder(String password) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();

         Pbkdf2PasswordEncoder pbkdf2Encoder =
         new Pbkdf2PasswordEncoder(
         "", 8, 185000,
         Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

         encoders.put("pbkdf2", pbkdf2Encoder);
         DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
         passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

        return passwordEncoder.encode(password).substring(8);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
    }
}
