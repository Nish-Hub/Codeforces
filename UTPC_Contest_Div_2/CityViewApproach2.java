/*
https://codeforces.com/gym/103562/problem/D
Problem Statement : 
We have a camera lens and a set of n points on the grid . We need to find the minimum angle such that all the n points can fit in the line of sight of the lens .
Current Approach :
If we find the angles of all the n points and then find the minimum and maximum angle , we can take the difference of these angles to get the required minimum angle of the Lens
to capture all the n points.
Addition : The earlier approach failed for cases where the counterclockwise angle was less than the clockwise angle . example : If i take the test case :
2
1 -1
1 1
Approach 1 says that we have counterclockwise min angle as - 45 ( 1,1) and max as 315 (1,-1) . And I was finding the difference of the counterclockwise angles . I missed out
that the clockwise angle is also a possibility i.e between 1,1 and 1,-1 . Way to find this is - find counter angle of y>=0 i.e for 1,1 , it will be 45 and for y<0 , find 
clockwise angle from +X - -45 . Now add their absolute values - 45+45 = 90 

So we have counter as - 270 , clockwise - 90 . Now compare both the calculations - u will get the minimum one i.e clockwise or counter clockwise

Again Failure  :) :
Getting a failure on codeforces "Wrong answer on test 5" . 
Possible Areas of failure and steps taken to rectify them :
1. Algorithm seems right to me  - Joined discord to verify
2. Maybe it is the output format expectation as given in the question : "The answer will be considered valid if the relative or absolute error doesn't exceed 10−6" - Have
used BigDecimal with precision but with not much luck . Have also used String.format but no luck
3. Overflow or Underflow in operations like "angle+=360.0d" or "max-min" - Have tried to used BigDecimal again here but not much difference
BigDecimal code is in the same package  : CityViewUsingBigDecimal.java
Currently i am trying to create the failing test case 5. Maybe need some help. Do not want the solution. 
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Solution {

	public static void main(String[] args) throws NumberFormatException,
			IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(
				System.out));
		int testCases = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
     //Solution is not space optimized. But for now i am focussing on the approach
		List<Double> quadrant1And2angleList = new ArrayList<>();
		List<Double> quadrant3And4ClockwiseangleList = new ArrayList<>();
		List<Double> quadrant3And4CounterClockwiseangleList = new ArrayList<>();
		List<Double> clockwiseangleList = new ArrayList<>();
		List<Double> counterClockwiseangleList = new ArrayList<>();
		
		for (int l = 0; l < testCases; l++) {
		
			String inputLine = br.readLine();
			String[] line = inputLine.trim().split(" ");

			Double x=Double.parseDouble(line[0]);
			Double y=Double.parseDouble(line[1]);
  
      // finding the counter angles for y>=0 
			if(y>=0) {
				quadrant1And2angleList.add(getCounterClockwiseAngle(x, y));
			}else {
      // finding both rotation angles for y<0 coordinates
				quadrant3And4CounterClockwiseangleList.add(getCounterClockwiseAngle(x, y));
				quadrant3And4ClockwiseangleList.add(getClockwiseAngle(x, y));
			}

		}
	
		// Case 1 : 
		clockwiseangleList.addAll(quadrant1And2angleList);
		clockwiseangleList.addAll(quadrant3And4ClockwiseangleList);
		
		// Case 2 : 
		counterClockwiseangleList.addAll(quadrant1And2angleList);
		counterClockwiseangleList.addAll(quadrant3And4CounterClockwiseangleList);
		
		BigDecimal clockwiseAngle=getDifferenceBetweenMaxAndMinAngle(clockwiseangleList, true);
		BigDecimal counterClockwiseAngle=getDifferenceBetweenMaxAndMinAngle(counterClockwiseangleList, false);
		
	  // we compare the difference of the angles now
		sb.append(clockwiseAngle.compareTo(counterClockwiseAngle)>=0?counterClockwiseAngle:clockwiseAngle);
		wr.write(sb.toString());
		br.close();
		wr.flush();

	}
	public static BigDecimal getDifferenceBetweenMaxAndMinAngle(List<Double> angleList,Boolean isClockwise){
		
		Collections.sort(angleList);
		Double max=angleList.get(angleList.size()-1);
		Double min=angleList.get(0);
		BigDecimal maxB=new BigDecimal(max);
		BigDecimal minB=new BigDecimal(min);
		// for clockwise , we need to add both
		if(isClockwise) {
			return maxB.abs().add(minB.abs());
		}  	
		// for counter clockwise , we need to add subtract
		return maxB.subtract(minB);
	}
	// this will give a negative angle which i will use to find min angle in the list
	public static double getClockwiseAngle(double x,double y) {
		
		double angle = Math.toDegrees(Math.atan2(y,x));
		
		return angle;
	}
	
	public static double getCounterClockwiseAngle(double x,double y) {
		
		double angle = Math.toDegrees(Math.atan2(y,x));
		
		if(angle<0)
			angle+=360.0d;
		
		return angle;
	}
}


