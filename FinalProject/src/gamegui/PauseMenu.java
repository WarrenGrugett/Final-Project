package gamegui;
import java.awt.event.*;
import javax.swing.*;

/**
 * PauseMenu GUI
 * 
 * @author Warren *implements ActionListener *extends JPanel
 *
 */
public class PauseMenu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton resume, menu, quit;
	private Window w;

	/**
	 * 
	 * @param w
	 *            The window to display the menu on
	 */
	public PauseMenu(Window w) {
		this.w = w;
		resume = new JButton("Resume game");
		resume.addActionListener(this);
		add(resume);
		menu = new JButton("Return to home screen");
		menu.addActionListener(this);
		add(menu);
		quit = new JButton("Quit game");
		quit.addActionListener(this);
		add(quit);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(resume))
			w.play();
		else if (e.getSource().equals(menu))
			w.menu();
		else if (e.getSource().equals(quit))
			w.quit();
	}
}