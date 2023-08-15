import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.text.DecimalFormat;
public class DrawGraph {
	public static void draw(Map<Integer, List<Integer>> edges, List<int[]> nodes,double[][] connections,ArrayList<Integer>OptPath) throws IOException {
		Plot2DPanel plot = new Plot2DPanel();
		for(Entry<Integer, List<Integer>> entry : edges.entrySet()) {
			Integer key = entry.getKey();
			int count = 0;//count the number of values for each key
			List<Integer> values = entry.getValue();
			for(int k=0; k<values.size(); k++){
				double[] p1 = new double[2];
				double[] p2 = new double[2];
				p1[0] = (double)nodes.get(key-1)[1];
				p1[1] = (double)nodes.get(key-1)[2];
				p2[0] = (double)nodes.get(values.get(k)-1)[1];
				p2[1] = (double)nodes.get(values.get(k)-1)[2];
				plot.addLinePlot("my plot", Color.blue, p1, p2);
//				double[] t = new double[2];
//				t[0] = (p1[0]+p2[0])/2;
//				t[1] = (p1[1]+p2[1])/2;
//				DecimalFormat df = new DecimalFormat("#.0");//设置保留两位小数
//				double tmp=connections[key-1][values.get(k)-1];
//				String S = df.format(tmp);
//				plot.addLabel(S, Color.red, t);
//				System.out.println("p1 = "+p1[0]+", "+p1[1]+", p2 = "+p2[0]+", "+p2[1]);
				count++;
				}
			}
		for(int i=0;i<OptPath.size()-1;i++)
		{
			double[] p1 = new double[2];
			double[] p2 = new double[2];
			int a = (int)OptPath.get(i);
			int b = (int)OptPath.get(i+1);
			p1[0] = (double)nodes.get(a-1)[1];
			p1[1] = (double)nodes.get(a-1)[2];
			p2[0] = (double)nodes.get(b-1)[1];
			p2[1] = (double)nodes.get(b-1)[2];
			plot.addLinePlot("my plot", Color.red, p1, p2);
		}
		//show the index of each node
//		for (int i = 0; i < nodes.size(); i++) {
//			double[] s = new double[2];
//			s[0] = nodes.get(i)[1]+0.5;// getKey的横坐标
//			s[1] = nodes.get(i)[2]+0.5; // 与getKey纵坐标
//			plot.addLabel(i + 1 + "", Color.BLACK, s);
//		}
		//determine the boundary of the figure
		double xmin = boundMin_x(nodes);
		double ymin = boundMin_y(nodes);
		double xmax = boundMax_x(nodes);
		double ymax = boundMax_y(nodes);
		double boundMin[]  = {xmin,ymin};
        double boundMax[]  = {xmax,ymax};
        plot.setFixedBounds(boundMin, boundMax);	

		JFrame frame = new JFrame();
		frame.setContentPane(plot);// 平台
		frame.setBounds(0, 0, 1200, 1200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭方法
	}
	
	
	public static int boundMin_x(List<int[]> list) {

		int x_min = list.get(1)[1];
		  for(int i=0; i<list.size(); i++){//在i小于list长度时循环
	        if(x_min > list.get(i)[1]){//如果第i个数据的横坐标大于max
	    	  x_min = list.get(i)[1];
	        }
		    }
//	        System.out.println("x_min = "+x_min);
		return x_min;
	    }
	
	public static int boundMin_y(List<int[]> list) {

		int y_min = list.get(1)[2];//将第一个数据设置为最大、最小值ֵ
		for(int i=0; i<list.size(); i++){
		      if(y_min > list.get(i)[2]){
		    	  y_min  = list.get(i)[2];
		       }
			   }
//		System.out.println("y_min = "+y_min);
		return y_min;
	}
	
	
	public static int boundMax_x(List<int[]> list) {

		int x_max = list.get(1)[1];
	
		for(int i=0; i<list.size(); i++){//在i小于list长度时循环
	      if(x_max < list.get(i)[1]){//如果第i个数据的横坐标大于max
		     x_max = list.get(i)[1];
	       }
		   }
//		System.out.println("x_max = "+x_max);
		return x_max;
        }

	public static int boundMax_y(List<int[]> list) {

		int y_max = list.get(1)[2];//将第一个数据设置为最大、最小值ֵ
		for(int i=0; i<list.size(); i++){
		      if(y_max < list.get(i)[2]){
			     y_max = list.get(i)[2];
		       }
			   }
//		System.out.println("y_max = "+y_max);
		return y_max;
	}
}
