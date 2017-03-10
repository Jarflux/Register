package be.skdebrug.view;

import be.skdebrug.model.Team;
import be.skdebrug.service.TeamService;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.JDOMException;
//import org.jdom2.input.SAXBuilder;
//import org.jdom2.output.Format;
//import org.jdom2.output.XMLOutputter;

/**
 * Developer: Ben Oeyen
 * Date: 10/03/2017
 */

public class TeamView {

    private static TeamView instance;
    JFrame frame = new JFrame("Cashier");

    public static TeamView getInstance() {
        if ((instance == null) || (instance.frame == null)) {
            instance = new TeamView();
        }
        //instance.frame.setVisible(true);
        return instance;
    }

	public void Init() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setLayout(new BorderLayout());
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setVisible(true);
        createTablePanel();
    }

    void createTablePanel() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        List<Team> teams = TeamService.getInstance().getAllTeamsOrderByNumberAsc();
        for (int counter = 0; counter < (teams.size()); counter = counter + 1) {
            JButton btnTable = new JButton();
            btnTable.setLayout(new BoxLayout(btnTable, BoxLayout.PAGE_AXIS));
            JLabel lblNumber = new JLabel(Integer.toString(teams.get(counter).getNumber()));
            lblNumber.setFont(new Font("test", 0, 40));
            lblNumber.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel lblTeamName = new JLabel(teams.get(counter).getName());
            lblTeamName.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnTable.add(lblNumber);
            btnTable.add(lblTeamName);
            btnTable.setPreferredSize(new Dimension(100, 75));

            final int finalCounter = counter;
            btnTable.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    OrderView.getInstance().Init(finalCounter);
                    OrderView.getInstance().open();
                    TeamView.getInstance().close();
                }
            });
            panel.add(btnTable);
        }
        frame.add(panel, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        JButton btnAdd = new JButton("Add Table");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.addActionListener(new ButtonListener());
        panel2.add(btnAdd);

        JButton btnSave = new JButton("Save");
        btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSave.addActionListener(new ButtonListener());
        panel2.add(btnSave);

        JButton btnExport = new JButton("Export");
        btnExport.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExport.addActionListener(new ButtonListener());
        panel2.add(btnExport);

        JButton btnBack = new JButton("Back");
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.addActionListener(new ButtonListener());
        panel2.add(btnBack);

        frame.add(panel2, BorderLayout.SOUTH);
        createStatisticsPanel();
        frame.getContentPane().repaint();
        frame.setVisible(true);
    }

    void createStatisticsPanel() {
        JPanel list = new JPanel();
        double total = 0.0;
        JTextArea statisticsArea = new JTextArea("Statistics \n-----------------------------\n");
        statisticsArea.setPreferredSize(new Dimension(400, 800));
        for (Team t : TeamService.getInstance().getAllTeamsOrderByTotalPriceDesc()) {
            statisticsArea.append(String.format("%-35s %5.2f euro\n", t.getName(), t.getTotalPrice()));
            total += t.getTotalPrice();
        }
        statisticsArea.append("                  -----------\n");
        statisticsArea.append(String.format("%-45s %5.2f euro\n", "totaal", total));
        
        list.add(statisticsArea);
        list.setPreferredSize(new Dimension(400, 800));
        frame.add(list, BorderLayout.EAST);
    }

    public void close() {
        frame.setVisible(false);
    }

    public void open() {
        createTablePanel();
        frame.setVisible(true);
    }

    public void dispose() {
        instance.frame.dispose();
        instance = null;
    }

    public void save() { // TODO Save Table as file
//        String path = System.getProperty("user.dir");
//        String filename = "Save.xml";
//
//        Element rootElement = new Element("Quiz");
//        for (Team t : TeamService.getInstance().getAllTeams()) {
//            Element tableElement = t.save();
//            rootElement.addContent(tableElement);
//        }
//        Document doc = new Document(rootElement);
//        XMLOutputter outputter = new XMLOutputter();
//        outputter.setFormat(Format.getPrettyFormat());
//        try {
//            outputter.output(doc, new FileWriter(new File(
//                    path, filename)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void load(String path) {
//        try {
//            SAXBuilder parser = new SAXBuilder();
//            Document doc = parser.build(path);
//            Element rootElement = doc.getRootElement();
//            @SuppressWarnings("unchecked")
//            List<Element> allChildren = (List<Element>) rootElement
//                    .getChildren();
//
//            for (int counter = 0; counter < allChildren.size(); counter++) {
//                Team tempTable = Team.Load(allChildren.get(counter));
//                TeamService.getInstance().addTeam(tempTable);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JDOMException e) {
//            e.printStackTrace();
//        }
//        Init();
    }

    void extractToCsv() { //
//		String path = System.getProperty("user.dir");
//		String filename = "data.csv";
//
//		try {
//			BufferedWriter out = new BufferedWriter(new FileWriter(new File(
//					path, filename)));
//			out.write("Teamnaam;Consumpties;Bedrag\n");
//			ArrayList<Team> tableListSorted = (ArrayList<Table>) tableList.clone();
//			Collections.sort(tableListSorted);
//			for (Team t: tableList){
//				out.write(t.extractToCsv());
//			}
//			out.close();
//		} catch (IOException e) {
//			System.out.println("Exception");
//		}
    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Save")) {
                save();
                // TODO Save confirmation message
            }
            if (e.getActionCommand().equals("Back")) {
                TeamService.getInstance().clean();
                MainView.getInstance();
                TeamView.getInstance().dispose();
            }
            if (e.getActionCommand().equals("Export")) {
                extractToCsv();
            }
            if (e.getActionCommand().equals("Add Table")) {
                TeamService.getInstance().addTeam(new Team(Integer.parseInt(JOptionPane
                        .showInputDialog(null, "Table Number?",
                        "Enter a number here",
                        JOptionPane.QUESTION_MESSAGE)), JOptionPane
                        .showInputDialog(null, "Teamname?",
                        "Enter team name here",
                        JOptionPane.QUESTION_MESSAGE)));
                createTablePanel();
                frame.setVisible(true);
            }
        }
    }
}
