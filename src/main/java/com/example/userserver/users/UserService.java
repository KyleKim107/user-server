package com.example.userserver.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserInfo createUser(UserRequest userRequest) {

        String hashedPassword = passwordEncoder.encode(userRequest.getPlainPassword());
        if (userRepository.findByUsername(userRequest.getUsername()) != null) {
            throw new RuntimeException("Username duplicated");
        }

        User user = new User(userRequest.getUsername(), userRequest.getEmail(), hashedPassword);
        User savedUser = userRepository.save(user);

        return new UserInfo(savedUser);
    }

    public UserInfo getUser(int userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        return new UserInfo(user);
    }

    public UserInfo getUserByName(String name) {
        User user = userRepository.findByUsername(name);
        if (user == null) {
            return null;
        }

        return new UserInfo(user);
    }

    public UserInfo signIn(UserRequest signInRequest) {
        User user = null;
        if (signInRequest.getUsername() != null) {
            user = userRepository.findByUsername(signInRequest.getUsername());
        }

        if (user == null) {
            return null;
        }

        boolean isPasswordMatch = passwordEncoder.matches(signInRequest.getPlainPassword(), user.getPassword());
        if (isPasswordMatch) {
            return new UserInfo(user);
        }

        return null;
    }

    public void updateLastPost(int userId, String postId, ZonedDateTime updatedDatetime) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setLastPostId(postId);
        user.setLastPostDatetime(updatedDatetime);
        userRepository.save(user);
    }
}
