import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
class XMLWorker{
	static String xmlEncode(Object[] objs){
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLEncoder e = new XMLEncoder(baos);
		for (Object i : objs){
	    	e.writeObject(i);
		}
		e.close();
		return new String(baos.toByteArray());
	}
	static void xmlEncode(Object[] objs,String filename){
		try{
			BufferedOutputStream file = new BufferedOutputStream(new FileOutputStream(filename));
			XMLEncoder e = new XMLEncoder(file);
			for (Object i : objs){
		    	e.writeObject(i);
			}
			e.close();
			file.close();
		}
		catch(java.io.IOException ex){
			System.out.println(ex);
		}
	}
	public static List objectXmlDecode(String filename)  {  
		try { 
			List objList = new ArrayList();     
			BufferedInputStream file = new BufferedInputStream(new FileInputStream(filename));
			XMLDecoder decoder = new XMLDecoder(file); 
			objList.add(decoder.readObject());  
			objList.add(decoder.readObject());  
			objList.add(decoder.readObject()); 
			file.close(); 
			decoder.close();      
			return objList;  
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace();     
		} 
		return null;
	}
}