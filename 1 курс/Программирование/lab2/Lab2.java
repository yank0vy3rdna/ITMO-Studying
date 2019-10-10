//package ru.ifmo.se.pokemon;
import ru.ifmo.se.pokemon.*;
public class Lab2{
	public static void main(String []args){
		Miltank p1 = new Miltank("ДимонТурбина",1);
		Goodra p2 = new Goodra("Эльтотон", 1);
		Woobat p3 = new Woobat("Куколд",1);
		Swoobat p4 = new Swoobat("Миха", 1);
		Sliggoo p5 = new Sliggoo("Андрюха",1);
		Goomy p6 = new Goomy("Камазик", 1);
		Battle b = new Battle();
		b.addAlly(p1);
		b.addFoe(p2);
		b.addAlly(p5);
		b.addFoe(p3);
		b.addAlly(p4);
		b.addFoe(p6);

		B b1 = new B(2);
		System.out.println(b1.a);

	}
}