import javax.swing.*;
import processing.awt.*;
import processing.core.*;
import java.awt.*;

public class Main {
	private Window window;
	private JPanel cardPanel;
	private GameMenu panel1;
	private Gameboard panel2;
	private PSurfaceAWT.SmoothCanvas processingCanvas;

	public Main() {
		panel2 = new Gameboard();
		PApplet.runSketch(new String[] { "" }, panel2);
		PSurfaceAWT surf = (PSurfaceAWT) panel2.getSurface();
		processingCanvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		window = (Window) processingCanvas.getFrame();
		window.setBounds(0, 0, 800, 600);
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		cardPanel = new JPanel();
		CardLayout cl = new CardLayout();
		cardPanel.setLayout(cl);
		window.getContentPane().removeAll();
		panel1 = new GameMenu();
		panel2 = new Gameboard();
		cardPanel.add(panel1, "1");
		cardPanel.add(processingCanvas, "2");
		window.setLayout(new BorderLayout());
		window.add(cardPanel);
		window.revalidate();
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Main m = new Main();
	}

	public void changePanel() {
		((CardLayout) cardPanel.getLayout()).next(cardPanel);
		processingCanvas.requestFocus();
	}
}