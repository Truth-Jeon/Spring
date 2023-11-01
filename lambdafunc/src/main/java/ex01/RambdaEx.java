package ex01;

import java.util.Arrays;
import java.util.List;

import org.galapagos.predicate.Student;

public class RambdaEx {
	private static List<Student> list = Arrays.asList(
			new Student("홍길동", "남자", 90),
			new Student("김순희", "여자", 55),
			new Student("김자바", "남자", 45),
			new Student("박한나", "여자", 72)
	);

	public static void main(String[] args) {
		for(Student s : list) {
			System.out.println(s);
		}
		list.forEach(s->System.out.println(s));
		System.out.println();
		
		list.forEach(System.out::println);
		list.forEach(RambdaEx::deco);
		
		for(Student s : list) {
			deco(s);
		}
	}
	
	public static void deco(Student s) {
		System.out.print("=======");
		System.out.print(s.getName());
		System.out.println("=======");
	}

}
