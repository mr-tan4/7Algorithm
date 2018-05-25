import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class AlgoVisualizer {

    private static final int DELAY = 40;
    private int[] money;
    private AlgoFrame frame;
    private boolean isAnimated = true;

    public AlgoVisualizer(int sceneWidth, int sceneHeight) {
        money = new int[100];
        for (int i = 0; i < money.length; i++) {
            money[i] = 100;
        }

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Circle", sceneWidth, sceneHeight);
//            frame.addKeyListener(new AlgoKeyListener());
//            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {
        while (true) {
            Arrays.sort(money);
            frame.render(money);
            AlgoVisHelper.pause(DELAY);
            for (int k = 0; k < 50; k++) {
                for (int i = 0; i < money.length; i++) {
//                    if (money[i] > 0) {
                        int j = (int) (Math.random() * money.length);
                        money[i] -= 1;
                        money[j] += 1;
                    //}
                }
            }
        }
    }

    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            e.translatePoint(0, -(frame.getBounds().height - frame.getCanvasHeight()));
        }
    }


    public static void main(String[] args) {
        int sceneWidth = 1000;
        int sceneHeight = 800;
        int N = 50;
        AlgoVisualizer algoVisualizer = new AlgoVisualizer(sceneWidth, sceneHeight);
    }
}
