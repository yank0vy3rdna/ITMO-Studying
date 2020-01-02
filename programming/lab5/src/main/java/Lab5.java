class Lab5 {
	public static void main(String[] args){
		String filename = "data.xml";
		if(args.length!=0){
			filename = args[0];
		}
		UI scn = new UI(new Controller(new Domain(new Storage())),filename);
		scn.start();
	}
}