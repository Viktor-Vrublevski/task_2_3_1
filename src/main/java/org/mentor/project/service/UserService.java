package org.mentor.project.service;

import org.mentor.project.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> getAll();
    User findById(Long id);
    void update(User user);
    void delete(Long id);
}
