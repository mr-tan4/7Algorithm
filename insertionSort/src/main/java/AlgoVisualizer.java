import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {

    private static final int DELAY = 40;
    private InsertionSortData data;
    private AlgoFrame frame;
    private boolean isAnimated = true;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        data = new InsertionSortData(N, sceneHeight);
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Insertion Sort Visualization", sceneWidth, sceneHeight);
//            frame.addKeyListener(new AlgoKeyListener());
//            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {
        setData(0, -1);
        for (int i = 0; i < data.N(); i++) {
            setData(i, i);
            for (int j = i; j > 0 && data.get(j) < data.get(j - 1); j--) {
                data.swap(j, j - 1);
                setData(i + 1, j - 1);
            }
        }
        setData(data.N(), -1);
    }

    private void setData(int orderedIndex, int currentIndex) {
        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
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
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;
        AlgoVisualizer algoVisualizer = new AlgoVisualizer(sceneWidth, sceneHeight,N);
    }
}
