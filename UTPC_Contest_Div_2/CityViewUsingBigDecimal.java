//package prob3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

	public static void main(String[] args) throws NumberFormatException,
			IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(
				System.out));
		int testCases = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		List<BigDecimal> angleList = new ArrayList<>();
		for (int l = 0; l < testCases; l++) {

			String inputLine = br.readLine();
			String[] line = inputLine.trim().split(" ");

			Double x=Double.parseDouble(line[0]);
			Double y=Double.parseDouble(line[1]);

			BigDecimal angle = getAngle2(x,y);
			angleList.add(angle);
		}
		;
		Collections.sort(angleList);
		BigDecimal maxB=angleList.get(angleList.size()-1);
		BigDecimal minB=angleList.get(0);
		
		sb.append(maxB.subtract(minB));
		wr.write(sb.toString());
		br.close();
		wr.flush();

	}
	public static BigDecimal getAngle2(Double x,Double y) {
		
		Double angle = Math.toDegrees(Math.atan2(y,x));
		BigDecimal angleB=new BigDecimal(angle);
		
		if(angle<0)
			//angle+=360.0d;
			angleB=	angleB.add(new BigDecimal(360.0d));
		
		return angleB;
	}
	
	public static BigDecimal getAngle(Double x,Double y) {
		BigDecimal xB=new BigDecimal(x);
		BigDecimal yB=new BigDecimal(y);
		//System.out.println(yB.divide(xB,10,RoundingMode.HALF_UP));
		
		//Double angle = Math.toDegrees(Math.atan((yB.divide(xB,10,RoundingMode.HALF_UP)).doubleValue()));
		Double angle = Math.toDegrees(Math.atan2(yB.doubleValue(),xB.doubleValue()));
		//Double angle = Math.toDegrees(Math.atan2(y,x));
		BigDecimal angleB=new BigDecimal(angle);
		
		if(angle<0)
			//angle+=360.0d;
			angleB.add(new BigDecimal(360.0d));
		
		return angleB;
	}
}


