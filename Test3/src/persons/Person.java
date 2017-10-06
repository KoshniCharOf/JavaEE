package persons;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public abstract class Person {
	
	protected int age;
	protected String name;
	private static int id = 1;
	private static HashMap<String, Integer> brigada = new HashMap<>();


	public Person(int age, String name) {
		this.age = age;
		this.name = name+" "+id++;
		register(this);
	}


	private void register(Person p) {
		if(isPisar()) {
			return;
		}
		if(!brigada.containsKey(p.name)) {
			brigada.put(p.name, p.age);
		}
	}
	
	
	public static Map<String, Integer> getBrigada() {
		return Collections.unmodifiableMap(brigada);
	}


	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
	
	protected boolean isPisar() {
		return false;
	}
	

}
