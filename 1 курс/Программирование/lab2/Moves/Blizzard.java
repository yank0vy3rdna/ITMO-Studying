import ru.ifmo.se.pokemon.*;

public class Blizzard extends SpecialMove{
	Blizzard(){
		super(Type.ICE,120, 70);
	}
	@Override
	protected String describe(){
		return "буранит";
	}
	protected void applyOppEffects(Pokemon opp){
		Effect eff = new Effect();
		eff = eff.condition(Status.FREEZE);
		eff.chance(0.1);
		opp.addEffect(eff);
	}
}