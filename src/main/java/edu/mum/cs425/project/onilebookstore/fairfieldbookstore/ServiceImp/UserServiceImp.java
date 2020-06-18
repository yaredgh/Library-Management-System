package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.ServiceImp;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.Role;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.ShoppingCart;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.User;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Repository.RoleRepository;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Repository.UserRepository;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class UserServiceImp implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
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
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        //User localUser = userRepository.findByUsername(user.getUsername());

        // if(localUser != null) {
        LOGGER.info("user {} already exists. Nothing will be done.");
//            } else {
//                for (Role ur : userRoles) {
//                    //roleRepository.save(ur.getRole());
//                }
//
//                user.getUserRoles().addAll(userRoles);
//
//                ShoppingCart shoppingCart = new ShoppingCart();
//                shoppingCart.setUser(user);
//                //user.setShoppingCart(shoppingCart);
//
//
//                localUser = userRepository.save(user);
//            }
//
        return userRepository.save(user);
    }
}
