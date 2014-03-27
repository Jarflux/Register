package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;


public class MainView {
	private static MainView instance;
	JFrame frame = new JFrame("Cashier");

	public static MainView getInstance() {
		if (instance == null) {
			instance = new MainView();
		}
		return instance;
	}

	private MainView() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame.setLayout(new BorderLayout());

		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JButton btnNew = new JButton("New");
		btnNew.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNew.addActionListener(new ButtonListener());
		panel.add(btnNew);

		JButton btnLoad = new JButton("Load");
		btnLoad.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLoad.addActionListener(new ButtonListener());
		panel.add(btnLoad);

		frame.add(panel, BorderLayout.CENTER);

		JPanel panel2 = new JPanel();
		// INTRO LOGO PLAATSEN
		frame.add(panel2, BorderLayout.NORTH);
	}
	
	public static void close(){
		instance.frame.dispose();
		instance = null;
	};
	
}
class ButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("New")) {
			String path = System.getProperty("user.dir");
			String filename = "Save.xml";
			if ((new File(path, filename)).exists()) {
				if (JOptionPane.YES_OPTION == JOptionPane
						.showConfirmDialog(
								null,
								"Do you want to overwrite previous file?",
								"window title", JOptionPane.YES_NO_OPTION)) {
					TeamView.getInstance().Init();
					MainView.close();
				}
			} else {
				TeamView.getInstance().Init();
				MainView.close();
			}
			// TODO What if NO_OPTION
		}
		if (e.getActionCommand().equals("Load")) { //TODO Impliment Filechooser
			//JFileChooser fileopen = new JFileChooser();
			String path = System.getProperty("user.dir") + "/Save.xml";
			TeamView.getInstance().load(path);
			MainView.close();
		}
	}
	
}
