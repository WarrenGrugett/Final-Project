import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import processing.awt.*;

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
	private PSurfaceAWT surf;
	private PSurfaceAWT.SmoothCanvas processingCanvas;

	public Window() {
		window = new JFrame();
		game = new Gameboard(this);
		game.run();
		surf = (PSurfaceAWT) game.getSurface();
		processingCanvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		window.setBounds(500, 0, 1206, 995);
		window.setMinimumSize(new Dimension(306, 235));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);
		surf.setSize(window.getWidth(), window.getHeight());
		cardPanel = new JPanel();
		CardLayout cl = new CardLayout();
		cardPanel.setLayout(cl);
		window.getContentPane().removeAll();
		menu = new GameMenu(this);
		pause = new PauseMenu(this);
		cardPanel.add(menu, "menu");
		cardPanel.add(processingCanvas, "game");
		cardPanel.add(pause, "pause");
		cardPanel.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent arg0) {
				Component x = (Component) arg0.getSource();
				fixProcessingPanelSizes(x);
			}
		});
		window.setLayout(new BorderLayout());
		window.add(cardPanel);
		window.revalidate();
	}

	public void fixProcessingPanelSizes(Component x) {
		surf.setSize(x.getWidth(), x.getHeight());
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