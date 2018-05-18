import java.awt.*;
import javax.swing.*;
import processing.awt.*;
import processing.core.*;

/**
 * The window that contains the entire program
 * 
 * @author Warren
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
		window.setBounds(500, 0, 1206, 995);
		window.setMinimumSize(new Dimension(306, 235));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		cardPanel = new JPanel();
		CardLayout cl = new CardLayout();
		cardPanel.setLayout(cl);
		window.getContentPane().removeAll();
		menu = new GameMenu(this);
		pause = new PauseMenu(this);
		cardPanel.add(menu, "menu");
		cardPanel.add(processingCanvas, "game");
		cardPanel.add(pause, "pause");
		window.setLayout(new BorderLayout());
		window.add(cardPanel);
		window.revalidate();
	}

	public void keepShop() {
		int width = window.getBounds().width, height = window.getBounds().height;
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		if (width > gd.getDisplayMode().getWidth())
			width = gd.getDisplayMode().getWidth();
		if (height > gd.getDisplayMode().getHeight())
			height = gd.getDisplayMode().getHeight();
		if (width - 100 < height)
			height = width - 100;
		window.setBounds(window.getBounds().x, window.getBounds().y, width, height);
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
		((CardLayout) cardPanel.getLayout()).show(cardPanel, "menu");
		processingCanvas.requestFocus();
	}

	public void quit() {
		System.exit(0);
	}
}