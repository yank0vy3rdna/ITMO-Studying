abstract class Entity{
	private ArrayList<Statement> statements = new ArrayList<Statement>();
	private String name;
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
	}
}