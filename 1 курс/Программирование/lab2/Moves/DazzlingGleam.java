import ru.ifmo.se.pokemon.*;

public class DazzlingGleam extends SpecialMove{
	DazzlingGleam(){
		super(Type.NORMAL,80,100);
	}
	@Override
	protected String describe(){
		return "испускает мощную вспышку";
	}
}