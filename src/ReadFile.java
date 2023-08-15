import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
public class ReadFile {
	public static List<int[]> readFile(String fileName) {
		int i;
		Sheet sheet;
		Workbook book;
		Cell cell1, cell2, cell3;
		List<int[]> list = new ArrayList<int[]>();
		try {
			// fileNameΪҪ��ȡ��excel�ļ���
			book = Workbook.getWorkbook(new File(fileName));
			// ��õ�һ�����������(excel��sheet�ı�Ŵ�0��ʼ,0,1,2,3,....)
			sheet = book.getSheet(0);
			// ��ȡ���Ͻǵĵ�Ԫ��
			cell1 = sheet.getCell(0, 0);
			cell2 = sheet.getCell(1, 0);
			cell3 = sheet.getCell(2, 0);
			i = 1;
			while (true) {
				int[] s = new int[3];
				// ��ȡÿһ�еĵ�Ԫ��
				if ("".equals(cell1.getContents()) == true) // �����ȡ������Ϊ��
					break;
				cell1 = sheet.getCell(0, i);// ���У��У�
				cell2 = sheet.getCell(1, i);
				cell3 = sheet.getCell(2, i);
				s[0] = Integer.parseInt(cell1.getContents());
				s[1] = Integer.parseInt(cell2.getContents());
				s[2] = Integer.parseInt(cell3.getContents());
				list.add(s);
				i++;
			}
			book.close();
		} catch (Exception e) {
		}
		return list;
	}

	//build undirected graph, given a directional-edges-list (no repeated edges), it build the bi-directional edges
	//it checks the bi-directional edge
	public static Map<Integer, List<Integer>> buildGraph() {//build undirected graph
		List<int[]> list = readFile(Cst.edge_fileName);
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		List<Integer> nodes = null;
		for (int i = 0; i < list.size(); i++) {
			if(map.get(list.get(i)[1])==null){//not exist in map yet
				nodes = new ArrayList<Integer>();
				nodes.add(list.get(i)[2]);
				map.put(list.get(i)[1], nodes);
			}else{//already exist in map
				nodes = map.get(list.get(i)[1]);
				nodes.add(list.get(i)[2]);
			}
		}
		//take the end node as the start node, then re-check
		for (int i = 0; i < list.size(); i++) {
			if(map.get(list.get(i)[2])==null){//not exist in map yet
				nodes = new ArrayList<Integer>();
				nodes.add(list.get(i)[1]);
				map.put(list.get(i)[2], nodes);
			}else{//already exist in map
				nodes = map.get(list.get(i)[2]);
				nodes.add(list.get(i)[1]);
			}
		}
		
		
		for(Entry<Integer, List<Integer>> entry : map.entrySet()) {
			Integer key = entry.getKey();
			int count = 0;//count the number of values for each key
			List<Integer> values = entry.getValue();
			System.out.println("Key = " + key);
			for(int k=0; k<values.size(); k++){
				System.out.print(values.get(k)+", ");
				count++;
				}
			System.out.println();
//			System.out.println("the number of values = "+count);
			}
		
		
		return map;
	}
	
	
}
