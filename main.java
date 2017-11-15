/*
 Group members:
 Mujeeb Ur Rehman
 Hafiz Muhammad Taha khan
 BSCS 5A
 OS LAB 8 
 */
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.print("a. EDF\nb. RMS\nPlease select a scheduler:");
		String choice = s.next();
		
		if(choice.charAt(0)=='a') {
			EDF object = new EDF();
			object.EDF_Scheduler();
		}
		else {
			RMS object = new RMS();
			object.RM_Scheduler();
		}
	}

}
