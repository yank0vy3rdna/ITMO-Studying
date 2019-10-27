import java.util.ArrayList;
abstract class Entity{
	private ArrayList<Statement> statements = new ArrayList<Statement>();
	protected String name;
	Entity(String name){
		this.name = name;
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
        return obj.hashCode()== this.hashCode() ? true : false;
    }
    @Override
    public String toString() {
    	return "Сущность " + this.name;
    }
	public void addStatements(ArrayList<Statement> arr){
		this.statements.addAll(arr);
		for (int i = 0;i<arr.size();i++){
			System.out.println("Теперь у " + this.getName() + " " + arr.get(i).getName());
		}
	}
	public void removeStatements(ArrayList<Statement> arr){
		for (int i = 0; i > arr.size();i++){
			int a = this.statements.indexOf(arr.get(i));
			if (a != -1){
				this.statements.remove(a);
				System.out.println("Теперь у " + this.getName() + " не " + arr.get(i).getName());
			}
		}
	}
}