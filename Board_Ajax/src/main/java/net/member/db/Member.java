// 데이터 빈(DataBean) 클래스 작성
package net.member.db;

public class Member {
	private String id		;
	private String password ;
	private String name		;
	private int    age		;
	private String gender	;
	private String email	;
	private String memberfile;
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMemberfile() {
		return memberfile;
	}
	public void setMemberfile(String memberfile) {
		this.memberfile = memberfile;
	}

}
