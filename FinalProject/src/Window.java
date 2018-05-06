import java.awt.*;
import javax.swing.*;
import processing.awt.*;
import processing.core.*;

/**
 * The window that contains the entire program
 * @author Warren, Sepehr, Leo
 *
 */
public class Window {
	private JFrame window;
	private JPanel cardPanel;
	private GameMenu menu;
	private Gameboard game;
	private PSurfaceAWT.SmoothCanvas processingCanvas;

	public Window() {
		game = new Gameboard(this);
		PApplet.runSketch(new String[] { "" }, game);
		PSurfaceAWT surf = (PSurfaceAWT) game.getSurface();
		processingCanvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		window = (JFrame) processingCanvas.getFrame();
		window.setBounds(0, 0, 800, 600);
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		cardPanel = new JPanel();
		CardLayout cl = new CardLayout();
		cardPanel.setLayout(cl);
		window.getContentPane().removeAll();
		menu = new GameMenu(this);
		game = new Gameboard(this);
		cardPanel.add(menu, "1");
		cardPanel.add(processingCanvas, "2");
		window.setLayout(new BorderLayout());
		window.add(cardPanel);
		window.revalidate();
	}

	public void changePanel() {
		((CardLayout) cardPanel.getLayout()).next(cardPanel);
		processingCanvas.requestFocus();
	}
}