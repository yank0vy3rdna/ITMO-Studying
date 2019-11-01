//Сигнатура метода включает название метода и типы параметров в определенном порядке.
public class B extends A{
	@Override
	public void hi(){
		System.out.println("Hello");
	}
	public String hi(String name){
		System.out.println("Hi, "+name);
		return "";
	}
	B(){
	}
}
