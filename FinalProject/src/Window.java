import java.awt.*;
import javax.swing.*;
import processing.awt.*;
import processing.core.*;

/**
 * The window that contains the entire program
 * 
 * @author Warren, Sepehr, Leo
 *
 */
public class Window {
	private JFrame window;
	private JPanel cardPanel;
	private GameMenu menu;
	private Gameboard game;
	private PauseMenu pause;
	private PSurfaceAWT.SmoothCanvas processingCanvas;

	public Window() {
		game = new Gameboard(this);
		PApplet.runSketch(new String[] { "" }, game);
		PSurfaceAWT surf = (PSurfaceAWT) game.getSurface();
		processingCanvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		window = (JFrame) processingCanvas.getFrame();
		window.setBounds(0, 0, 1200, 1000);
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.setResizable(true);
		cardPanel = new JPanel();
		CardLayout cl = new CardLayout();
		cardPanel.setLayout(cl);
		window.getContentPane().removeAll();
		menu = new GameMenu(this);
		game = new Gameboard(this);
		pause = new PauseMenu(this);
		cardPanel.add(menu, "menu");
		cardPanel.add(processingCanvas, "game");
		cardPanel.add(pause, "pause");
		window.setLayout(new BorderLayout());
		window.add(cardPanel);
		window.revalidate();
	}

	public void changePanel() {
		((CardLayout) cardPanel.getLayout()).next(cardPanel);
		processingCanvas.requestFocus();
	}

	public void pause() {
		game.pause();
		((CardLayout) cardPanel.getLayout()).show(cardPanel, "pause");
		processingCanvas.requestFocus();
	}

	public void play() {
		game.play();
		((CardLayout) cardPanel.getLayout()).show(cardPanel, "game");
		processingCanvas.requestFocus();
	}

	public void menu() {
		game = new Gameboard(this);
		((CardLayout) cardPanel.getLayout()).show(cardPanel, "menu");
		processingCanvas.requestFocus();
	}

	public void quit() {
		System.exit(0);
	}
}