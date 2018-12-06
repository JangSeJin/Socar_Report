import java.util.Random;

public class Mine {

	private static final int ROW_COUNT = 10; // 가로
	private static final int COL_COUNT = 10; // 세로
	private static final int MINE_COUNT = 10; // 지뢰 갯수
	private static final String MINE = " M "; // 지뢰
	private static final String EMPTY = " 0 ";// 공백

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
	 * Array 생성
	 */
	private static void initArray() {
		mArray = new String[ROW_COUNT][COL_COUNT];
	}

	/**
	 * 바둑판 생성
	 */
	private static void setFeild() {
		for (int i = 0; i < ROW_COUNT; i++) {
			for (int j = 0; j < COL_COUNT; j++) {
				mArray[i][j] = EMPTY;
			}
		}
	}

	/**
	 * 지뢰 생성
	 */
	private static void setMine() {
		// 랜덤객체 생성
		Random random = new Random();

		int count = MINE_COUNT;
		while (count > 0) {
			int row = random.nextInt(ROW_COUNT);
			int col = random.nextInt(COL_COUNT);

			// mArray의 값이 EMPTY일때 MINE추가
			if (mArray[row][col].equals(EMPTY)) {
				mArray[row][col] = MINE;
				// 카운트 -1
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
	 * 지뢰 찾기
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
	 * 지뢰 주변 숫자 카운트
	 * 
	 * @param row
	 * @param col
	 * @return 지뢰 주변 숫자 카운트
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
	 * 지뢰 판단
	 */
	private static boolean isExist(int row, int col) {

		// 범위 체크
		if (isRange(row, col)) {
			return false;
		}

		// 공백 체크
		if (isEmpty(row, col)) {
			return false;
		}

		// 지뢰 체크
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
			// 지뢰의 범위 체크
			return true;
		}
		return false;
	}

	/**
	 * Empty 체크
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
	 * Mine 체크
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
	 * 출력
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
