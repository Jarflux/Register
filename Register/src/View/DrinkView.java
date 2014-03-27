package View;

import Controller.PriceController;
import Controller.TeamController;
import Model.Drink;
import Model.Order;
import View.OrderView;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class DrinkView {

    private static DrinkView instance;
    JFrame frame = new JFrame("Cashier");
    PriceController priceController = PriceController.getInstance();
    TeamController teamController = TeamController.getInstance();
    int fTeamId;
    int fOrderId;
    Boolean newOrder = false;

    public static DrinkView getInstance() {
        if (instance == null) {
            instance = new DrinkView();
        }
        return instance;
    }

    public void setParameters(int teamId, Integer orderId) {
    }

    public void Init(int teamId, Integer orderId) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fTeamId = teamId;
        if (orderId == null) {
            newOrder = true;
            Order order = new Order();
            teamController.getTeam(teamId).addOrder(order);
            fOrderId = teamController.getTeam(teamId).getAllOrders().indexOf(order);
        } else {
            fOrderId = orderId;
        }

        frame.setLayout(new BorderLayout());
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true); 
        frame.setVisible(true);

        createBeverageOrderList();
        createBeveragePanel();
    }

    public void close() {
        frame.setVisible(false);
    }

    public void dispose() {
        instance.frame.dispose();
        instance = null;
    }

    void createBeveragePanel() {
        frame.getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5, 10, 10));

        for (int counter = 0; counter < priceController.getSize() - 1; counter++) {
            JButton btnBeverage = new JButton();
            btnBeverage.setLayout(new BoxLayout(btnBeverage, BoxLayout.PAGE_AXIS));
            String path = "/images/"
                    + priceController.getDrinkByIndex(counter)
                    .getPicture() + ".jpg";
            JLabel lblImage = new JLabel(new ImageIcon(getClass().getResource(path)));
            lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel lblName = new JLabel(priceController.getDrinkByIndex(counter).getName());
            lblName.setFont(new Font("test", Font.BOLD, 11));
            lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnBeverage.add(lblImage);
            btnBeverage.add(new JLabel(" "));
            btnBeverage.add(lblName);
            btnBeverage.setPreferredSize(new Dimension(100, 75));

            final String Name = priceController.getDrinkByIndex(counter).getName();
            btnBeverage.addMouseListener(new MouseAdapter() {
                boolean pressed;

                @Override
                public void mousePressed(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    button.getModel().setArmed(true);
                    button.getModel().setPressed(true);
                    pressed = true;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    JButton button = (JButton) e.getSource();
                    button.getModel().setArmed(false);
                    button.getModel().setPressed(false);

                    if (pressed) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            teamController.getTeam(fTeamId).getOrder(fOrderId).removeDrink(priceController.getDrinkByName(Name));
                        } else {
                            teamController.getTeam(fTeamId).getOrder(fOrderId).addDrink(priceController.getDrinkByName(Name));
                        }
                        createBeveragePanel();
                    }
                    pressed = false;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    pressed = false;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    pressed = true;
                }
            });

            panel.add(btnBeverage);
        }
        frame.add(panel, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();

        JButton btnOk = new JButton("Confirm");
        btnOk.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOk.addActionListener(new ButtonListener());
        panel2.add(btnOk);

        JButton btnCancel = new JButton("Back");
        btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancel.addActionListener(new ButtonListener());
        panel2.add(btnCancel);
        frame.add(panel2, BorderLayout.SOUTH);
        createBeverageOrderList();
        frame.getContentPane().repaint();
        frame.setVisible(true);
    }

    void createBeverageOrderList() {
        JPanel list = new JPanel();
        double total = 0;
        JTextArea beverageOrderList = new JTextArea("Bestelling \n-----------------------------\n");

        for (Drink d : teamController.getTeam(fTeamId).getOrder(fOrderId).getAllDrinks()) {
            beverageOrderList.append(String.format("%2d x  %-15s %5.2f euro\n", 1, d.getName(), d.getPrice()));
            total += d.getPrice();
        }
        beverageOrderList.append("                  -----------\n");
        beverageOrderList.append(String.format("%20s %6.2f euro\n", "Totaal", total));

        list.add(beverageOrderList);
        frame.add(list, BorderLayout.EAST);
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Confirm")) {
                OrderView.getInstance().open();
                DrinkView.getInstance().dispose();
                TeamView.getInstance().save();
            }
            if (e.getActionCommand().equals("Back")) {
                OrderView.getInstance().open();
                DrinkView.getInstance().dispose();
            }
        }
    }
}
