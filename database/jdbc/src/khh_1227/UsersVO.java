package khh_1227;

public class UsersVO {
	private String id;
	private String password;
	private String name;
	private String role;
	
	public UsersVO() {
		super();
	}
	public UsersVO(String id, String password, String name, String role) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UsersVO [id=" + id + ", password=" + password + ", name=" + name + ", role=" + role + "]";
	}
	
	

	
}
