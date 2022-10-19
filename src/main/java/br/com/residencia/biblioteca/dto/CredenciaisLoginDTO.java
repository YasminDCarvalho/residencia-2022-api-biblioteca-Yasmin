package br.com.residencia.biblioteca.dto;

public class CredenciaisLoginDTO {
	private String email;
	private String password;
	
	public CredenciaisLoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public CredenciaisLoginDTO() {
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

}
