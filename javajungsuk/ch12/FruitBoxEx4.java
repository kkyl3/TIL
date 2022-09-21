package ch12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Fruit{
	String name; // 인스턴스 변수
	int weight;
	
	Fruit(String name, int weight){ // 생성자
		this.name = name;
		this.weight = weight;
	}
	
	public String toString() { // toString() 오버라이딩 -> public
		return name+"("+weight+")";
	}
}

class Apple extends Fruit{ // 상속
	Apple(String name, int weight){ // 생성자
		super(name, weight); // 조상클래스의 생성자 호출
	}
}

class Grape extends Fruit{
	Grape(String name, int weight){
		super(name, weight);
	}
}

class AppleComp implements Comparator<Apple>{ // 구현
	@Override
	public int compare(Apple t1, Apple t2) {
		return t2.weight - t1.weight; // 내림차순
	}
}

class GrapeComp implements Comparator<Grape>{ // 구현
	@Override
	public int compare(Grape t1, Grape t2) {
		return t2.weight - t1.weight; // 내림차순
	}
}

class FruitComp implements Comparator<Fruit>{ // 구현
	@Override
	public int compare(Fruit t1, Fruit t2) {
		return t1.weight - t2.weight; // 오름차순
	}
}

public class FruitBoxEx4 {

	public static void main(String[] args) {
		FruitBox<Apple> appleBox = new FruitBox<Apple>(); // Apple은 Fruit의 자손
		FruitBox<Grape> grapeBox = new FruitBox<Grape>();
		
		appleBox.add(new Apple("GreenApple", 300)); // ArrayList에 저장
		appleBox.add(new Apple("GreenApple", 100));
		appleBox.add(new Apple("GreenApple", 200));
		
		grapeBox.add(new Grape("GreenGrape", 400));
		grapeBox.add(new Grape("GreenGrape", 300));
		grapeBox.add(new Grape("GreenGrape", 200));
		
		Collections.sort(appleBox.getList(), new AppleComp());
		Collections.sort(grapeBox.getList(), new GrapeComp());
		System.out.println(appleBox);
		System.out.println(grapeBox);
		System.out.println();
		Collections.sort(appleBox.getList(), new FruitComp()); // 조상
		Collections.sort(grapeBox.getList(), new FruitComp());
		System.out.println(appleBox);
		System.out.println(grapeBox);
	} // main

}

class FruitBox<T extends Fruit> extends Box<T> {} // FruitBox는 Fruit포함 Fruit의 자손들만 타입으로 지정가능
class Box<T> {
	ArrayList<T> list = new ArrayList<T>();
	
	void add(T item) {
		list.add(item);
	}
	
	T get(int i) {
		return list.get(i);
	}
	
	ArrayList<T> getList() {
		return list;
	}
	
	int size() {
		return list.size();
	}
	
	public String toString() { // toString() 오버라이딩 -> public
		return list.toString();
	}
}
