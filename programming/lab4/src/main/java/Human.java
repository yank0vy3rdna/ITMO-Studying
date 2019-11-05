import java.util.ArrayList;
public class Human extends Entity implements IHuman{
	public void walk (APlace place){
		System.out.println(place.getName());
	}
	public void decision(String des){
		System.out.println(this.getName() + " твердо решает " + des);
	}
	public void action(String act){
		System.out.println(this.getName() + " " + act);
	}
	public void speak(String str){
		System.out.println(this.getName() + " говорит: " + str);
	}
	public void useThing(Thing thing){
		ArrayList<Statement> rm = thing.getonSuccessfullUsingRm();
		this.removeStatements(rm);
		ArrayList<Statement> add = thing.getonSuccessfullUsingAdd();
		this.addStatements(add);
	}

    @Override
    public String toString() {
    	return "существо по имени " + this.getName();
    }
	Human(String name){
		super(name);
	}
}