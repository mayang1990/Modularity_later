package Modularity_later;

/**
 * 
 * @author Mayang
 * 
 */

public class MaxValue {
	public float getMaxValue() {
		return maxValue;
	}

	public int getCow() {
		return cow;
	}

	public int getCol() {
		return col;
	}

	private float maxValue = 0;
	private int cow, col;

	/**
	 * ���캯�����õ������е����ֵ�������ꡣ
	 * 
	 * @param array
	 */
	public MaxValue(float[][] array) {

		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i][j] > maxValue) {
					maxValue = array[i][j];
					cow = i;
					col = j;
				}
			}
		}
	}

}
