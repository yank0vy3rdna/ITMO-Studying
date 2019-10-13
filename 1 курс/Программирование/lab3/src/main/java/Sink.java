public class Sink extends Thing{
	Sink(String name){
		super(name);
		this.setType(Things.SINK);
		this.setStatusChange("смыл мыло");
	}
}