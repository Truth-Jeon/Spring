package org.galapagos.function;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.galapagos.references.Member;

public class ConstructorReferencesExample {

	public static void main(String[] args) {
		Function<String, Member> function1 = Member :: new;
		Member member1 = function1.apply("angel"); //new Member("angel");
		
		BiFunction<String, String, Member> function2 = Member :: new;
		Member member2 = function2.apply("신천사", "angel"); //new Member("신천사", "angel");
	}

}
