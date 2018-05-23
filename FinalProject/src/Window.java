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
	private MapCreator creator;
	private PSurfaceAWT surf, cSurf;
	private PSurfaceAWT.SmoothCanvas gameCanvas, cCanvas;

	public Window() {
		window = new JFrame();
		game = new Gameboard(this);
		creator = new MapCreator(this);
		game.run();
		creator.run();
		surf = (PSurfaceAWT) game.getSurface();
		gameCanvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		cSurf = (PSurfaceAWT) creator.getSurface();
		cCanvas = (PSurfaceAWT.SmoothCanvas) cSurf.getNative();
		window.setBounds(500, 0, 1206, 995);
		window.setMinimumSize(new Dimension(306, 235));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setVisible(true);
		surf.setSize(window.getWidth(), window.getHeight());
		cSurf.setSize(window.getWidth(), window.getHeight());
		cardPanel = new JPanel();
		CardLayout cl = new CardLayout();
		cardPanel.setLayout(cl);
		window.getContentPane().removeAll();
		menu = new GameMenu(this);
		pause = new PauseMenu(this);
		cardPanel.add(menu, "menu");
		cardPanel.add(gameCanvas, "game");
		cardPanel.add(pause, "pause");
		cardPanel.add(cCanvas, "creator");
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
		cSurf.setSize(x.getWidth(), x.getHeight());
	}

	public void pause() {
		game.pause();
		((CardLayout) cardPanel.getLayout()).show(cardPanel, "pause");
	}

	public void play() {
		game.play();
		((CardLayout) cardPanel.getLayout()).show(cardPanel, "game");
	}

	public void menu() {
		game.pause();
		creator.noLoop();
		((CardLayout) cardPanel.getLayout()).show(cardPanel, "menu");
	}

	public void creator() {
		JOptionPane.showMessageDialog(cardPanel, "For troop selection, use the right menu to select a troop to add. It will show up on the left side, under the highlighted troop.");
		JOptionPane.showMessageDialog(cardPanel, "Use the up and down arrows to change the highlighted troop. The number next to each troop shows how many of them will be spawned.");
		JOptionPane.showMessageDialog(cardPanel, "There will be approximately 160 milliseconds between each troop sent.");
		JOptionPane.showMessageDialog(cardPanel, "For the map creation, select the tile you would like to place from the right menu, then click where you want to place it.");
		JOptionPane.showMessageDialog(cardPanel, "Alternatively, you can click and drag to place that tile everywhere you pass over.");
		JOptionPane.showMessageDialog(cardPanel, "You can also change the dimensions of the map, or the number of grid spots there are, and the background image.");
		JOptionPane.showMessageDialog(cardPanel, "Once you are done, you can save it as a map file to be loaded from the menu.");
		creator.loop();
		((CardLayout) cardPanel.getLayout()).show(cardPanel, "creator");
	}

	public void quit() {
		System.exit(0);
	}

	public void loadMap() {
		game.loadMap();
	}
}