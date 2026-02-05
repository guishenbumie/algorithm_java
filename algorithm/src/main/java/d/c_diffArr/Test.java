package d.c_diffArr;

public class Test {
    public static void main(String[] args) {
    }

    /**
     * 订机票问题
     * 一维差分
     *
     * @param bookings
     * @param n
     * @return
     */
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] cnt = new int[n + 2];
        for (int[] booking : bookings) {//一维差分，[l]+num，[r+1]+num
            cnt[booking[0]] += booking[2];
            cnt[booking[1] + 1] -= booking[2];
        }
        for (int i = 1; i <= cnt.length; i++) {//前缀和
            cnt[i] += cnt[i - 1];
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = cnt[i + 1];
        }
        return ans;
    }
}
