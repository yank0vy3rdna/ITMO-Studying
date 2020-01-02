import org.codehaus.jackson.map.ObjectMapper;
class JSONWorker{
	static public Object readValue(String json, Class cls){
		try{
			return (new ObjectMapper()).readValue(json,cls);
		}catch(java.io.IOException ex){
			System.out.println(ex);
		}
		return null;
	}
}