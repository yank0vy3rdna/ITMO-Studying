import ru.ifmo.se.pokemon.*;
public class Woobat extends Pokemon{
	Woobat(String name, int level){
		super(name,level);
		this.setStats(65,45,43,55,43,72);
		this.setType(Type.PSYCHIC,Type.FLYING);
		this.setMove(new DazzlingGleam(),new Swagger(),new DoubleTeam());
	}
}