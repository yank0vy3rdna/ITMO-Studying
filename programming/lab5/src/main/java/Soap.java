import java.util.ArrayList;
import java.util.Arrays;
public class Soap extends Thing{
	Soap(String name) throws NamingException{
		super(name);
		ArrayList<String> add = new ArrayList<String>(
			Arrays.asList("намыленное лицо"));
		this.setType(Things.SOAP);
		this.setonSuccessfullUsingAdd(add);
	}
}