import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gui
{
    private JFrame frame;
    private ChatClient cc;
    private JTextArea textArea;

    /**
     * Launch the application.
     */
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
            {
                public void run() 
                {
                    try 
                    {
                        gui window = new gui();
                        window.frame.setVisible(true);
                    } catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                }
            });
    }

    /**
     * Create the application.
     */
    public gui() 
    {
        cc = new ChatClient();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() 
    {
        frame = new JFrame();
        frame.setBounds(100, 100, 720, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setToolTipText("");
        tabbedPane.setBorder(null);
        tabbedPane.setBounds(22, 11, 495, 380);
        frame.getContentPane().add(tabbedPane);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tabbedPane.addTab("Chat", null, scrollPane, null);
        tabbedPane.setEnabledAt(0, true);

        final JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        scrollPane.setViewportView(textArea);

        JList list = new JList();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        list.setBounds(542, 32, 152, 359);
        frame.getContentPane().add(list);

        final JTextArea editorPane = new JTextArea();
        editorPane.setBounds(22, 402, 495, 77);
        frame.getContentPane().add(editorPane);

        JButton btnNewButton = new JButton("Senden");
        btnNewButton.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    String a = editorPane.getText();
                    cc.nachrichtVersenden(a);
                }
            });
        btnNewButton.setBounds(542, 402, 152, 23);
        frame.getContentPane().add(btnNewButton);

        JButton btnQuit = new JButton("Beenden");
        btnQuit.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent arg0) 
                {
                    cc.nachrichtVersenden("!quit");
                }
            });
        btnQuit.setBounds(542, 436, 152, 23);
        frame.getContentPane().add(btnQuit);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnVerbindung = new JMenu("Verbindung");
        menuBar.add(mnVerbindung);

        JMenuItem mntmVerbinden = new JMenuItem("Verbinden");
        mntmVerbinden.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                }
            });
        mnVerbindung.add(mntmVerbinden);
    }
}
