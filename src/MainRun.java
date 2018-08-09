import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import lejos.remote.ev3.RemoteEV3;

public class MainRun {
	private RemoteEV3 ev3;
	private static MainRun instance;
	
	public static void main(String[] args) {

	}
	
	private MainRun(){
		
	}
	
	public static MainRun init(){
		if(instance == null){
			instance = new MainRun();
		}
		return instance;
	}
	
	public RemoteEV3 getEv3(){
		if(ev3 == null){
			try {
				ev3 = new RemoteEV3("BestEv3EVER");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ev3;
	}
}
