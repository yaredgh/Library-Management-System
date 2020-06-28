package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.ServiceImp;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.User;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service

public class BookStoreUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    public BookStoreUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(null == user) {
            throw new UsernameNotFoundException("Username not found");
        }

        return user;
    }


}
