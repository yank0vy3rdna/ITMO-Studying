import ru.ifmo.se.pokemon.*;

public class MegaDrain extends SpecialMove{
	MegaDrain(){
		super(Type.NORMAL,35,95);
	}
	@Override
	protected void applySelfDamage(Pokemon att, double damage){
		att.setMod(Stat.HP, 0-(int) Math.round(damage/2));
	}
	protected String describe(){
		return "отсасывает хилзпоинты";
	}
}