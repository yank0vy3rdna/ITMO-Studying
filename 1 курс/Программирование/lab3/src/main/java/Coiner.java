public class Coiner extends Thing{
	Coiner(String name){
		super(name);
		this.setType(Things.TOWEL);
		this.setStatusChange("оплатил");
	}
}