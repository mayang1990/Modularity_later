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
	 * ���캯��������һ��List,ÿ��Ԫ�ذ���һ������Ϊ���͵�List��
	 */
	public resultExcel() {

		for (int i = 0; i < readFileExcel.matrix.length; i++) {
			Community.add(i, new ArrayList<Integer>());
		}
	}

	/**
	 * �������������
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
						Community.get(j).add(null); // ����õĺܺã�ֱ�Ӹ�ֵΪnull���Է�ֹ����ɾ����Ԫ�غ�ֱ�Ӹ�ֵ�����
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

		// �����ͬԪ��
		for (int i = 0; i < Community.size(); i++) {
			for (int p = 0; p < Community.get(i).size(); p++) {
				for (int q = p + 1; q < Community.get(i).size(); q++) {
					if (Community.get(i).get(p) == Community.get(i).get(q)) {
						Community.get(i).remove(q);
						q--; // �������Ƴ���q�󣬺����Ԫ���Զ��Զ��油�ˣ����ǻ�����q���λ�ã����������ж�q���λ�õ�Ԫ�ء�
					}
				}
			}
		}
		// ������
		for (int i = 0; i < Community.size(); i++) {
			if (!Community.get(i).isEmpty() && !Community.get(i).contains(null)) {
				System.out.println(Community.get(i));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	List<List> Community = new ArrayList<List>();

}
