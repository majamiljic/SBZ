package sbz.project.WebShop.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false, unique = true)
	private String email;
	private String password;
	private String name;
	private String surname;
	private String role;		// Buyer, Seller, Manager
	private Date registrationDate;
	@OneToOne(cascade = CascadeType.ALL)
	private UsersProfile usersProfile;

	public User() {
		super();
	}

	public User(Integer id, String email, String password, String name, String surname, String role, Date registrationDate,
			UsersProfile usersProfile) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.registrationDate = registrationDate;
		this.usersProfile = usersProfile;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public UsersProfile getUsersProfile() {
		return usersProfile;
	}

	public void setUsersProfile(UsersProfile usersProfile) {
		this.usersProfile = usersProfile;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", surname=" + surname
				+ ", role=" + role + ", registrationDate=" + registrationDate + ", usersProfile=" + usersProfile + "]";
	}
}
