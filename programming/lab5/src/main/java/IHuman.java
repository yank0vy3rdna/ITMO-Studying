import java.util.ArrayList;
import java.util.List;
public interface IHuman {
	List<Human> clientsArr = new ArrayList<Human>();
    void walk(APlace place);
    void decision(String des);
    void action(String act);
    void speak(String str);
    String getName();
    void useThing(Thing thing) throws NamingException;
}