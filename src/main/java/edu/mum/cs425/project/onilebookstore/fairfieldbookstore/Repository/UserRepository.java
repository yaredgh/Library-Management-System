package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Repository;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

}