import ru.ifmo.se.pokemon.*;
public class Sliggoo extends Pokemon{
	Sliggoo(String name, int level){
		super(name,level);
		this.setStats(68,75,53,83,113,60);
		this.setType(Type.DRAGON);
		this.setMove(new Rest(),new Confide(),new Bulldoze());
	}
}