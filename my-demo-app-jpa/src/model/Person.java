package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PERSON database table.
 * 
 */
//@Entity
//@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int age;

	private String name;

	public Person() {
	}
	

	public Person(int id) {
		super();
		this.id = id;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}