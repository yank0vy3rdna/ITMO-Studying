import java.util.ArrayList;
import java.util.Arrays;
public class Towel extends Thing{
	Towel(String name){
		super(name);
		this.setType(Things.TOWEL);
		ArrayList<Statement> add = new ArrayList<Statement>(
			Arrays.asList(new Statement("сухое лицо")));
		ArrayList<Statement> rm = new ArrayList<Statement>(
			Arrays.asList(new Statement("мокрое лицо")));
		this.setonSuccessfullUsingRm(rm);
		this.setonSuccessfullUsingAdd(add);
	}
}