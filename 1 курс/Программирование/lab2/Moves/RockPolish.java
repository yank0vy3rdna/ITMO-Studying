import ru.ifmo.se.pokemon.*;

public class RockPolish extends StatusMove{
	private double currentHP;
	RockPolish(){
		super(Type.NORMAL,0,0);

	}
	@Override
	protected boolean checkAccuracy(Pokemon att,Pokemon def){
		return true;
	}
	protected void applySelfEffects(Pokemon att){
		att.setMod(Stat.SPEED,2);
	}
	protected String describe(){
		return "натурально режет скалы";
	}
}