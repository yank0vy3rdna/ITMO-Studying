import ru.ifmo.se.pokemon.*;
public class Goodra extends Pokemon{
	Goodra(String name, int level){
		super(name,level);
		this.setStats(90,100,70,110,150,80);
		this.setType(Type.DRAGON);
		this.setMove(new Rest(),new Confide(),new Bulldoze(),new Blizzard());
	}
}