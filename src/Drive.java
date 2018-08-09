import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

public class Drive {
	private Port port1;
	private Port port2;
	private int dir1;
	private int dir2;
	private RegulatedMotor motor1;
	private RegulatedMotor motor2;
	
	public Drive(Port p1, Port p2, int d1, int d2){
		port1 = p1;
		port2 = p2;
		dir1 = d1;
		dir2 = d2;
		motor1 = new EV3LargeRegulatedMotor(p1);
		motor2 = new EV3LargeRegulatedMotor(p2);
	}
	
	public Drive(RegulatedMotor m1, RegulatedMotor m2, int d1, int d2){
		dir1 = d1;
		dir2 = d2;
		motor1 = m1;
		motor2 = m2;
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
		motor1.rotate((int)(rightMotorSpeed * 360 * dir1 / Math.abs(dir1)));
		motor2.rotate((int)(leftMotorSpeed * 360 * dir2 / Math.abs(dir2)));
	}
}
