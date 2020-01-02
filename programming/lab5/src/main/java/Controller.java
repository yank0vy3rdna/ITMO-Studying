
/**
 * Controller
 * <ul>
 * <li>import {filename} - добавить в коллекцию все элементы из файла</li>
 * <li>info - вывести информацию о коллекции</li>
 * <li>insert {key} {json} - добавить новый элемент с заданным ключом</li>
 * <li>show - вывести все элементы коллекции в строковом представлении в консоль</li>
 * <li>remove {key} - удалить элемент коллекции по его ключу</li>
 * <li>remove greater {json} - удалить элементы коллекции, превышающие заданный</li>
 * <li>remove greater key {key} - удалить элементы коллекции, ключ которых превышает данный</li>
 * </ul>
 */
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

	/**
	 *
	 * метод, выполняющий различные действия в зависимости от введенной команды
	 * @param command
	 * @return output
	 */
	public String execute(String command){
		String[] operands = command.split(" ",2);
		switch(operands[0]){
			case "insert":
				String[] params = operands[1].split(" ",2);
				manager.insertJSON(Integer.parseInt(params[0]),params[1]);
				break;
			case "info":
				return manager.printInfo();
			case "import":
				manager.importXML(operands[1]);
				break;
			case "show":
				return manager.show();
			case "help":
				return manager.printHelp();
			case "remove":
				manager.removeParser(command);
				break;
		}
		return "";
	}
}