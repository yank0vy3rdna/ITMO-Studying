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
import org.codehaus.jackson.map.ObjectMapper;
class Lab5 {
	public static void main(String[] args){
		String filename = args[0];
		CollectionManager manager = new CollectionManager();
		manager.importXML(filename);
		Commander cmd = new Commander(manager);
		IActuallyDontKnowHowToNameThisClass scn = new IActuallyDontKnowHowToNameThisClass(cmd);
		scn.scan();
		manager.saveXML(filename);
	}
}