import java.util.*;

public class SA {
	public static ArrayList<Integer> simu_anneal (double[][] connections,int nb_nodes){
		Random random = new Random();
		// 定义初始温度、温度衰减系数
        double initT=100000;
        double decreaseRate = 0.9;
        double finalT=100;
        double currT=initT;
        
        int gen=0;

        // 生成初始解initX， 及该解对应的函数值initY(评价函数可以直接使用目标函数)
        int[] initX=new int[Cst.ring.length]; 
        for(int i=0;i<initX.length;i++)
        {
        	initX[i]=Cst.ring[i];
        }
        TwoTuple<ArrayList<Integer>, Double> answer = ringroute.road(connections,nb_nodes,initX);
        double initY = answer.getRight();

        // 初始解作为当前最优解
        int[] bestX = initX;
        double bestY = initY;
        
        // 初始解为当前解
        int[] currX = initX;
        double currY = initY;
       
        
        while(currT>finalT){
        	
            // 计算当前的迭代温度
            currT = currT*decreaseRate;

            for(int j=0; j<200; j++){ // 在当前温度下循环多次

                // 在当前最优解附近产生一个新解，并计算新解的函数值ֵ
            	int[] tmp=new int[currX.length];
            	for(int i=0;i<currX.length;i++)
                {
                	tmp[i]=currX[i];
                }
                int[] tmpX = getNewX.NewX(tmp);
//                int[] tmpX = getNewX.NewX(currX);
                TwoTuple<ArrayList<Integer>, Double> answers = ringroute.road(connections,nb_nodes,tmpX);
                double tmpY = answers.getRight();
                
                // 计算能量变化差值ֵ
                double Energy=tmpY-currY;

                // 评价新解和当前最优解，决定取舍
                if(Energy<=0){ // 新解更优，则接受新解为当前最优解
                    currY = tmpY;
                    currX = tmpX;
                }else {         // 新解更差，则以一定的概率接受新解为当前最优解
                    // 根据当前温度和两个解的差值生成接受概率
                    double p = Math.exp((bestY - tmpY)/currT);

                    // 生成一个均匀分布的概率
                    double randomP = random.nextDouble();
                    
                    // 比较两个概率，决定是否接受
                    if(p > randomP){
                        currY = tmpY;
                        currX = tmpX;
                    }
                }
                if(tmpY<=bestY){
                	bestX=tmpX;
                	bestY=tmpY;
                }
//                System.out.println(bestY);
//                for(int i=0;i<bestX.length;i++){
//                	System.out.print(bestX[i]+" ");
//                }
//                System.out.println();
            }
            gen++;
        }
        TwoTuple<ArrayList<Integer>, Double> finalanswers = ringroute.road(connections,nb_nodes,bestX);
        System.out.println("best solution is"+finalanswers.getLeft());
        System.out.println("best solution is"+finalanswers.getRight());
        return finalanswers.getLeft();
	}
}
