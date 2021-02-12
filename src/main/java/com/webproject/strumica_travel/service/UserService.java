package com.webproject.strumica_travel.service;

import com.webproject.strumica_travel.model.User;
import com.webproject.strumica_travel.model.enumeration.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname,  Role role);
}
