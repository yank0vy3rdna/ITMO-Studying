class Controller {
	private final Domain manager;
	Controller(Domain manager){
		this.manager = manager;
	}
	public void begin(String filename){
		manager.initWithXML(filename);
	}
	public void end(String filename){
		manager.saveXML(filename);
	}
	public void execute(String command){
		String[] operands = command.split(" ",2);
		switch(operands[0]){
			case "insert":
				String[] params = operands[1].split(" ",2);
				manager.insertJSON(Integer.parseInt(params[0]),params[1]);
				break;
			case "info":
				manager.printInfo();
				break;
			case "import":
				manager.importXML(operands[1]);
				break;
			case "show":
				manager.show();
				break;
			case "help":
				manager.printHelp();
				break;
			case "remove":
				manager.removeParser(command);
				break;
		}
	}
}