import java.util.ArrayList;
public class Human extends Entity implements IHuman, Comparable<Human>{
	public void walk (APlace place){
		Logger.log(place.getName());
	}
	public void decision(String des){
		Logger.log(this.getName() + " твердо решает " + des);
	}
	public void action(String act){
		Logger.log(this.getName() + " " + act);
	}
	public void speak(String str){
		Logger.log(this.getName() + " говорит: " + str);
	}
	public void useThing(Thing thing) throws NamingException{
		ArrayList<String> rm = thing.getonSuccessfullUsingRm();
		this.removeStatements(rm);
		ArrayList<String> add = thing.getonSuccessfullUsingAdd();
		this.addStatements(add);
	}
	public int compareTo(Human hum){
		return this.getName().compareTo(hum.getName());
	}
    @Override
    public String toString() {
    	return "существо по имени " + this.getName();
    }
    public Human() throws NamingException{
		super("unnamed");
	}
	public Human(String name) throws NamingException{
		super(name);
	}
}