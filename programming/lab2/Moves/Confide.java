import ru.ifmo.se.pokemon.*;

public class Confide extends SpecialMove{
	Confide(){
		super(Type.NORMAL,0,100);
	}
	@Override
	protected String describe(){
		return "доверяет противнику тайны бытия";
	}
	protected void applyOppEffects(Pokemon opp){
		opp.setMod(Stat.SPECIAL_ATTACK,-1);
	}
}