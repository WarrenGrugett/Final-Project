import java.awt.event.*;
import javax.swing.*;

/**
 * The main menu screen for the game.
 * @author Warren, Sepehr, Leo
 *
 */
public class GameMenu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton begin;
	// add more buttons and graphics
	Window w;

	public GameMenu(Window w) {
		this.w = w;
		begin = new JButton("Begin game");
		begin.addActionListener(this);
		add(begin);
		// initialize other buttons here
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(begin))
			w.changePanel();
		else {
			// use the if statement above to check for other buttons
		}
	}
}