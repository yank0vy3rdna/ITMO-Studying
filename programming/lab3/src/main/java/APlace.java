abstract class APlace extends Entity{
    APlace(String name) {
        super(name);
    }
    public void action(String act){
        System.out.println("Произошло "+act+" в месте \""+this.getName()+"\"");
    }
    @Override
    public String toString() {
    	return "Место " + this.getName();
    }
}