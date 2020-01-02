import java.util.*;
import java.util.stream.Collectors;
class Storage {
    private  Date createDate;

    public String getModifyDate() {
        return modifyDate.toString();
    }
    public String getCreateDate() {
        return createDate.toString();
    }

    private Date modifyDate;
    private Map<Integer, Human> humans;
    Storage() {
        humans = new HashMap<Integer, Human>();
        createDate = new Date();
        modifyDate = new Date();
    }
    public void remove(Integer key){
        humans.remove(key);
    }
    public void removeGreater(Human hum){
        humans = humans.entrySet().stream()
                .filter(e -> e.getValue().compareTo(hum)>0)
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
    }
    public int getSize(){
        return humans.size();
    }
    public String getCollectionType(){
        return humans.getClass().getSimpleName();
    }
    public void insert(Integer key,Human hum){
        modifyDate = new Date();
        humans.put(key,hum);
    }
    public Set<Integer> getKeySet(){
        return humans.keySet();
    }
    public Human getHumanById(int id){
        return humans.get(id);
    }
    public void saveXML(String fileName){
        XMLWorker.xmlEncode(new Object[]{humans,createDate,modifyDate},fileName);
    }
    public void initWithXML(String fileName){
        List data = XMLWorker.objectXmlDecode(fileName);
        if (data.get(0) != null){
            humans = (HashMap<Integer, Human>) data.get(0);
        }
        createDate = (Date) data.get(1);
        modifyDate = (Date) data.get(2);
    }
    public void importXML(String fileName){
        modifyDate = new Date();
        humans.putAll((HashMap<Integer, Human>) XMLWorker.objectXmlDecode(fileName).get(0));
    }

    public void removeGreaterKey(Integer key) {
        humans = humans.entrySet().stream()
                .filter(e -> e.getKey()<=key)
                .collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
    }
}
