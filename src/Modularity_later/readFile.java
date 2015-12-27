package Modularity_later;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Mayang
 * 
 */
public class readFile {
	public int[][] readLine(String path) {

		BufferedReader br = null;
		int[][] array = null;

		try {

			String line;
			int count = 0;
			int cows = 0;
			int cols = 0;

			br = new BufferedReader(new FileReader(path));

			// ȥ����β����ո�
			line = br.readLine();
			String[] eline = line.split(" ");

			// �����һ�е�����
			// �����һ�е�һ�����ݣ�Ϊ�����������ɶ�������
			int vexnum = Integer.parseInt(eline[0]);
			vex = new int[vexnum];
			for (int i = 0; i < vexnum; i++) {
				vex[i] = i;
			}

			// �����һ��ʣ�µ���������
			cows = Integer.parseInt(eline[1]);
			cols = Integer.parseInt(eline[2]);
			array = new int[cows][cols];

			// �����һ��֮�������
			while ((line = br.readLine()) != null) {

				eline = line.split(" ");
				array[count][0] = Integer.parseInt(eline[0]);
				array[count][1] = Integer.parseInt(eline[1]);
				array[count][2] = Integer.parseInt(eline[2]);
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return array;
	}

	public static int[] vex;
	public static float Q = 0;

	/**
	 * ������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// �����ݼ�
		readFile read = new readFile();
		int[][] edges = read.readLine("Gahu.txt"); // �з�������signed

		signedNet pG2 = new signedNet(vex, edges);
		resultVector resultCom = new resultVector();

		// ��ӡͼ
		pG2.printMatrix();
		pG2.qMatrix();

		while (true) {

			// �����ֵ��
			MaxValue mMax = new MaxValue(signedNet.qMatrix);
			float max = mMax.getMaxValue();
			int cow = mMax.getCow();
			int col = mMax.getCol();
			System.out.printf("%f %d %d\n", max, cow, col);

			if (max > 0) {
				pG2.reqMatrix(signedNet.qMatrix, cow, col);

				// ����resultVector����
				resultCom.result(cow, col);
				// resultCom.print_result();
				Q = Q + max;
				System.out.printf("%f", Q);
			} else {
				break;
			}
			pG2.printqMatris();

		}
		// ����������ֽ��
		resultCom.print_result();
		System.out.printf("%f", Q);
	}
}
