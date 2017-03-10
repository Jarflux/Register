package be.skdebrug.view;

import be.skdebrug.model.Order;
import be.skdebrug.service.TeamService;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Developer: Ben Oeyen
 * Date: 10/03/2017
 */

public class OrderView {

    private static OrderView instance;
    JFrame frame = new JFrame("Cashier");
    int fTeamId;
    TeamService teamService = TeamService.getInstance();

    public static OrderView getInstance() {
        if ((instance == null) || (instance.frame == null)) {
            instance = new OrderView();
        }
        return instance;
    }

    public void Init(int teamId) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        fTeamId = teamId;
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true); 
        frame.setVisible(true);
        createOrderPanel();
    }

    void createOrderPanel() { //TODO redesign order panel
        frame.getContentPane().removeAll();
        JPanel panel1 = new JPanel();

        JLabel teamName = new JLabel(teamService.getTeam(fTeamId).getName());
        panel1.add(teamName);
        frame.add(panel1, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel panel3 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JLabel lblHeader1 = new JLabel("");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 1;
        panel3.add(lblHeader1, c);


        JLabel lblHeader2 = new JLabel(String.format("%15s", "Consumpties"));
        c.gridx = 1;
        panel3.add(lblHeader2, c);


        JLabel lblHeader3 = new JLabel(String.format("%15s", "Bedrag"));
        c.gridx = 2;
        panel3.add(lblHeader3, c);


        int counter = 0;
        for (Order o : teamService.getAllTeams().get(fTeamId).getAllOrders()) {
            counter++;

            JLabel lblOrder1 = new JLabel(counter + ")");

            c.gridy = counter;
            c.gridx = 0;
            panel3.add(lblOrder1, c);

            JLabel lblOrder2 = new JLabel(String.format("%15d", o.getTotalAmountBeverages()));
            c.gridx = 1;
            panel3.add(lblOrder2, c);

            JLabel lblOrder3 = new JLabel(String.format("%15.2feuro  ", o.getTotalPrice()));
            c.gridx = 2;
            panel3.add(lblOrder3, c);


            JButton btnOrder = new JButton("edit");
            final int finalCounter = counter - 1;
            btnOrder.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BeverageView.getInstance().Init(fTeamId, finalCounter);
                    OrderView.getInstance().close();
                }
            });
            c.gridx = 3;
            panel3.add(btnOrder, c);
        }

        JLabel lblFooter1 = new JLabel("");
        c.gridy++;
        c.gridx = 0;
        panel3.add(lblFooter1, c);

        JLabel lblFooter2 = new JLabel(String.format("%15d", teamService.getTeam(fTeamId).getTotalAmountDrinks()));
        c.gridx = 1;
        panel3.add(lblFooter2, c);

        JLabel lblFooter3 = new JLabel(String.format("%15.2feuro  ", teamService.getTeam(fTeamId).getTotalPrice()));
        c.gridx = 2;
        panel3.add(lblFooter3, c);

        panel.add(panel3);
        frame.add(panel);
        JPanel panel2 = new JPanel();
        JButton btnAdd = new JButton("Add Order");
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.addActionListener(new ButtonListener());
        panel2.add(btnAdd);

        JButton btnBack = new JButton("Back");
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.addActionListener(new ButtonListener());
        panel2.add(btnBack);

        frame.add(panel2, BorderLayout.SOUTH);
    }

    public void close() {
        frame.setVisible(false);
    }

    public void dispose() {
        instance.frame.dispose();
        instance = null;
    }

    public void open() {
        createOrderPanel();
        frame.setVisible(true);
    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Add Order")) {
                BeverageView.getInstance().Init(fTeamId, null);
                OrderView.getInstance().close();
            }
            if (e.getActionCommand().equals("Back")) {
                TeamView.getInstance().open();
                OrderView.getInstance().dispose();
            }

        }
    }
}
