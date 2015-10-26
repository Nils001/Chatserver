
/**
*Text genereted by Simple GUI Extension for BlueJ
*/
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.border.Border;
import javax.swing.*;


public class GUI_project extends JFrame {

	private JMenuBar menuBar;
	private JButton button2;
	private JList list2;
	private JTextArea textarea2;
	private JTextField textfield2;

	//Constructor 
	public GUI_project(){

		this.setTitle("GUI_project");
		this.setSize(611,536);
		//menu generate method
		generateMenu();
		this.setJMenuBar(menuBar);

		//pane with null layout
		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(611,536));
		contentPane.setBackground(new Color(192,192,192));


		button2 = new JButton();
		button2.setBounds(383,481,90,35);
		button2.setBackground(new Color(214,217,223));
		button2.setForeground(new Color(0,0,0));
		button2.setEnabled(true);
		button2.setFont(new Font("sansserif",0,12));
		button2.setText("Send");
		button2.setVisible(true);

		list2 = new JList();
		list2.setBounds(489,142,98,373);
		list2.setBackground(new Color(255,255,255));
		list2.setForeground(new Color(0,0,0));
		list2.setEnabled(true);
		list2.setFont(new Font("sansserif",0,12));
		list2.setVisible(true);

		textarea2 = new JTextArea();
		textarea2.setBounds(20,140,454,333);
		textarea2.setBackground(new Color(255,255,255));
		textarea2.setForeground(new Color(0,0,0));
		textarea2.setEnabled(true);
		textarea2.setFont(new Font("sansserif",0,12));
		textarea2.setText("");
		textarea2.setBorder(BorderFactory.createBevelBorder(1));
		textarea2.setVisible(true);

		textfield2 = new JTextField();
		textfield2.setBounds(21,482,354,36);
		textfield2.setBackground(new Color(255,255,255));
		textfield2.setForeground(new Color(0,0,0));
		textfield2.setEnabled(true);
		textfield2.setFont(new Font("sansserif",0,12));
		textfield2.setText("Enter Message");
		textfield2.setVisible(true);

		//adding components to contentPane panel
		contentPane.add(button2);
		contentPane.add(list2);
		contentPane.add(textarea2);
		contentPane.add(textfield2);

		//adding panel to JFrame and seting of window position and close operation
		this.add(contentPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}

	//method for generate menu
	public void generateMenu(){
		menuBar = new JMenuBar();

		JMenu file = new JMenu("File");
		JMenu tools = new JMenu("Tools");
		JMenu help = new JMenu("Help");

		JMenuItem open = new JMenuItem("Open   ");
		JMenuItem save = new JMenuItem("Save   ");
		JMenuItem exit = new JMenuItem("Exit   ");
		JMenuItem preferences = new JMenuItem("Preferences   ");
		JMenuItem about = new JMenuItem("About   ");


		file.add(open);
		file.add(save);
		file.addSeparator();
		file.add(exit);
		tools.add(preferences);
		help.add(about);

		menuBar.add(file);
		menuBar.add(tools);
		menuBar.add(help);
	}



	 public static void main(String[] args){
		System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI_project();
			}
		});
	}

}