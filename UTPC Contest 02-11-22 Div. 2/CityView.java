/*
https://codeforces.com/gym/103562/problem/D
Problem Statement : 
We have a camera lens and a set of n points on the grid . We need to find the minimum angle such that all the n points can fit in the line of sight of the lens .

Current Approach :
If we find the angles of all the n points and then find the minimum and maximum angle , we can take the difference of these angles to get the required minimum angle of the Lens
to capture all the n points

Failure :
Getting a failure on codeforces "Wrong answer on test 5" . 

Possible Areas of failure and steps taken to rectify them :
1. Algorithm seems right to me  - Joined discord to verify
2. Maybe it is the output format expectation as given in the question : "The answer will be considered valid if the relative or absolute error doesn't exceed 10−6" - Have
used BigDecimal with precision but with not much luck . Have also used String.format but no luck
3. Overflow or Underflow in operations like "angle+=360.0d" or "max-min" - Have tried to used BigDecimal again here but not much difference

Currently i am trying to create the failing test case 5. Maybe need some help. Do not want the solution. 
*/


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

	public static void main(String[] args) throws NumberFormatException,
			IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCases = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
    
    // A list to store the angle of each special point in the list    
		List<Double> angleList = new ArrayList<>();
		for (int l = 0; l < testCases; l++) {

			String inputLine = br.readLine();
			String[] line = inputLine.trim().split(" ");
      //Reading the special points 
			Double x=Double.parseDouble(line[0]);
			Double y=Double.parseDouble(line[1]);
      
      // Getting the angle of the point with reference to +X axis 
			Double angle = getAngle(x,y);
			angleList.add(angle);
		}
		;
     // Sorting the angle list to get the max angle and min angle which will give us the Minimum angle of the lens to contain all the special points in one shot
		Collections.sort(angleList);
		Double max=angleList.get(angleList.size()-1);
		Double min=angleList.get(0);
		
		sb.append((max-min));
		wr.write(sb.toString());
		br.close();
		wr.flush();

	}
	public static double getAngle(double x,double y) {
		// Using atan2 to find the angle of the point x,y
		double angle = Math.toDegrees(Math.atan2(y,x));
    // In case of angles which are negative i.e counterclockwise , find the clockwise angle by adding 360
		if(angle<0)
			angle+=360.0d;
		
		return angle;
	}
}
