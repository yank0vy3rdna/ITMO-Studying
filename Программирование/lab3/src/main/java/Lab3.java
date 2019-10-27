//Решив на ночь умыться, Козлик подошел к рукомойнику, но и тут пришлось израсходовать сантик на воду, сантик на мыло и сантик на полотенце. Вслед за Козликом начал умываться Незнайка. Но едва он намылил лицо, как что-то щелкнуло и вода перестала течь. Незнайка вертел кран то в одну сторону, то в другую, стучал по нему кулаком, но это не помогало. Мыло невыносимо щипало ему глаза, а смыть было нечем. Тогда Незнайка стал звать на помощь Козлика. Видя неладное, Козлик подбежал к крану, но как раз в это время погас свет и комната снова погрузилась во мрак. Единственное, что можно было разглядеть в темноте, это настойчиво мигавший красный глазок на стене и поблескивавший под ним металлический язычок
class Lab3 {
	public static void main(String[] args){
		Room room = new Room("комната");
		Human dunno = new Human("Незнайка");
		Human goat = new Human("Козлик");
		goat.decision("умыться на ночь");
		room.paymentThing(goat, Things.SOAP,1);
		room.paymentThing(goat, Things.SINK,1);
		room.paymentThing(goat, Things.TOWEL,1);
		room.useThing(goat,Things.SOAP);
		room.useThing(goat,Things.SINK);
		room.useThing(goat,Things.TOWEL);
		dunno.action("начал умываться");
		room.paymentThing(dunno, Things.SOAP,1);
		room.useThing(dunno,Things.SOAP);
		room.useThing(dunno,Things.SINK);
		room.action("щелк");
		room.action("вода перестала течь");
		dunno.action("вертит кран в разные стороны");
		dunno.action("стучит по рукомойнику кулаком");
		dunno.action("чувствует боль от мыла в глазах");
		dunno.speak("Козлик, иди сюда, бачок потiк");
		goat.action("видит неладное");
		goat.action("подошел к крану");
		room.action("отключение света");
		room.letCoinerBlink();
	}
}