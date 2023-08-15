import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
	
	public static void main(String[] args) throws Exception { 
		
		long start=System.currentTimeMillis();
		List<int[]> nodes = new ArrayList<int[]>();
		nodes = ReadFile.readFile(Cst.node_fileName);
//		for(int i=0;i<nodes.size();i++)
//		{
//			System.out.print(nodes.get(i)[0]+" "+nodes.get(i)[1]+" "+nodes.get(i)[2]+"\n");
//		}
		
		List<int[]> edges = new ArrayList<int[]>();
		edges = ReadFile.readFile(Cst.edge_fileName);
//		for(int i=0;i<edges.size();i++)
//		{
//			System.out.print(edges.get(i)[0]+" "+edges.get(i)[1]+" "+edges.get(i)[2]+"\n");
//		}
		Map<Integer, List<Integer>> edgesMap= new HashMap<Integer, List<Integer>>();
		edgesMap = ReadFile.buildGraph();
		int nb_nodes=nodes.size();
	    double[][] connections=new double[nodes.size()][nodes.size()];
	    		for(int i=0;i<connections.length;i++)
	    		{
	    			for(int j=0;j<connections[i].length;j++)
	    			{
	    				connections[i][j]=-1;
	    			}
	    		}
	    for(int i=0;i<edges.size();i++)
	    {
	    	int m=edges.get(i)[1];
	    	int n=edges.get(i)[2];
	    	connections[m-1][n-1]=Math.sqrt(Math.pow(nodes.get(m-1)[1]-nodes.get(n-1)[1],2)+Math.pow(nodes.get(m-1)[2]-nodes.get(n-1)[2],2));
	    	connections[n-1][m-1]=Math.sqrt(Math.pow(nodes.get(m-1)[1]-nodes.get(n-1)[1],2)+Math.pow(nodes.get(m-1)[2]-nodes.get(n-1)[2],2));
	    }
//	    for (int i=0;i<connections.length;i++)
//	    {
//	    	for (int j=0;j<connections[i].length;j++)
//	    	{
//	    		System.out.print(connections[i][j]+" ");
//	    	}
//	    	System.out.println();
//	    }
	    
//	    int[] ceshiX={204,57,158,217,65,221,175};
//	    TwoTuple<ArrayList<Integer>, Double> answers = ringroute.road(connections,nb_nodes,ceshiX);
//	    ArrayList<Integer> CS =new ArrayList<Integer>();
//	    CS=answers.getLeft();
//	    DrawGraph.draw(edgesMap, nodes,connections,CS);
//	    System.out.print(answers.getRight());
	    
//	    DrawGraph.draw(edgesMap, nodes,connections);
	    
	    ArrayList<Integer> road =new ArrayList<Integer>();
	    road=SA.simu_anneal(connections, nb_nodes);
	    DrawGraph.draw(edgesMap, nodes,connections,road);
	    long end=System.currentTimeMillis();
	    System.out.println("当前程序运行多少毫秒:" + "=" + (end-start));
	}
 
}


