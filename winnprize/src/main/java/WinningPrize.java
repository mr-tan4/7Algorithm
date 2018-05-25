public class WinningPrize {

    private double chance;
    private int playTime;
    private int N;

    public WinningPrize(double chance, int playTime, int N) {
        if (chance < 0.0 || chance > 1.0) {
            throw new IllegalArgumentException();
        }
        if (playTime <= 0 || N <= 0) {
            throw new IllegalArgumentException();
        }
        this.chance = chance;
        this.playTime = playTime;
        this.N = N;
    }

    public void run() {
        int wins = 0;
        for (int i = 0; i < N; i++) {
            if (play()) {
                wins++;
            }
        }
        System.out.println("winning rate:" + (double) wins / N);
    }

    public boolean play() {
        for (int i = 0; i < playTime; i++) {
            if (Math.random() < chance) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        double chance = 0.17;
        int playTime = 20;
        int N = 10000000;

        WinningPrize prize = new WinningPrize(chance,playTime,N);
        prize.run();
    }
}
