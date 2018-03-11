package train.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import train.bean.Student;
import train.tactics.FilterStudent;

public class StudentServices {

	public static List<Student> getStudentsWhenSexIsMan(List<Student> students) {
		List<Student> result = new ArrayList<>();
		for (Student student : students) {
			if (student.getAge() == 1) {
				result.add(student);
			}
		}
		return result;
	}
	
	public static List<Student> getStudentsBySex(List<Student> students, Integer sex) {
		List<Student> result = new ArrayList<>();
		for (Student student : students) {
			if (student.getAge() == sex) {
				result.add(student);
			}
		}
		return result;
	}
	
	public static List<Student> getStudentsByAge(List<Student> students, Integer age) {
		List<Student> result = new ArrayList<>();
		for (Student student : students) {
			if (student.getAge() >= age) {
				result.add(student);
			}
		}
		return result;
	}
	
	public static List<Student> getStudentsByAgeSexAndFlag(List<Student> students, Integer age,Integer sex,boolean flag) {
		List<Student> result = new ArrayList<>();
		for (Student student : students) {
			if ((flag&&student.getAge() >= age)||(!flag&&student.getAge() == sex)) {
				result.add(student);
			}
		}
		return result;
	}
	
	public static List<Student> getStudentsByFilter(List<Student> students, FilterStudent fs) {
		List<Student> result = new ArrayList<>();
		for (Student student : students) {
			if (fs.test(student)) {
				result.add(student);
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		List<Student> students = Arrays.asList(new Student(16, "张三", "上海", 1), new Student(17, "李四", "北京", 1),
				new Student(16, "王五", "广州", 0), new Student(16, "赵六", "深圳", 1));
		getStudentsByFilter(students, new FilterStudent() {
			@Override
			public boolean test(Student s) {
				if(s.getName().equals("赵六"))
					return true;
				return false;
			}
		});
		getStudentsByFilter(students, s->s.getName().equals("赵六"));
	}
}
