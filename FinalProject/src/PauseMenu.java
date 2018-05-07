import java.awt.event.*;
import javax.swing.*;

public class PauseMenu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton resume;
	// add more buttons and graphics
	Window w;

	public PauseMenu(Window w) {
		this.w = w;
		resume = new JButton("Resume game");
		resume.addActionListener(this);
		add(resume);
		// initialize other buttons here
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(resume))
			w.play();
		else {
			// use the if statement above to check for other buttons
		}
	}
}