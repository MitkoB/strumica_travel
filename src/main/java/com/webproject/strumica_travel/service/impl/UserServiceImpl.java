package com.webproject.strumica_travel.service.impl;

import com.webproject.strumica_travel.model.User;
import com.webproject.strumica_travel.model.enumeration.Role;
import com.webproject.strumica_travel.model.exception.PasswordsDoNotMatchException;
import com.webproject.strumica_travel.model.exception.UserAlreadyExistsException;
import com.webproject.strumica_travel.model.exception.UserNotFoundException;
import com.webproject.strumica_travel.repository.UserRepository;
import com.webproject.strumica_travel.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty() || name==null || name.isEmpty() || surname==null || surname.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        if (!password.equals(repeatPassword))
        {
            throw new PasswordsDoNotMatchException();
        }
        if(this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsername(username).isEmpty())
        {
            throw new UserAlreadyExistsException(username);
        }
        return userRepository.save(new User(username,passwordEncoder.encode(password),name,surname,Role.ROLE_USER));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }

    @Override
    public User addRoleToUser(String username, Role role) {
        User user=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException());
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
