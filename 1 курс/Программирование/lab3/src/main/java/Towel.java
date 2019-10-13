public class Towel extends Thing{
	Towel(String name){
		super(name);
		this.setType(Things.TOWEL);
		this.setStatusChange("вытер воду с лица");
	}
}