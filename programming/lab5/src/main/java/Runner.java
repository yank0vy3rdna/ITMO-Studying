public class Runner implements Runnable{

	@Override
	public void run(){
		System.out.println("ya");
	}

	@Override
	public void run(float time){
		System.out.println("Ya begu" + time + "hui");
	}

}