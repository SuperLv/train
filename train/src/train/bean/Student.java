package train.bean;

public class Student {
	private Integer age;
	private String name;
	private String city;
	private Integer sex;

	public Student(Integer age, String name, String city, Integer sex) {
		this.age = age;
		this.name = name;
		this.city = city;
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

}
