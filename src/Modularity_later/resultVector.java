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
	 * ���캯��������һ��List,ÿ��Ԫ�ذ���һ������Ϊ���͵�List��
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
	 * ����������ֽ��
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
