import java.util.Scanner;

public class Main {
    static int[] numbers;
    static int[] len;
    static int[] prexIndex;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        numbers = new int[cnt]; // 수열 담기
        len = new int[cnt]; // 수열의 각 수에 대한 가장 긴 증가하는 부분 수열의 길이
        prexIndex = new int[cnt]; // 가장 긴 증가하는 부분 수열의 직전의 수
        for (int i=0;i<cnt;i++) {
            numbers[i] = sc.nextInt();
        }
        // Bottom-Up 방식
        // A[i] = 수열의 i번째 수까지 주어졌을 때, 가장 긴 증가하는 부분 수열
        // 점화식 : A[i] = A[j] + 1, j = 수열의 i번째 수까지 주어졌을 때, 가장 긴 증가하는 부분 수열의 마지막 직전의 수가 위치하는 인덱스
        for (int i=0;i<cnt;i++) {
            len[i] = 1;
            prexIndex[i] = -1;
            for (int j=0;j<i;j++) {
                if (numbers[j] < numbers[i] && len[i] < len[j]+1) {
                    len[i] = len[j] + 1;
                    prexIndex[i] = j;
                }
            }
        }
        int maxLen = len[0];
        int maxLenIndex = 0;
        for (int i=0;i<cnt;i++) {
            if (maxLen < len[i]) {
                maxLen = len[i];
                maxLenIndex = i;
            }
        }
        System.out.println(maxLen);
        go(maxLenIndex);
        System.out.println();
    }

    public static void go(int p) {
        if (p == -1) {
            return;
        }
        go(prexIndex[p]);
        System.out.print(numbers[p] + " ");
    }
}
