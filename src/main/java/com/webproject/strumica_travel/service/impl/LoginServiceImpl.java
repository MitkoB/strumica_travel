package com.webproject.strumica_travel.service.impl;

import com.webproject.strumica_travel.model.User;
import com.webproject.strumica_travel.model.exception.InvalidUserCredentialsException;
import com.webproject.strumica_travel.repository.UserRepository;
import com.webproject.strumica_travel.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }
}
