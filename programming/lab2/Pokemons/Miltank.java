import ru.ifmo.se.pokemon.*;
public class Miltank extends Pokemon{
	Miltank(String name, int level){
		super(name,level);
		this.setStats(40,40,50,20,30,50);
		this.setType(Type.NORMAL);
		this.setMove(new Rest(),new Flamethrowler(),new Tackle(),new RockPolish());
	}
}