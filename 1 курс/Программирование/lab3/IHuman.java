import java.util.ArrayList;
public interface IHuman {
	ArrayList<Human> clientsArr = new ArrayList<Human>();
    void walk(APlace place);
    String getName();
    void useThing(Thing thing);
}