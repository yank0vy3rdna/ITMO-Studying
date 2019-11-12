import java.util.ArrayList;
import java.util.Arrays;
public class Towel extends Thing{
	Towel(String name) throws NamingException{
		super(name);
		this.setType(Things.TOWEL);
		ArrayList<String> add = new ArrayList<String>(
			Arrays.asList("сухое лицо"));
		ArrayList<String> rm = new ArrayList<String>(
			Arrays.asList("мокрое лицо"));
		this.setonSuccessfullUsingRm(rm);
		this.setonSuccessfullUsingAdd(add);
	}
}