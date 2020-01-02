import java.util.ArrayList;
import java.io.Serializable;
public class Human extends Entity implements IHuman, Comparable<Human>{
	public void decision(String des){
		Logger.log(this.getName() + " твердо решает " + des);
	}
	public void action(String act){
		Logger.log(this.getName() + " " + act);
	}
	public void speak(String str){
		Logger.log(this.getName() + " говорит: " + str);
	}
	public int compareTo(Human hum){
		return this.getName().compareTo(hum.getName());
	}
    @Override
    public String toString() {
    	return "существо по имени " + this.getName();
    }
	public String getName(){
		return name;
	}
    public Human(){
		super("unnamed");
	}
	public Human(String name) {
		super(name);
	}
}