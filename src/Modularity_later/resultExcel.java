package Modularity_later;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Mayang
 * 
 */

public class resultExcel {

	/**
	 * 构造函数，定义一个List,每个元素包含一个类型为整型的List。
	 */
	public resultExcel() {

		for (int i = 0; i < readFileExcel.matrix.length; i++) {
			Community.add(i, new ArrayList<Integer>());
		}
	}

	/**
	 * 处理结果并且输出
	 * 
	 * @param cows
	 * @param cols
	 */
	@SuppressWarnings("unchecked")
	public void result(int cows, int cols) {
		for (int i = 0; i < Community.size(); i++) {
			if (Community.get(i).contains(cows)) {
				for (int j = 0; j < Community.size(); j++) {
					if (Community.get(j).contains(cols)) {
						Community.get(i).addAll(Community.get(j));
						Community.get(j).clear();
						Community.get(j).add(null); // 这个用的很好，直接赋值为null，以防止后面删除了元素后，直接赋值到这里。
						break;
					}
				}
				Community.get(i).add(cols);
				break;
			} else if (Community.get(i).contains(cols)) {
				for (int j = 0; j < Community.size(); j++) {
					if (Community.get(j).contains(cows)) {
						Community.get(i).addAll(Community.get(j));
						Community.get(j).clear();
						Community.get(j).add(null);
						break;
					}
				}
				Community.get(i).add(cows);
				break;
			} else if (Community.get(i).isEmpty()) {
				Community.get(i).add(cows);
				Community.get(i).add(cols);
				break;
			}
		}
	}

	public void print_result() {

		// 清除相同元素
		for (int i = 0; i < Community.size(); i++) {
			for (int p = 0; p < Community.get(i).size(); p++) {
				for (int q = p + 1; q < Community.get(i).size(); q++) {
					if (Community.get(i).get(p) == Community.get(i).get(q)) {
						Community.get(i).remove(q);
						q--; // 这里是移除了q后，后面的元素自动自动替补了，但是会跳过q这个位置，所以重新判断q这个位置的元素。
					}
				}
			}
		}
		// 输出结果
		for (int i = 0; i < Community.size(); i++) {
			if (!Community.get(i).isEmpty() && !Community.get(i).contains(null)) {
				System.out.println(Community.get(i));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	List<List> Community = new ArrayList<List>();

}
