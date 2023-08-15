import java.util.ArrayList;

public class swap{
	public static int distmin(ArrayList<Integer> Open,double[] dist)
	{
//		ArrayList<Integer> Open = new ArrayList<Integer>();
//		for(int i=0;i<5;i++)
//		{
//			Open.add(i);
//		}
//		double[] dist = new double[5];
//		dist[0]=3;
//		dist[1]=5;
//		dist[2]=2;
//		dist[3]=1;
//		dist[4]=4;
		int n=Open.size();
		int minvalue=Open.get(0);
		for(int i=0;i<n;i++)
		{
			int choosevalue=Open.get(i);
			if(dist[minvalue]>dist[choosevalue])
			{
				minvalue=choosevalue;
			}
		}
		return minvalue;
	}
}
