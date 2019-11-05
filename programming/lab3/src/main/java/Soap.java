import java.util.ArrayList;
import java.util.Arrays;
public class Soap extends Thing{
	Soap(String name){
		super(name);
		ArrayList<Statement> add = new ArrayList<Statement>(
			Arrays.asList(new Statement("намыленное лицо")));
		this.setType(Things.SOAP);
		this.setonSuccessfullUsingAdd(add);
	}
}