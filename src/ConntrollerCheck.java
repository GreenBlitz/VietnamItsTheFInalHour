import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import lejos.remote.ev3.RemoteEV3;

public class ConntrollerCheck extends Thread {
	private String name;
	private Thread thread;
	private static ConntrollerCheck instance;
	private boolean[] lastButtons;
	private boolean[] buttons;
	private boolean working;
	private float[] axis;
	private ControllerBoy getter;
	private ButtonThread[] buttonsThreads;
	private RemoteEV3 ev3;
	
	private ConntrollerCheck(){
		ev3 = MainRun.init().getEv3();
		getter = ControllerBoy.init();
		name = "MainRunThread";
		working = true;
		lastButtons = new boolean[18];
		buttons = new boolean[18];
		axis = new float[6];
		buttonsThreads = new ButtonThread[11];
		for(int i = 0; i< buttons.length; i++){
			lastButtons[i] = false;
			buttons[i] = false;
		}
		for(int i = 0; i< axis.length; i++)
			axis[i] = 0.0f;
	}
	
	public static ConntrollerCheck init(){
		if(instance == null){
			instance = new ConntrollerCheck();
		}
		return instance;
	}
	
	@Override
	public void run(){
		while(working){
			checkButtons();
			checkAxis();
		}
	}
	
	public void kill(){
		working = false;
	}
	
	public void checkButtons(){
		buttons = getter.getButtons();
		for(int i = 0; i< buttons.length; i++){
			if(buttons[i]&& !lastButtons[i]){
				int x = i;
				if(i > 9)
					x = 10;
				if(buttonsThreads[x] == null){
					buttonsThreads[x] = new ButtonThread(i);
					buttonsThreads[x].start();
				}else if(!buttonsThreads[x].isRunning()){
					buttonsThreads[x] = new ButtonThread(i);
					buttonsThreads[x].start();
				}
				lastButtons[i] = true;
			}else{
				lastButtons[i] = buttons[i];
			}
		}
	}
	
	public void checkAxis(){
		
	}
	
	public void start(){
		if(thread == null){
			thread = new Thread(this, name);
			thread.start();
		}
	}
}
