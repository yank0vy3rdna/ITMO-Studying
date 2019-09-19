import ru.ifmo.se.pokemon.*;
public class Miltank extends Pokemon{
	final public static double baseHP = 40;
	double getBaseHP(){
		return baseHP;
	}
	Miltank(String name, int level){
		super(name,level);
		this.setStats(baseHP,40,50,20,30,50);
		this.setType(Type.NORMAL);
		this.setMove(new Rest(),new Flamethrowler(),new Tackle());
	}
}