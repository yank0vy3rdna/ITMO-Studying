import java.util.ArrayList;
public class Room extends APlace{
	Room(String name) {
		super(name);
		thingsArr.add(new Sink("рукомойник"));
		thingsArr.add(new Soap("мыло"));
		thingsArr.add(new Towel("полотенце"));
		thingsArr.add(new Coiner("монетоприемник"));
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

	public void paymentThing(Human client,Things thing,int price){
		for (int i = 0; i < thingsArr.size();i++){
			if (thingsArr.get(i).getType()==Things.COINER){
				thingsArr.get(i).addClient(client);
			}
		}
		for (int i = 0; i < thingsArr.size();i++){
			if (thingsArr.get(i).getType()==thing){

				this.useThing(client,Things.COINER);
				System.out.println(client.getName() + " платит нолог на объект \"" + thingsArr.get(i).getName()+"\" в размере "+String.valueOf(price)+" сантиков");
				thingsArr.get(i).addClient(client);
				break;
			}
		}
	}
}