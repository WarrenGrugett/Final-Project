import java.awt.event.*;
import javax.swing.*;

/**
 * The main menu screen for the game.
 * 
 * @author Warren
 *
 */
public class GameMenu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton begin, quit;
	private Window w;

	public GameMenu(Window w) {
		this.w = w;
		begin = new JButton("Begin game");
		begin.addActionListener(this);
		add(begin);
		quit = new JButton("Quit game");
		quit.addActionListener(this);
		add(quit);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(begin))
			w.play();
		else if (e.getSource().equals(quit))
			w.quit();
	}
}