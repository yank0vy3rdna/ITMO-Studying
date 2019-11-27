import java.util.ArrayList;
import java.io.Serializable;
abstract class Entity {
	protected static class Logger{
		public static void log(String str){
			System.out.println(str);
		}
	}
	protected String name;
	Entity(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	@Override
    public int hashCode() {
        return super.hashCode()+this.name.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() != this.hashCode() ? false : true;
    }
    @Override
    public String toString() {
    	return "Сущность " + this.name;
    }
}