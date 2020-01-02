import java.util.*;

import org.codehaus.jackson.map.ObjectMapper;

import static java.util.Collections.*;

class Domain {
	Storage storage;
	Domain(Storage storage){
		this.storage = storage;
	}
	public String printHelp(){
		return "insert %key% %jsoncode%\nremove %key%\nremove greater %jsoncode%\nremove greater key %key%\nshow\ninfo\nhelp\nimport %filename%\nexit";
	}
	public void removeParser(String inp){
		String[] operands = inp.split(" ",2);
		String[] params = operands[1].split(" ",2);
		if(params[0].equals("greater")){
			if (params[1].split(" ")[0].equals("key")){
				params = operands[1].split(" ",3);
				this.removeGreaterKey(Integer.parseInt(params[2]));
			}else{
				this.removeGreater(params[1]);
			}
		}else{
			storage.remove(Integer.parseInt(operands[1]));
		}
	}
	private void removeGreater(String json){
		try{
			ObjectMapper mapper = new ObjectMapper();
			Human human = mapper.readValue(json, Human.class);
			storage.removeGreater(human);
		}catch(java.io.IOException ex){ex.printStackTrace();}
	}
	private void removeGreaterKey(Integer key){
		HashMap<Integer, Human> newHumans = new HashMap<>();
		Object[] keySet = storage.getKeySet().toArray();
		for (int i = 0; i < keySet.length; i++){
			if((int)keySet[i]>key){
				storage.remove((int)i);
			}
		}
	}
	public void show(){
		Object[] keySet = storage.getKeySet().toArray();
		for (Object i : keySet){
			System.out.println(i + " " +storage.getHumanById((int)i));
		}
	}
	public void insertJSON(Integer key, String json){
		storage.insert(key,(Human)JSONWorker.readValue(json,Human.class));
	}
	public String printInfo(){
		return "Collection type: " + storage.getCollectionType()+"\n"+
		"Creation date: " + storage.getCreateDate() +"\n"+
		"Modification date: " + storage.getModifyDate()+"\n"+
		"Count elements: " + storage.getSize();
	}

	public void initWithXML(String filename) {
		storage.initWithXML(filename);
	}

	public void saveXML(String filename) {
		storage.saveXML(filename);
	}

	public void importXML(String filename) {
		storage.importXML(filename);
	}
}