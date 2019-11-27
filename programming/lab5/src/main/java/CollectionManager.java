import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Date;
import java.util.Set;
import org.codehaus.jackson.map.ObjectMapper;

class CollectionManager{
	private Date createDate;
	private Date modifyDate;
	private  HashMap<Integer, Human> humans;
	CollectionManager (){
		createDate = new Date();
		modifyDate = new Date();
	}
	public void insert(Integer key,Human hum){
		modifyDate = new Date();
		humans.put(key,hum);
	} 
	private String toStr() {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    XMLEncoder e = new XMLEncoder(baos);
	    e.writeObject(humans);
	    e.close();
	    return new String(baos.toByteArray());
	}
	public void remove(Integer key){
		humans.remove(key);
	}
	public void removeGreater(String json){
		try{
			ObjectMapper mapper = new ObjectMapper();
			Human human = mapper.readValue(json, Human.class);
			HashMap<Integer, Human> newHumans = new HashMap<>();
			for (Object i : humans.keySet().toArray()){
				if(humans.get(i).compareTo(human)<=0){
					newHumans.put((int)i,humans.get(i));
				}
			}
			humans = newHumans;
		}catch(java.io.IOException ex){}
	}
	public void removeGreaterKey(Integer key){
		HashMap<Integer, Human> newHumans = new HashMap<>();
		for (Object i : humans.keySet().toArray()){
			if((int)i<=key){
				newHumans.put((int)i,humans.get(i));
			}
		}
		humans = newHumans;
	}
	public void saveXML(String fileName){
		try{
			BufferedOutputStream file = new BufferedOutputStream(new FileOutputStream(fileName));
			XMLEncoder e = new XMLEncoder(file);
		    e.writeObject(humans);
		    e.writeObject(createDate);
		    e.writeObject(modifyDate);
		    e.close();
		    file.close();
		}
		catch(java.io.IOException ex){

		}
	}
	public void show(){
		Object[] keys = humans.keySet().toArray();
		for (Object i : keys){
			System.out.println(i + " " +humans.get(i));
		}
	}
	public void insertJSON(Integer key, String json){
		try{
			ObjectMapper mapper = new ObjectMapper();
			Human human = mapper.readValue(json, Human.class);
			insert(key,human);
		}catch(java.io.IOException ex){}
	}
	public void printInfo(){
		System.out.println("Collection type: " + humans.getClass().getSimpleName());
		System.out.println("Creation date: " + createDate.toString());
		System.out.println("Modification date: " + modifyDate.toString());
		System.out.println("Count elements: " + humans.size());
	}
	public void importXML(String fileName){
		try{
			BufferedInputStream file = new BufferedInputStream(new FileInputStream(fileName));
	        XMLDecoder dec = new XMLDecoder(file);
	        humans = (HashMap<Integer, Human>) dec.readObject();
	        createDate = (Date) dec.readObject();
	        modifyDate = (Date) dec.readObject();
	        file.close();
        }
		catch(java.io.IOException ex){

		}
	}
	public void importAnotherXML(String fileName){
		try{
			BufferedInputStream file = new BufferedInputStream(new FileInputStream(fileName));
	        XMLDecoder dec = new XMLDecoder(file);
	        humans.putAll((HashMap<Integer, Human>) dec.readObject());
	        file.close();
        }
		catch(java.io.IOException ex){

		}
	}
	public String toXML(){
		System.out.println(toStr());
		return "";
	}	
}