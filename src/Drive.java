import java.rmi.RemoteException;

import lejos.hardware.port.Port;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.RegulatedMotor;

public class Drive {
	private int dir1;
	private int dir2;
	private static Drive instance;
	
	public static Drive init(){
		if(instance == null){
			instance = new Drive();
		}
		return instance;
	}
	
	private Drive(){

		dir1 = MainRun.init().getRightDir();
		dir2 = MainRun.init().getLeftDir();
	}
	
	public void arcadeDrive(float moveValue, float rotateValue){
		
		moveValue = moveValue*moveValue*(moveValue/Math.abs(moveValue));
		rotateValue = rotateValue*rotateValue*(rotateValue/Math.abs(rotateValue));
		
		double leftMotorSpeed = 0;
		double rightMotorSpeed = 0;
		
		if (moveValue > 0.0) {
			if (rotateValue > 0.0) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			} else {
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
		        rightMotorSpeed = moveValue + rotateValue;
		    }
		 } else {
			 if (rotateValue > 0.0) {
				 leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				 rightMotorSpeed = moveValue + rotateValue;
			 } else {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			 }
		 }
		
		try{
			if(leftMotorSpeed > 0)
				MainRun.init().getLeftMotor().forward();
			else
				MainRun.init().getLeftMotor().backward();
			
			if(rightMotorSpeed > 0)
				MainRun.init().getRightMotor().forward();
			else
				MainRun.init().getRightMotor().backward();
			
			MainRun.init().getRightMotor().setSpeed((int)(rightMotorSpeed * 360*3 * dir1 / Math.abs(dir1)));
			MainRun.init().getLeftMotor().setSpeed((int)(leftMotorSpeed * 360*3 * dir2 / Math.abs(dir2)));
		}catch(RemoteException x){
			x.printStackTrace();
		}
	}
}
