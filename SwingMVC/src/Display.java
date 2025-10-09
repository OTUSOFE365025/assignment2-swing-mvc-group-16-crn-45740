import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Display {
    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> list;
    private JLabel subtotalLabel;

    public Display(String title) {
        frame = new JFrame(title);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocation(50, 50);

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        JScrollPane sp = new JScrollPane(list);

        subtotalLabel = new JLabel("Subtotal: $0.00");

        frame.getContentPane().add(sp, BorderLayout.CENTER);
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(subtotalLabel, BorderLayout.CENTER);
        frame.getContentPane().add(bottom, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public void addItem(String text) {
        listModel.addElement(text);
    }

    public void setSubtotal(double subtotal) {
        subtotalLabel.setText(String.format("Subtotal: $%.2f", subtotal));
    }
}
