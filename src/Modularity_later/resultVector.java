package Modularity_later;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Mayang
 * 
 */
public class resultVector {

	/**
	 * 构造函数，定义一个List,每个元素包含一个类型为整型的List。
	 */
	public resultVector() {

		for (int i = 0; i < readFile.vex.length; i++) {
			Community.add(i, new ArrayList<Integer>());
		}
	}

	/**
	 * 
	 * @param cows
	 * @param cols
	 */
	@SuppressWarnings("unchecked")
	public void result(int cows, int cols) {

		for (int i = 0; i < readFile.vex.length; i++) {
			if (Community.get(i).isEmpty()) {
				Community.get(i).add(cows);
				Community.get(i).add(cols);
				break;
			} else if (Community.get(i).contains(cols)) {
				Community.get(i).add(cows);
				break;
			} else if (Community.get(i).contains(cows)) {
				Community.get(i).add(cols);
				break;
			}

		}
	}

	/**
	 * 输出社区划分结果
	 */
	public void print_result() {
		for (int i = 0; i < readFile.vex.length; i++) {
			if (!Community.get(i).isEmpty()) {
				System.out.println(Community.get(i));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	List<List> Community = new ArrayList<List>();

}
