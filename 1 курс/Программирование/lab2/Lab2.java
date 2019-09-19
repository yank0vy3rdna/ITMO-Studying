//package ru.ifmo.se.pokemon;
import ru.ifmo.se.pokemon.*;
public class Lab2{
	public static void main(String []args){
		System.out.println("kekus");
		Miltank kekus = new Miltank("Путин",1);
		Battle b = new Battle();
		Pokemon p1 = new Pokemon("Навальный", 1);

		//Pokemon p2 = new Pokemon("Хищник", 1);
		b.addAlly(kekus);
		b.addFoe(p1);
		b.go();
	}
}