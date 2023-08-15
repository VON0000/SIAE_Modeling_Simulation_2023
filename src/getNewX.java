import java.util.*;

public class getNewX {
	public static int[] NewX(int[] bestX){
		Random random = new Random();
		int rand1= random.nextInt(bestX.length);
		int rand2= random.nextInt(bestX.length);
		int temp;
		if (rand1>rand2)
		{
			temp=rand1;
			rand1=rand2;
			rand2=temp;
		}
		for(int i=0;i<Math.ceil((rand2-rand1)/2);i++)
		{
			int temp2=bestX[rand1+i];
			bestX[rand1+i]=bestX[rand2-i];
			bestX[rand2-i]=temp2;
		}
		return bestX;
	}

}
