import java.util.ArrayList;
abstract class AThing extends Entity{
	private ArrayList<Human> clientsArr = new ArrayList<Human>();
	private Things type;
	private String statusChange;
	public void setType(Things type){
		this.type = type;
	}
	public Things getType(){
		return this.type;
	}
	public String getStatusChange(){
		return this.statusChange;
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
	private void setStatus(boolean status){
		this.status = status;
	}
	public void setStatusChange(String status){
		this.statusChange = status;
	}
	AThing(String name){
		super(name);
	}

    @Override
    public String toString() {
    	return this.name + " типа : " + this.getType().toString();
    }
}