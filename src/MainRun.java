import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.RegulatedMotor;

public class MainRun {
	private RemoteEV3 ev3;
	private String ev3ip;
	private static MainRun instance;
	private int dirRight;
	private int dirLeft;
	private RMIRegulatedMotor rightMotor;
	private RMIRegulatedMotor leftMotor;
	private String portRight;
	private String portLeft;
	
	public static void main(String[] args) {
		MainRun.init();
		ConntrollerCheck check = ConntrollerCheck.init();
		check.start();
	}
	
	private MainRun(){
		ev3ip = "10.0.1.1"; //TODO ip of the ev3
		try {
			ev3 = new RemoteEV3(ev3ip);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		portRight = "B";
		portLeft = "A";
		rightMotor = ev3.createRegulatedMotor(portRight, 'M');
		leftMotor = ev3.createRegulatedMotor(portLeft, 'M');
		dirRight = -1; // TODO direction of the motor
		dirLeft = -1; //TODO direction of the motor
	}
	
	public static MainRun init(){
		if(instance == null){
			instance = new MainRun();
		}
		return instance;
	}
	
	public RMIRegulatedMotor getRightMotor(){
		return rightMotor;
		
	}
	
	public RMIRegulatedMotor getLeftMotor(){
		return leftMotor;
		
	}
	
	public RemoteEV3 getEv3(){
		return ev3;
	}
	
	public int getRightDir(){
		return dirRight;
	}
	public int getLeftDir(){
		return dirLeft;
	}
	
}
