package train.tactics;

import java.util.ArrayList;
import java.util.List;

import train.bean.Student;

public class FilterStudentBySex implements FilterStudent {

	@Override
	public boolean test(Student s) {
		if (s.getAge() == 1) {
			return true;
		}
		return false;
	}
}
