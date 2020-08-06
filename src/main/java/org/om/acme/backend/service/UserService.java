package org.om.acme.backend.service;

import org.om.acme.backend.model.User;
import org.om.acme.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> listUser() {
        return repository.findAll();
    }

    public  void createUser(User user) {
        repository.save(user);
    }

    public User readUser(Long id) {
        return repository.getOne(id);
    }

    public void update(Long id, User user) {
        if(user != null && repository.existsById(id)) {
            User oldUser = repository.getOne(id);
            oldUser.setUsername(user.getUsername());
            oldUser.setPassword(user.getPassword());
            oldUser.setAdmin(user.getAdmin());
            repository.save(oldUser);
        }
    }
}