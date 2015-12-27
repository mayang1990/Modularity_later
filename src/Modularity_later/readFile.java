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

			// 去除首尾多余空格
			line = br.readLine();
			String[] eline = line.split(" ");

			// 处理第一行的数据
			// 处理第一行第一个数据，为定点数，生成顶点数组
			int vexnum = Integer.parseInt(eline[0]);
			vex = new int[vexnum];
			for (int i = 0; i < vexnum; i++) {
				vex[i] = i;
			}

			// 处理第一行剩下的两个数据
			cows = Integer.parseInt(eline[1]);
			cols = Integer.parseInt(eline[2]);
			array = new int[cows][cols];

			// 处理第一行之后的数据
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
	 * 主函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// 读数据集
		readFile read = new readFile();
		int[][] edges = read.readLine("Gahu.txt"); // 有符号网络signed

		signedNet pG2 = new signedNet(vex, edges);
		resultVector resultCom = new resultVector();

		// 打印图
		pG2.printMatrix();
		pG2.qMatrix();

		while (true) {

			// 求最大值。
			MaxValue mMax = new MaxValue(signedNet.qMatrix);
			float max = mMax.getMaxValue();
			int cow = mMax.getCow();
			int col = mMax.getCol();
			System.out.printf("%f %d %d\n", max, cow, col);

			if (max > 0) {
				pG2.reqMatrix(signedNet.qMatrix, cow, col);

				// 调用resultVector函数
				resultCom.result(cow, col);
				// resultCom.print_result();
				Q = Q + max;
				System.out.printf("%f", Q);
			} else {
				break;
			}
			pG2.printqMatris();

		}
		// 输出社区划分结果
		resultCom.print_result();
		System.out.printf("%f", Q);
	}
}
