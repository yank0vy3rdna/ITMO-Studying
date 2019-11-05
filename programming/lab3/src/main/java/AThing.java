import java.util.ArrayList;
abstract class AThing extends Entity{
	private ArrayList<Human> clientsArr = new ArrayList<Human>();
	private Things type;
	private ArrayList<Statement> onSuccessfullUsingAdd = new ArrayList<Statement>();
	private ArrayList<Statement> onSuccessfullUsingRm = new ArrayList<Statement>();
	public void setonSuccessfullUsingAdd(ArrayList<Statement> arr){
		this.onSuccessfullUsingAdd=arr;
	}
	public void setonSuccessfullUsingRm(ArrayList<Statement> arr){
		this.onSuccessfullUsingRm=arr;
	}
	public ArrayList<Statement> getonSuccessfullUsingAdd(){
		return onSuccessfullUsingAdd;
	}
	public ArrayList<Statement> getonSuccessfullUsingRm(){
		return onSuccessfullUsingRm;
	}
	public void addClient(Human client){
		clientsArr.add(client);
	}
	public void rmClient(Human client){
		clientsArr.remove(client);
	}
	public boolean checkClient(Human client){
		return clientsArr.contains(client);
	}
	private boolean status = false;
	public boolean getStatus(){
		return this.status;
	}
	public void setType(Things type){
		this.type = type;
	}
	public Things getType(){
		return this.type;
	}
	public void setStatus(boolean status){
		this.status = status;
	}
	AThing(String name){
		super(name);
	}

    @Override
    public String toString() {
    	return this.name + " типа : " + this.getType().toString();
    }
}