package ex01;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class IteratorVsStreamExample {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("홍길동", "신용권", "감자바");
		
		//Iterator 이용
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()) {
			String name = iterator.next();
			System.out.println(name);
		}
		
		System.out.println();
		
		//Stream 이용
		// 1. 함수의 구현체가 생성
		// 2. 구현체의 참조가 forEach의 매개변수로 전달
		Stream<String> stream = list.stream();
		stream.forEach(name -> System.out.println(name));
		
		System.out.println();
		stream.forEach(System.out::println);
	}

}