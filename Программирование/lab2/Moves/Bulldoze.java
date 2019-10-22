import ru.ifmo.se.pokemon.*;

public class Bulldoze extends SpecialMove{
	Bulldoze(){
		super(Type.GROUND,60,100);
	}
	@Override
	protected String describe(){
		return "чисто бульдозит";
	}
	protected void applyOppEffects(Pokemon opp){
		opp.setMod(Stat.SPEED,-1);
	}
}