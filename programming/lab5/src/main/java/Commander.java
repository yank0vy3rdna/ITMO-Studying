class Commander{
	private final CollectionManager manager;
	Commander(CollectionManager manager){
		this.manager = manager;
	}
	public void execute(String command){
		String[] operands = command.split(" ",2);
		boolean beginned = false;
		int count = 0;
		for (String i : operands){
			int begin = i.length() - i.replace("{", "").length();
			int end = i.length() - i.replace("}", "").length();
			count += begin - end;
			boolean lb = beginned;
		}
		switch(operands[0]){
			case "insert":
				String[] params = operands[1].split(" ",2);
				manager.insertJSON(Integer.parseInt(params[0]),params[1]);
				break;
			case "info":
				manager.printInfo();
				break;
			case "import":
				manager.importAnotherXML(operands[1]);
				break;
			case "show":
				manager.show();
				break;
			case "help":
				System.out.println("insert %key% %jsoncode%");
				System.out.println("remove %key%");
				System.out.println("remove greater %jsoncode%");
				System.out.println("remove greater key %key%");
				System.out.println("show");
				System.out.println("info");
				System.out.println("help");
				System.out.println("import %filename%");
				break;
			case "remove":
				params = operands[1].split(" ",2);
				if(params[0].equals("greater")){
					if (params[1].split(" ")[0].equals("key")){
						params = operands[1].split(" ",3);
						manager.removeGreaterKey(Integer.parseInt(params[2]));
					}else{
						manager.removeGreater(params[1]);
					}
				}else{
					manager.remove(Integer.parseInt(operands[1]));
				}
				break;
		}
	}
}