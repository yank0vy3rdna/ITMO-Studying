import java.util.ArrayList;
public class Room extends APlace{
	Room(String name) {
		super(name);
		thingsArr.add(new Sink("рукомойник"));
		thingsArr.add(new Soap("мыло"));
		thingsArr.add(new Towel("полотенце"));
	}

	public void action(String act){
		System.out.println("Произошло "+act);
	}
	private ArrayList<Thing> thingsArr = new ArrayList<Thing>();
	public void useThing(Human client,Things thing){
		for (int i = 0; i < thingsArr.size();i++){
			if (thingsArr.get(i).getType() == thing){
				if(thingsArr.get(i).checkClient(client)){
					System.out.println(client.getName() + " использует объект \"" + thingsArr.get(i).getName()+"\"");
					client.useThing(thingsArr.get(i));
					thingsArr.get(i).rmClient(client);
					break;
				}
				else
				{
					System.out.println(client.getName() + " пытается использовать объект \"" + thingsArr.get(i).getName()+"\", но ему дают по счам тк тот не заплатил нолог");
				}
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