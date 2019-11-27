import java.util.Scanner;
class IActuallyDontKnowHowToNameThisClass{
	private final Commander cmd;
	public IActuallyDontKnowHowToNameThisClass(Commander cmd){
		this.cmd = cmd;
	}
	public void scan(){
		Scanner in = new Scanner(System.in);
		while(true){
			System.out.print("> ");
        	String line = in.nextLine();
        	if (line.equals("quit") || line.equals("exit")){
        		break;
        	}else{
        		cmd.execute(line);
        	}
		}
	}
}