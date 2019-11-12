import java.util.ArrayList;
abstract class AThing extends Entity{
	private ArrayList<IHuman> clientsArr = new ArrayList<IHuman>();
	private Things type;
	private ArrayList<String> onSuccessfullUsingAdd = new ArrayList<String>();
	private ArrayList<String> onSuccessfullUsingRm = new ArrayList<String>();
	public void setonSuccessfullUsingAdd(ArrayList<String> arr){
		this.onSuccessfullUsingAdd=arr;
	}
	public void setonSuccessfullUsingRm(ArrayList<String> arr){
		this.onSuccessfullUsingRm=arr;
	}
	public ArrayList<String> getonSuccessfullUsingAdd(){
		return onSuccessfullUsingAdd;
	}
	public ArrayList<String> getonSuccessfullUsingRm(){
		return onSuccessfullUsingRm;
	}
	public void addClient(IHuman client){
		clientsArr.add(client);
	}
	public void rmClient(IHuman client){
		clientsArr.remove(client);
	}
	public boolean checkClient(IHuman client){
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
	AThing(String name) throws NamingException{
		super(name);
	}

    @Override
    public String toString() {
    	return this.name + " типа : " + this.getType().toString();
    }
}