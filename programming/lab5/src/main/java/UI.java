import java.util.Scanner;
class UI {
	private final Controller cmd;
	private final String filename;
	public UI(Controller cmd, String filename){
		this.cmd = cmd;
		this.filename = filename;
	}
	public void start(){
		cmd.begin(filename);
		this.run();
		cmd.end(filename);
	}
	private void run(){
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