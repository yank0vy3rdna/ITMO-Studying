import java.util.ArrayList;
public class Room extends APlace{
	Room(String name) {
		super(name);
		thingsArr.add(new Sink("рукомойник"));
		thingsArr.add(new Soap("мыло"));
		thingsArr.add(new Towel("полотенце"));
	}

	private ArrayList<Thing> thingsArr = new ArrayList<Thing>();
	public void useThing(Human client,Things thing){
		System.out.println("kek");
		for (int i = 0; i < thingsArr.size();i++){
			if (thingsArr.get(i).getType()==thing){
				System.out.println(client.getName() + " использует объект \"" + thingsArr.get(i).getName()+"\"");
				client.useThing(thingsArr.get(i));
				break;
			}
		}
	}
	public void paymentThing(Human client,Things thing){
		for (int i = 0; i < thingsArr.size();i++){
			if (thingsArr.get(i).getType()==thing){
				System.out.println(client.getName() + " платит нолог на объект \"" + thingsArr.get(i).getName()+"\"");
				thingsArr.get(i).addClient(client);
				break;
			}
		}
	}
}