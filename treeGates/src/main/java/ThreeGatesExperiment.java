public class ThreeGatesExperiment {

    private int N;

    public ThreeGatesExperiment(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
    }

    public void run(boolean changeDoor) {
        int wins = 0;
        for (int i = 0; i < N; i++) {
            if (play(changeDoor)) {
                wins++;
            }
        }
        System.out.println(changeDoor ? "Change" : "Not Change");
        System.out.println("winning rate: " + (double) wins / N);
    }

    public boolean play(boolean changeDoor){
        int prizeDoor = (int)(Math.random() * 3);
        int playerChoice = (int)(Math.random() * 3);
        if(playerChoice == prizeDoor){
            return changeDoor ? false : true;
        }else {
            return changeDoor ? true : false;
        }
    }

    public static void main(String[] args) {
        int N = 10000000;
        ThreeGatesExperiment experiment = new ThreeGatesExperiment(N);
        experiment.run(true);
        System.out.println();
        experiment.run(false);
    }


}
