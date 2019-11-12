abstract class APlace extends Entity{
    APlace(String name) throws NamingException {
        super(name);
    }
    public void action(String act){
        Logger.log("Произошло "+act+" в месте \""+this.getName()+"\"");
    }
    @Override
    public String toString() {
    	return "Место " + this.getName();
    }
}