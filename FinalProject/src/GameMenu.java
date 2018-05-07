import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The main menu screen for the game.
 * @author Warren, Sepehr, Leo
 *
 */
public class GameMenu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton begin, quit;
	// add more buttons and graphics
	Window w;

	public GameMenu(Window w) {
		this.w = w;
		begin = new JButton("Begin game");
		begin.addActionListener(this);
		add(begin);
		quit = new JButton("Quit game");
		quit.addActionListener(this);
		add(quit);
		// initialize other buttons here
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(begin))
			w.play();
		else if (e.getSource().equals(quit))
			System.exit(0);
	}
}