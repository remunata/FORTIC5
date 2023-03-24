package com.pplbo.fortic5.service.user;

import com.pplbo.fortic5.model.request.RegisterRequest;
import com.pplbo.fortic5.model.user.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    User findByUsername(String username);

    User save(User user);

    void saveAll(List<User> users);

    User register(RegisterRequest request);
}
