import lejos.remote.ev3.RemoteEV3;

public class Autonomous extends Thread{
	private String name;
	private Thread thread;
	private static Autonomous instance;
	
	private Autonomous(){
		name = "AutoThread";
	}
	
	public static Autonomous init(){
		if(instance == null){
			instance = new Autonomous();
		}
		return instance;
	}
	
	@Override
	public void run(){
		//RemoteEV3 ev3 = ConntrollerCheck.init().getEv3();
		//here write your autonomous code

	}
	
	public void start(){
		if(thread == null){
			thread = new Thread(this, name);
			thread.start();
		}
	}
}
