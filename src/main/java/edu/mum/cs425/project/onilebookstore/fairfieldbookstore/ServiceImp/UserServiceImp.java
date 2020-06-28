package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.ServiceImp;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.User;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.UserRole;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Repository.RoleRepository;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Repository.UserRepository;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;


@Service
public class UserServiceImp implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);

    }

    @Override
    public void save(User customer) {
        userRepository.save(customer);

    }

    @Override
    public User findOne(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null) {
            LOGGER.info("user {} already exists. Nothing will be done.", user.getUsername());
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            localUser = userRepository.save(user);
        }

        return localUser;
    }
}
