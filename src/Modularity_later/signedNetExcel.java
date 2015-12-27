package Modularity_later;

/**
 * 
 * @author Mayang
 *
 */
public class signedNetExcel {

	private int[][] mMatrix; // �ڽӾ���
	static float[][] qMatrix; // ģ�����������
	int LEN; // ��ꇵĴ�С

	int totalNum = 0, pNum = 0, nNum = 0;// �����еı�������������������
	float pRatio, nRatio;// ���������ߺ͸��߷ֱ�ռ�ñ�����
	float pVector[], nVector[];

	public signedNetExcel(int[][] array) {
		mMatrix = array;
		LEN = mMatrix.length;
	}

	/**
	 * ��ӡ�������ͼ
	 */
	public void printMatrix() {
		System.out.printf("signed Martix Graph:\n");
		for (int i = 0; i < LEN; i++) {
			for (int j = 0; j < LEN; j++)
				System.out.printf("%3d", mMatrix[i][j]);
			System.out.printf("\n");
		}
	}

	/**
	 * ��ӡ��ʼģ�����������
	 */
	public void printqMatris() {
		System.out.printf("\nsigned Q:\n");
		for (int i = 0; i < LEN; i++) {
			for (int j = 0; j < LEN; j++)
				System.out.printf("%10f", qMatrix[i][j]);
			System.out.printf("\n");
		}
	}

	/**
	 * ����ڵ���ǿ��
	 * 
	 * @param i
	 * @return
	 */
	public int pStrength(int i) {
		int ps = 0;
		for (int j = 0; j < LEN; j++) {
			if (mMatrix[i][j] == 1)
				ps++;
		}
		return ps;
	}

	/**
	 * ����ڵ㸺ǿ��
	 * 
	 * @param i
	 * @return
	 */
	public int nStrength(int i) {
		int ns = 0;
		for (int j = 0; j < LEN; j++) {
			if (mMatrix[i][j] == -1)
				ns++;
		}
		return ns;
	}

	/**
	 * ����ģ��Ⱦ���
	 */
	public void qMatrix() {

		pVector = new float[LEN];
		nVector = new float[LEN];
		qMatrix = new float[LEN][LEN];

		// �����������ߺ͸��ߵĺ͡� pRatio����߅���ױ�����nRatio��ؓ߅���ױ�����
		for (int i = 0; i < LEN; i++) {
			for (int j = 0; j < LEN; j++) {
				if (mMatrix[i][j] == 1) {
					pNum++;
				} else if (mMatrix[i][j] == -1) {
					nNum++;
				}
			}
		}
		totalNum = pNum + nNum;
		pRatio = (float) pNum / totalNum;
		nRatio = (float) nNum / totalNum;
		System.out.printf("%d %d", pNum, nNum);
		System.out.printf(" %f %f", pRatio, nRatio);

		// �õ���ǿ������͸�ǿ�����顣
		for (int i = 0; i < LEN; i++) {
			pVector[i] = (float) pStrength(i) / pNum;
			nVector[i] = (float) nStrength(i) / nNum;
		}

		for (int i = 0; i < LEN; i++) {

			System.out.printf("\n%d", pStrength(i));
			System.out.printf(" %d", nStrength(i));
			System.out.printf("\n%f", pVector[i]);
			System.out.printf(" %f", nVector[i]);
			System.out.printf("\n");
		}

		// �����ʼģ�����������
		for (int i = 0; i < LEN; i++) {
			for (int j = 0; j < LEN; j++) {
				if (mMatrix[i][j] == 1) {
					qMatrix[i][j] = pRatio
							* ((float) 1 / pNum - pVector[i] * pVector[j])
							+ nRatio * nVector[i] * nVector[j];
				} else if (mMatrix[i][j] == -1) {
					qMatrix[i][j] = -pRatio * pVector[i] * pVector[j] - nRatio
							* ((float) 1 / nNum - nVector[i] * nVector[j]);
				}
			}
		}

		// �����ʼģ�����������
		printqMatris();

	}

	/**
	 * ����ģ�����������
	 * 
	 * @param array
	 * @param cow
	 * @param col
	 */
	public void reqMatrix(float[][] array, int cow, int col) {
		for (int k = 0; k < array.length; k++) {
			if (array[cow][k] != 0 && array[col][k] != 0 && k != cow
					&& k != col) {

				array[col][k] = array[cow][k] + array[col][k];

			} else if (array[cow][k] == 0 && array[col][k] != 0 && k != cow
					&& k != col) {
				array[col][k] = array[col][k]
						- 2
						* (pRatio * pVector[cow] * pVector[k] - nRatio
								* nVector[cow] * nVector[k]);

			} else if (array[cow][k] != 0 && array[col][k] == 0 && k != cow
					&& k != col) {
				array[col][k] = array[cow][k]
						- 2
						* (pRatio * pVector[col] * pVector[k] - nRatio
								* nVector[col] * nVector[k]);

			}
			array[k][col] = array[col][k];
			array[k][cow] = 0;
			array[cow][k] = 0;
		}
		pVector[col] = pVector[cow] + pVector[col];
		nVector[col] = nVector[cow] + nVector[col];
		pVector[cow] = 0;
		nVector[cow] = 0;

		for (int m = 0; m < mMatrix.length; m++) {

			System.out.printf("%f", pVector[m]);
			System.out.printf("%f", nVector[m]);
			System.out.printf("\n");
		}

	}
}
