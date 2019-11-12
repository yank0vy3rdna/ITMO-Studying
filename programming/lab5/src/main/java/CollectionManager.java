import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
class CollectionManager{
	private final HashMap<String, Human> humans = new HashMap<>();
	public void insert(String key,Human hum){
		humans.put(key,hum);
	} 
	private String toString(HashMap<String, Human> obj) {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    XMLEncoder e = new XMLEncoder(baos);
	    e.writeObject(obj);
	    e.close();
	    return new String(baos.toByteArray());
	}
	public String toXML(){
		Set keys = humans.keySet();
		for (int i = 0; i < keys.size();i++){

		}
		System.out.println(toString(humans));
		/*JAXBContext jc = JAXBContext.newInstance();
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(a, System.out);
		/*FileOutputStream fos = new FileOutputStream("humans.xml");
		XMLEncoder encoder = new XMLEncoder(fos);
		encoder.writeObject(a);
		encoder.close();
		fos.close();*/
		return "";
	}	
}