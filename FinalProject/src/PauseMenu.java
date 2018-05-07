import java.awt.event.*;
import javax.swing.*;

public class PauseMenu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton resume, menu, quit;
	// add more buttons and graphics
	Window w;

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
		// initialize other buttons here
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(resume))
			w.play();
		else if (e.getSource().equals(menu))
			w.menu();
		else if (e.getSource().equals(quit))
			System.exit(0);
	}
}