import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove{
	private double currentHP;
	Rest(){
		super(Type.NORMAL,0,0);

	}
	@Override
	protected void applySelfEffects(Pokemon att){
		Effect eff = new Effect();
		eff = eff.condition(Status.SLEEP);
		eff = eff.turns(2);
		att.restore();
		att.setCondition(eff);
	}
	protected boolean checkAccuracy(Pokemon att,Pokemon def){
		return true;
	}
	protected String describe(){
		return "чиллит";
	}
}