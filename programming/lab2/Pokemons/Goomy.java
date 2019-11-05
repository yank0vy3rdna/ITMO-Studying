import ru.ifmo.se.pokemon.*;
public class Goomy extends Pokemon{
	Goomy(String name, int level){
		super(name,level);
		this.setStats(45,50,35,55,75,40);
		this.setType(Type.DRAGON);
		this.setMove(new Rest(),new Confide());
	}
}