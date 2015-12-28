package Modularity_later;

/**
 * 
 * @author Mayang
 * 
 */

public class signedNet {

	private int[] mVexs;
	private int[][] mMatrix; // �ڽӾ���
	static float[][] qMatrix; // ģ�����������

	int totalNum = 0, pNum = 0, nNum = 0;// �����еı�������������������
	float pRatio, nRatio;// ���������ߺ͸��߷ֱ�ռ�ñ�����
	float pVector[], nVector[];

	/**
	 * ���캯��������һ���ڽӾ���
	 * 
	 * @param vexs
	 * @param edges
	 */
	public signedNet(int[] vexs, int[][] edges) {

		// ��ʼ��"������"��"����"
		int vlen = vexs.length;
		int elen = edges.length;

		// ��ʼ��"����"
		mVexs = new int[vlen];
		for (int i = 0; i < vlen; i++)
			mVexs[i] = vexs[i];

		// ��ʼ��"��"
		mMatrix = new int[vlen][vlen];
		for (int i = 0; i < elen; i++) {

			int p1 = edges[i][0];
			int p2 = edges[i][1];

			mMatrix[p1][p2] = edges[i][2];
			mMatrix[p2][p1] = edges[i][2];

		}
	}

	/**
	 * ��ӡ�������ͼ
	 */
	public void printMatrix() {
		System.out.printf("signed Martix Graph:\n");
		for (int i = 0; i < mVexs.length; i++) {
			for (int j = 0; j < mVexs.length; j++)
				System.out.printf("%3d", mMatrix[i][j]);
			System.out.printf("\n");
		}
	}

	/**
	 * ��ӡģ�����������
	 */
	public void printqMatris() {
		System.out.printf("\nsigned Q:\n");
		for (int i = 0; i < mVexs.length; i++) {
			for (int j = 0; j < mVexs.length; j++)
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
		for (int j = 0; j < mVexs.length; j++) {
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
		for (int j = 0; j < mVexs.length; j++) {
			if (mMatrix[i][j] == -1)
				ns++;
		}
		return ns;
	}

	/**
	 * ����ģ��Ⱦ���
	 */
	public void qMatrix() {

		pVector = new float[mVexs.length];
		nVector = new float[mVexs.length];
		qMatrix = new float[mVexs.length][mVexs.length];

		// �����������ߺ͸��ߵĺ͡� pRatio����߅���ױ�����nRatio��ؓ߅���ױ�����
		for (int i = 0; i < mVexs.length; i++) {
			for (int j = 0; j < mVexs.length; j++) {
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
		for (int i = 0; i < mVexs.length; i++) {
			pVector[i] = (float) pStrength(i) / pNum;
			nVector[i] = (float) nStrength(i) / nNum;
		}

		for (int i = 0; i < mVexs.length; i++) {

			System.out.printf("\n%d", pStrength(i));
			System.out.printf(" %d", nStrength(i));
			System.out.printf("\n%f", pVector[i]);
			System.out.printf(" %f", nVector[i]);
			System.out.printf("\n");
		}

		// �����ʼģ�����������
		for (int i = 0; i < mVexs.length; i++) {
			for (int j = 0; j < mVexs.length; j++) {
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
	 * @param matrix
	 * @param cow
	 * @param col
	 */
	public void reqMatrix(float[][] matrix, int cow, int col) {
		for (int k = 0; k < matrix.length; k++) {
			if (matrix[cow][k] != 0 && matrix[col][k] != 0 && k != cow
					&& k != col) {

				matrix[col][k] = matrix[cow][k] + matrix[col][k];

			} else if (matrix[cow][k] == 0 && matrix[col][k] != 0 && k != cow
					&& k != col) {
				matrix[col][k] = matrix[col][k]
						- 2
						* (pRatio * pVector[cow] * pVector[k] - nRatio
								* nVector[cow] * nVector[k]);

			} else if (matrix[cow][k] != 0 && matrix[col][k] == 0 && k != cow
					&& k != col) {
				matrix[col][k] = matrix[cow][k]
						- 2
						* (pRatio * pVector[col] * pVector[k] - nRatio
								* nVector[col] * nVector[k]);

			}
			matrix[k][col] = matrix[col][k];
			matrix[k][cow] = 0;
			matrix[cow][k] = 0;
		}
		pVector[col] = pVector[cow] + pVector[col];
		nVector[col] = nVector[cow] + nVector[col];
		pVector[cow] = 0;
		nVector[cow] = 0;

		for (int m = 0; m < mVexs.length; m++) {

			System.out.printf("%f", pVector[m]);
			System.out.printf("%f", nVector[m]);
			System.out.printf("\n");
		}

	}
}
