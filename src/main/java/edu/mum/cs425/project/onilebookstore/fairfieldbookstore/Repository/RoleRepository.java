package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Repository;

import edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles.Role;
import org.springframework.data.repository.CrudRepository;



public interface RoleRepository extends CrudRepository<Role,Integer> {

	Role findByname(String name);
}
