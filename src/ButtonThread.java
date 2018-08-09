
public class ButtonThread extends Thread{
	private String name;
	private Thread thread;
	private boolean state;
	private int button;
	private boolean isRunning;
	
	public ButtonThread(int button){
		this.button = button;
		isRunning = false;
		name = "ButtonThread"+button;
	}

	@Override
	public void run(){
		isRunning = true;
		System.out.println("Button function "+button+" have been called");
		switch(button){
			case 0:
				ButtonCall.buttonA();
				break;
			case 1:
				ButtonCall.buttonB();
				break;
			case 2:
				ButtonCall.buttonX();
				break;
			case 3:
				ButtonCall.buttonY();
				break;
			case 4:
				ButtonCall.buttonLB();
				break;
			case 5:
				ButtonCall.buttonRB();
				break;
			case 6:
				ButtonCall.buttonBack();
				break;
			case 7:
				ButtonCall.buttonStart();
				break;
			case 8:
				ButtonCall.buttonLeftAxis();
				break;
			case 9:
				ButtonCall.buttonRightAxis();
				break;
			case 10:
				ButtonCall.povLeftUp();
				break;
			case 11:
				ButtonCall.povUp();
				break;
			case 12:
				ButtonCall.povRightUp();
				break;
			case 13:
				ButtonCall.povRight();
				break;
			case 14:
				ButtonCall.povRightDown();
				break;
			case 15:
				ButtonCall.povDown();
				break;
			case 16:
				ButtonCall.povLeftDown();
				break;
			case 17:
				ButtonCall.povLeft();
				break;
		}
		isRunning = false;
	}
	
	public void start(){
		if(thread == null){
			thread = new Thread(this, name);
			thread.start();
		}
	}
	
	public boolean isRunning(){
		return isRunning;
	}
}
