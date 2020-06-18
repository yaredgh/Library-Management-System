package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Service;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.Role;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {

    void deleteById(long id);

    void save(User user);

    User findOne(long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User createUser(User user) throws Exception;
}
