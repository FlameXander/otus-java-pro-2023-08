package composite.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import java.awt.Container;

public class SwingDemo extends JFrame {

    public SwingDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        /*
         setContentPane(Container) принимает Container, поэтому может принять
         любой элемент, реализующий этот интерфейс
         */

        // Это может быть любой элемент от JComponent (например простой элемент JLabel)
        // setContentPane(new JLabel("This is a label"));

        // или простой элемент JTextField
        // setContentPane(new JTextField(20));

        /*
         или сложный элемент, содержащий другие элементы (например JPanel)
         это компонент, который позволяет со сложным объектом работать точно
         также как и с простым (разницы нет)
         */
        Container component = createComponent();
        setContentPane(component);

        /*
         При отрисовке UI Swing не знает какой именно объект он отрисовывает.
         Ему достаточно вызывать метод paint() у простого элемента или компонента
         */
        pack();
        setLocationRelativeTo(null);
    }

    private static JPanel createComponent() {
        JPanel panel = new JPanel(new SpringLayout());

        addLabelTextField("Name", panel);
        addLabelTextField("Fax", panel);
        addLabelTextField("Email", panel);
        addLabelTextField("Address", panel);

        SpringUtilities.makeCompactGrid(panel,
                                        4, 2,   // rows, cols
                                        6, 6,   // initX, initY
                                        6, 6);  // xPad, yPad

        panel.setOpaque(true);

        return panel;
    }

    private static void addLabelTextField(String title, JPanel panel) {
        JLabel label = new JLabel(title + ':', JLabel.TRAILING);
        JTextField textField = new JTextField(20);

        panel.add(label);
        panel.add(textField);

        label.setLabelFor(textField);
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(() -> new SwingDemo().setVisible(true));
    }
}
