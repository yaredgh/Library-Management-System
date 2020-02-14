package edu.mum.cs425.project.onilebookstore.fairfieldbookstore.Modles;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	private String name;

	@ManyToMany(mappedBy="userRoles")
	private Set<User> users = new HashSet<>();

	public Role() {
	}
	public Role(int roleId) {
		this.roleId = roleId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
