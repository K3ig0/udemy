package com.healthycoderapp;

public class Coder {
	
	private double weight;
	private double height;
	private int age;
	private Gender gender;
	
	public Coder(double weight, double height) {
		super();
		this.weight = weight;
		this.height = height;
	}
	
	public Coder(double weight, double height, int age, Gender gender) {
		super();
		this.weight = weight;
		this.height = height;
		this.age = age;
		this.gender = gender;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}	
}
