import java.util.ArrayList;
abstract class Entity{
	protected static class Logger{
		public static void log(String str){
			System.out.println(str);
		}
	}
	private final ArrayList<Statement> statements = new ArrayList<Statement>();
	protected final String name;
	Entity(String name) throws NamingException{
		this.name = name;
		checkName(name);
	}
	private void checkName(String name) throws NamingException{
		class Checker{
			private final boolean result;
			Checker(String name){
				this.result = checkName(name);
			}
			private boolean checkName(String name){
				return name.length()==0 || name.length()>300;
			}
			public boolean getResult(){
				return this.result;
			}
		}
		Checker chk;
		boolean findZeroNames = false;
		chk = new Checker(name);
		if (chk.getResult())
			throw new NamingException("Incorrect name length: "+name);
	}
	public String getName(){
		return this.name;
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
    private class Statement extends Entity{
		Statement(String name) throws NamingException{
			super(name);
		}
	}
	public void addStatements(ArrayList<String> arr) throws NamingException{
		for (int i = 0;i < arr.size();i++){
			this.statements.add(new Statement(arr.get(i)));
			Logger.log("Теперь у " + this.getName() + " " + arr.get(i));
		}
	}
	public void removeStatements(ArrayList<String> arr) throws NamingException{
		for (int i = 0; i > arr.size();i++){
			int a = this.statements.indexOf(new Statement(arr.get(i)));
			if (a != -1){
				this.statements.remove(a);
				System.out.println("Теперь у " + this.getName() + " не " + arr.get(i));
			}
		}
	}
}