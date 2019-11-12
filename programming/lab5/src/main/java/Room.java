import java.util.ArrayList;
import java.util.Arrays;
public class Room extends APlace{
	Room(String name) throws NamingException{
		super(name);
	}

	private ArrayList<Thing> thingsArr = new ArrayList<Thing>();
	public void addThing(Thing thing){
		thingsArr.add(thing);
		Logger.log("Теперь в \""+this.getName()+"\" есть объект \""+thing.getName()+"\"");
	}
	public void useThingSafely(IHuman client,Things thing) throws NamingException{}
	public void useThing(IHuman client,Things thing) throws PermissionException, NamingException{
		for (int i = 0; i < thingsArr.size();i++){
			if (thingsArr.get(i).getType() == thing){
				if(thingsArr.get(i).checkClient(client)){
					Logger.log(client.getName() + " использует объект \"" + thingsArr.get(i).getName()+"\"");
					client.useThing(thingsArr.get(i));
					thingsArr.get(i).rmClient(client);
					break;
				}
				else
				{
					//Logger.log(client.getName() + " пытается использовать объект \"" + thingsArr.get(i).getName()+"\", но ему дают по счам тк тот не заплатил нолог");
					throw new PermissionException(client.getName() + " пытается использовать объект \"" + thingsArr.get(i).getName()+"\", но не имеет на это прав");
				}
			}
		}
	}
	public void letCoinerBlink() throws NamingException{
		for (int i = 0; i < thingsArr.size();i++){
			if(thingsArr.get(i).getType()==Things.COINER){
				ArrayList<String> add = new ArrayList<String>(
					Arrays.asList("мигание глазка","поблескивание металлического язычка приемника монет на стене"));
				thingsArr.get(i).addStatements(add);
			}
		}
	}
	public void letLampsOff() throws NamingException{
		ArrayList<String> add = new ArrayList<String>(
			Arrays.asList("выключился свет"));
		this.addStatements(add);	
	}
	public void paymentThing(IHuman client,Things thing,int price) throws NamingException{
		for (int i = 0; i < thingsArr.size();i++){
			if (thingsArr.get(i).getType()==Things.COINER){
				thingsArr.get(i).addClient(client);
			}
		}
		for (int i = 0; i < thingsArr.size();i++){
			if (thingsArr.get(i).getType()==thing){
				this.useThing(client,Things.COINER);
				Logger.log(client.getName() + " платит нолог на объект \"" + thingsArr.get(i).getName()+"\" в размере "+String.valueOf(price)+" сантиков");
				thingsArr.get(i).addClient(client);
				break;
			}
		}
	}
}