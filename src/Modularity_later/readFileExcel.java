package Modularity_later;

import java.io.*;
import jxl.*;

/**
 * 
 * @author Mayang
 * 
 */
public class readFileExcel {

	static int[][] matrix = null;
	public static float Q = 0;

	public readFileExcel() {

		try {
			Workbook book = Workbook.getWorkbook(new File("10nodesSigned.xls"));
			// ��õ�һ�����������
			Sheet sheet = book.getSheet(0);
			matrix = new int[sheet.getRows()][sheet.getColumns()];
			for (int i = 1; i < sheet.getRows(); i++) {
				for (int j = 1; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j, i);
					matrix[i][j] = Integer.valueOf(cell.getContents());
					System.out.print(matrix[i][j] + "  ");
				}
				System.out.println();
			}
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * ������
	 * 
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// ��Excel�ļ�
		readFileExcel readExcel = new readFileExcel();

		signedNetExcel pG2 = new signedNetExcel(matrix);
		resultExcel resultCom = new resultExcel();

		// ��ӡͼ
		pG2.printMatrix();
		pG2.qMatrix();

		while (true) {

			// �����ֵ��
			MaxValue mMax = new MaxValue(signedNetExcel.qMatrix);
			float max = mMax.getMaxValue();
			int cow = mMax.getCow();
			int col = mMax.getCol();
			System.out.printf("%f %d %d\n", max, cow, col);

			if (max > 0) {
				pG2.reqMatrix(signedNetExcel.qMatrix, cow, col);

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
