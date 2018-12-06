import java.util.Random;

public class Mine {

	private static final int ROW_COUNT = 10; // ����
	private static final int COL_COUNT = 10; // ����
	private static final int MINE_COUNT = 10; // ���� ����
	private static final String MINE = " M "; // ����
	private static final String EMPTY = " 0 ";// ����

	private static String mArray[][] = null; // Array

	public static void main(String[] args) {

		// init
		initArray();
		setFeild();
		setMine();

		// find
		findMine();
		print();

	}

	/**
	 * Array ����
	 */
	private static void initArray() {
		mArray = new String[ROW_COUNT][COL_COUNT];
	}

	/**
	 * �ٵ��� ����
	 */
	private static void setFeild() {
		for (int i = 0; i < ROW_COUNT; i++) {
			for (int j = 0; j < COL_COUNT; j++) {
				mArray[i][j] = EMPTY;
			}
		}
	}

	/**
	 * ���� ����
	 */
	private static void setMine() {
		// ������ü ����
		Random random = new Random();

		int count = MINE_COUNT;
		while (count > 0) {
			int row = random.nextInt(ROW_COUNT);
			int col = random.nextInt(COL_COUNT);

			// mArray�� ���� EMPTY�϶� MINE�߰�
			if (mArray[row][col].equals(EMPTY)) {
				mArray[row][col] = MINE;
				// ī��Ʈ -1
				count--;
			}
		}

//		// test
//		mArray[1][2] = MINE;
//		mArray[2][3] = MINE;
//		mArray[3][5] = MINE;
//		mArray[5][2] = MINE;
//		mArray[6][2] = MINE;
//		mArray[6][8] = MINE;
//		mArray[8][0] = MINE;
//		mArray[9][1] = MINE;
//		mArray[1][2] = MINE;
//		mArray[0][9] = MINE;

	}

	/**
	 * ���� ã��
	 */
	private static void findMine() {
		for (int i = 0; i < ROW_COUNT; i++) {
			for (int j = 0; j < COL_COUNT; j++) {
				if (isEmpty(i, j) && !isMine(i, j)) {
					mArray[i][j] = " " + count(i, j) + " ";
				}
			}
		}
	}
	
	/**
	 * ���� �ֺ� ���� ī��Ʈ
	 * 
	 * @param row
	 * @param col
	 * @return ���� �ֺ� ���� ī��Ʈ
	 */
	private static int count(int row, int col) {
		int count = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (isExist(row + i, col + j)) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * ���� �Ǵ�
	 */
	private static boolean isExist(int row, int col) {

		// ���� üũ
		if (isRange(row, col)) {
			return false;
		}

		// ���� üũ
		if (isEmpty(row, col)) {
			return false;
		}

		// ���� üũ
		if (isMine(row, col)) {
			return true;
		}

		return false;
	}

	/**
	 * Out of Range
	 * 
	 * @return true / false
	 */
	private static boolean isRange(int row, int col) {
		if (row < 0 || row >= ROW_COUNT || col < 0 || col >= COL_COUNT) {
			// ������ ���� üũ
			return true;
		}
		return false;
	}

	/**
	 * Empty üũ
	 * 
	 * @return true / false
	 */
	private static boolean isEmpty(int row, int col) {
		if (mArray[row][col].equals(EMPTY)) {
			return true;
		}
		return false;
	}

	/**
	 * Mine üũ
	 * 
	 * @return true / false
	 */
	private static boolean isMine(int row, int col) {
		if (mArray[row][col].equals(MINE)) {
			return true;
		}
		return false;
	}

	/**
	 * ���
	 */
	private static void print() {
		for (int i = 0; i < ROW_COUNT; i++) {
			for (int j = 0; j < COL_COUNT; j++) {
				System.out.print(mArray[i][j].replaceAll(MINE, EMPTY));
			}
			System.out.println("");
		}
	}
}
