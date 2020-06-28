package edu.mum.cs425.project.onilebookstore.fairfieldbookstore;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.Role;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.User;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.UserRole;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Service.UserService;
import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Service.utility.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashSet;
import java.util.Set;

//@EnableJpaRepositories
@SpringBootApplication
public class FairfieldbookstoreApplication implements CommandLineRunner {
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(FairfieldbookstoreApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword(UserSecurityService.passwordEncoder().encode("admin"));
        user1.setEmail("admin@gmail.com");
        user1.setFirstName("Admin");
        user1.setLastName("Admin");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(0);
        role1.setName("ROLE_ADMIN");
        userRoles.add(new UserRole(user1, role1));

        userService.createUser(user1, userRoles);
    }


}
