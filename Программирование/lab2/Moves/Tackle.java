import ru.ifmo.se.pokemon.*;

public class Tackle extends PhysicalMove{
	Tackle(){
		super(Type.NORMAL,35,95);
	}
	@Override
	protected String describe(){
		return "толкнул";
	}
}