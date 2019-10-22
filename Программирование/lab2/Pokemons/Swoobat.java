import ru.ifmo.se.pokemon.*;
public class Swoobat extends Pokemon{
	Swoobat(String name, int level){
		super(name,level);
		this.setStats(30,30,30,40,20,60);
		this.setType(Type.PSYCHIC,Type.FLYING);
		this.setMove(new DazzlingGleam(),new Swagger(),new DoubleTeam(),new MegaDrain());
	}
}