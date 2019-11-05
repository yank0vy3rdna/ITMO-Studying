import java.util.ArrayList;
import java.util.Arrays;
public class Sink extends Thing{
	Sink(String name){
		super(name);
		this.setType(Things.SINK);
		ArrayList<Statement> add = new ArrayList<Statement>(
			Arrays.asList(new Statement("мокрое лицо")));
		ArrayList<Statement> rm = new ArrayList<Statement>(
			Arrays.asList(new Statement("намыленное лицо")));
		this.setonSuccessfullUsingRm(rm);
		this.setonSuccessfullUsingAdd(add);
	}
	/*
	public void turn(Human client){
		if (this.getStatus())
		{
			this.turnOff(client);
			System.out.println(client.getName() + " выключает " + this.getName());
		}
		else
		{
			this.turnOn(client);
			System.out.println(client.getName() + " включает " + this.getName());
		}
	}
	private void turnOn(Human client){
		if (this.checkClient(client))
			this.setStatus(true);
	}
	private void turnOff(Human client){
		if (this.checkClient(client))
			this.setStatus(false);
	}*/
}