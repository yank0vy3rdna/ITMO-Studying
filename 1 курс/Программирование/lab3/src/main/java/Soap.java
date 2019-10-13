public class Soap extends Thing{
	Soap(String name){
		super(name);
		this.setType(Things.SOAP);
		this.setStatusChange("намылил лицо");
	}
}