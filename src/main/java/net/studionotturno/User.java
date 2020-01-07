package net.studionotturno;


public class User {
	
	private int id;
	private String name;
	private String lastname;
	private String email;
	private String pass;
	private String nickname;
	private int victory;
	private int loses;
	private int draw;
		
	public User() {
	}

	
	public User id(int id) {
		this.id=id;
		return this;
	}
	public User name(String name) {
		this.name=name;
		return this;
	}
	public User lastname(String lastname) {
		this.lastname=lastname;
		return this;
	}
	public User email(String email) {
		this.email=email;
		return this;
	}
	public User pass(String pass) {
		this.pass=pass;
		return this;
	}
	public User nickname(String nickname) {
		this.nickname=nickname;
		return this;
	}
	public User victory(int victory) {
		this.victory=victory;
		return this;
	}
	public User losed(int loses) {
		this.loses=loses;
		return this;
	}
	public User draw(int draw) {
		this.draw=draw;
		return this;
	}

	@Override
	public String toString() {
		return id+":"+name + ":" + lastname + ":" + email + ":" + pass + ":"+nickname+":"+victory + ":"+loses + ":"+draw;
	}
	
}
