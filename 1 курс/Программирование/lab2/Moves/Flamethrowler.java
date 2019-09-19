import ru.ifmo.se.pokemon.*;

public class Flamethrowler extends SpecialMove{
	private double currentHP;
	Flamethrowler(){
		super(Type.FIRE,95,100);
	}
	@Override
	protected void applyOppEffects(Pokemon opp){
		if (Math.random()<0.1)
			Effect.burn(opp);	
	}
	protected boolean checkAccuracy(Pokemon att,Pokemon def){
		return true;
	}
	protected String describe(){
		return "разливает дикое пламя по округе";
	}
}