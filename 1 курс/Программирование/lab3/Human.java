import java.util.ArrayList;
public class Human implements IHuman{
	private ArrayList<Human> clientsArr = new ArrayList<Human>();
	private String name;
	public void walk (APlace place){
		System.out.println(place.getPlace());
	}
	public String getName(){
		return name;
	}
	public void decision(String des){
		System.out.println(this.getName() + " твердо решает " + des);
	}
	public void useThing(Thing thing){
    
	}
	Human(String name){
		this.name = name;
	}
}