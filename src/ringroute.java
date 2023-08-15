import java.util.*;
public class ringroute {
	public static TwoTuple<ArrayList<Integer>, Double> road(double[][] connections,int nb_nodes,int[] initX){
		ArrayList<Integer> point = new ArrayList<Integer>();
		double Length=0;
		int startNode_nb;
		int endNode_nb;		
		for(int i=0;i<initX.length-1;i++)
		{
			startNode_nb=initX[i];
			endNode_nb=initX[i+1];
			TwoTuple<ArrayList<Integer>, Double> result = Dijkstra.dijkstra(connections,nb_nodes,startNode_nb,endNode_nb);
			ArrayList<Integer> OptPath = result.getLeft();
			double OptPathLength = result.getRight();
		    point.addAll(OptPath);	
		    Length=Length+OptPathLength;
		}
		startNode_nb=initX[initX.length-1];
		endNode_nb=initX[0];
		TwoTuple<ArrayList<Integer>, Double> result = Dijkstra.dijkstra(connections,nb_nodes,startNode_nb,endNode_nb);
		ArrayList<Integer> OptPath = result.getLeft();
		double OptPathLength = result.getRight();
	    point.addAll(OptPath);	
	    Length=Length+OptPathLength;
	    TwoTuple<ArrayList<Integer>, Double> answer = new TwoTuple<ArrayList<Integer>, Double>(point, Length);
		return answer;
	}

}
