import ru.ifmo.se.pokemon.*;

public class DoubleTeam extends StatusMove{
	private double currentHP;
	DoubleTeam(){
		super(Type.NORMAL,0,0);

	}
	@Override
	protected boolean checkAccuracy(Pokemon att,Pokemon def){
		return true;
	}
	protected void applySelfEffects(Pokemon att){
		att.setMod(Stat.EVASION,1);
	}
	protected String describe(){
		return "создает своего клона";
	}
}