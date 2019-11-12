import java.util.ArrayList;
import java.util.Arrays;
public class Sink extends Thing{
	Sink(String name) throws NamingException{
		super(name);
		this.setType(Things.SINK);
		ArrayList<String> add = new ArrayList<String>(
			Arrays.asList("мокрое лицо"));
		ArrayList<String> rm = new ArrayList<String>(
			Arrays.asList("намыленное лицо"));
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