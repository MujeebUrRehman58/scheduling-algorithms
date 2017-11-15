import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math.*;

public class RMS {
	private int n, current_proc = 0;	// no. of processes 
	private ArrayList<Integer> proc_id = new ArrayList<Integer>();
	private ArrayList<Integer> exe_time = new ArrayList<Integer>();
	private ArrayList<Integer> period = new ArrayList<Integer>();
	private ArrayList<Integer> rem_exe_time = new ArrayList<Integer>();
	//private ArrayList<Integer> wait_time = new ArrayList<Integer>();
	RMS(){
		Scanner s = new Scanner(System.in);
		System.out.print("Enter num of processes: ");
		n = s.nextInt();
		int y;
		for(int i = 0; i < n; i++) {
			proc_id.add(i+1);
			y=i+1;
			System.out.print("Enter burst time for proc "+ y +": ");
			exe_time.add(s.nextInt());
			System.out.print("Enter period: ");
			period.add(s.nextInt());
		}
	}
	
	private void sort() {
		int i, j, p_key,id_key, b_key; 	// period key, proc_id key and burst time key

		System.out.println("Before Sorting.\nproc_id\t\tburst time\tperiod.");
		for(i = 0; i < n; i++) 
			System.out.println(proc_id.get(i)+"\t\t"+exe_time.get(i)+"\t\t"+period.get(i));
		
		for (j = 1; j < n; j++) {  
            p_key = period.get(j);
            id_key = proc_id.get(j);
            b_key = exe_time.get(j);
            i = j-1;  
            while ( (i > -1) && ( period.get(i) > p_key ) ) {  
                period.set(i+1, period.get(i));
                proc_id.set(i+1, proc_id.get(i));
                exe_time.set(i+1, exe_time.get(i));
                i--;  
            }  
            period.set(i+1, p_key);
            proc_id.set(i+1, id_key);
            exe_time.set(i+1, b_key);
        }
		
		//populating remaining burst time list
		for(i = 0; i < n; i++)
			rem_exe_time.add(exe_time.get(i));
	
		System.out.println("After Sorting.\nproc_id\t\tburst time\tperiod.");
		for(i = 0; i < n; i++) 
			System.out.println(proc_id.get(i)+"\t\t"+exe_time.get(i)+"\t\t"+period.get(i));
	
	}
	
	private int gcd(int num1, int num2)
	{
		int temp;
	    while (num2 > 0)
	    {
	        temp = num2;
	        num2 = num1 % num2; 
	        num1 = temp;
	    }
	    return num1;
	}

	
	private int lcm(int num1, int num2)
	{
	    return num1 * (num2 / gcd(num1, num2));
	}

	private int lcm(ArrayList<Integer>input)
	{
	    int result = input.get(0);
	    for(int i = 1; i < n; i++) 
	    	result = lcm(result, input.get(i));
	    return result;
	}
	private boolean utilization() {
		double u_factor,d_factor = 0;
		for(int i = 0; i < n; i++) 
			d_factor +=((double)exe_time.get(i)/period.get(i)); 
		
		u_factor = n*(Math.pow(2.0,1/(double)n)-1);
		if (u_factor < d_factor) return true;
		else return false;
	}
	public void RM_Scheduler() {
		
		if(utilization()) { 	//	if u_factor < d_factor
			sort();
			// sorted list is being used...
			int j;
			for(int i = 0; i < lcm(period); i++) {
				
				for(j = 0; j < n; j++) {
					if(i % period.get(j) == 0 && i>0) {
						if(rem_exe_time.get(j) > 0) 
							System.out.println("Process with ID "+proc_id.get(j)+" has missed deadline.");
						rem_exe_time.set(j, exe_time.get(j));
						System.out.println("iteration "+i+". Period of process with ID "+proc_id.get(j)+" arrived.");
					}
				}
				
				for(j = 0; j < n; j++) {
					if(rem_exe_time.get(j) != 0) {
						current_proc = j;
						break;
					}
				}
				
				if(i>0) {
					System.out.println("iteration "+i+". Current process ID is "+proc_id.get(current_proc)+".");
					if(rem_exe_time.get(current_proc) > 0)
						rem_exe_time.set(current_proc,rem_exe_time.get(current_proc)-1);
				}
			}
		}
		else 
			System.out.println("Not schedulable....");
	}
}
