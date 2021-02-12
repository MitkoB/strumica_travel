package com.webproject.strumica_travel.service;

import com.webproject.strumica_travel.model.User;

public interface LoginService {
    User login(String username, String password);

}
