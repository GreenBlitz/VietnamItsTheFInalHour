
public class DriveThread extends Thread {
	private String name;
	private Thread thread;
	private float moveValue;
	private float rotateValue;
	private boolean isRunning;
	
	public DriveThread(String name, float m, float r){
		moveValue = m;
		isRunning = false;
		rotateValue = r;
		this.name = name;
	}
	
	@Override
	public void run(){
		isRunning = true;
		Drive.init().arcadeDrive(moveValue, rotateValue);
		isRunning = false;
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
	
	public void start(){
		if(thread == null){
			thread= new Thread(this, name);
			thread.start();
		}
	}

}
