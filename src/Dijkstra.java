import java.util.ArrayList;
import java.util.Collections;

public class Dijkstra {
    public static TwoTuple<ArrayList<Integer>, Double> dijkstra(double[][] connections,int nb_nodes,int startNode_nb,int endNode_nb){
        ArrayList<Integer> Close = new ArrayList<Integer>();
        ArrayList<Integer> Open = new ArrayList<Integer>();
        ArrayList<Integer> OptPath = new ArrayList<Integer>();
        double OptPathLength;

        double[] dist = new double[nb_nodes];
        for(int i=0;i<dist.length;i++)
        {
            dist[i]=Integer.MAX_VALUE;
        }

        int[] pnode = new int[nb_nodes];
        for(int i=0;i<pnode.length;i++)
        {
            pnode[i]=-1;
        }
        dist[startNode_nb-1]=0;
        pnode[startNode_nb-1]=startNode_nb;
        Open.add(startNode_nb-1);        

        while(Open.size()!=0)
        {
            int N=swap.distmin(Open,dist);//N从0开始
            Close.add(N);
            Open.remove(Integer.valueOf(N));
            if(N==endNode_nb-1)
            {
                break;
            }
            ArrayList<Integer> Neigh = new ArrayList<Integer>();
            for(int i=0;i<connections[N].length;i++)
            {
                if(connections[N][i]!=-1)
                {
                    Neigh.add(i);
                }
            }
            for(int i=0;i<Neigh.size();i++)
            {
                int X=Neigh.get(i);//X从0开始
                if(Close.contains(X))
                    continue;
                double Length=dist[N]+connections[X][N];
                if(Open.contains(X)==false)
                {
                    dist[X]=Length;
                    pnode[X]=N;
                    Open.add(X);
                }
                else if(Open.contains(X)&&dist[X]>Length)
                {
                    dist[X]=Length;
                    pnode[X]=N;
                }
                else continue;
            }
        }
        OptPathLength=dist[endNode_nb-1];
        int findnode=endNode_nb-1;
        while(findnode!=startNode_nb-1)
        {
            OptPath.add(findnode+1);
            findnode=pnode[findnode];
        }
        OptPath.add(startNode_nb);
        Collections.reverse(OptPath);
        TwoTuple<ArrayList<Integer>, Double> result = new TwoTuple<ArrayList<Integer>, Double>(OptPath, OptPathLength);
        return result;
    } 
}
