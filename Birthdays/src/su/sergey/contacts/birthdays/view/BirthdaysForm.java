package su.sergey.contacts.birthdays.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import su.sergey.contacts.birthdays.controller.FirstAction;
import su.sergey.contacts.birthdays.controller.LastAction;
import su.sergey.contacts.birthdays.controller.NextAction;
import su.sergey.contacts.birthdays.controller.PrevAction;
import su.sergey.contacts.birthdays.model.BirthdaysContent;
import su.sergey.contacts.birthdays.model.BirthdaysTableModel;
import su.sergey.contacts.person.valueobjects.Person2;
import su.sergey.contacts.util.Util;

public class BirthdaysForm extends JDialog {
    private BirthdaysContent content;
    private JButton firstButton;
    private JButton lastButton;
    private JButton prevButton;
    private JButton nextButton;
    private Action firstAction;
    private Action lastAction;
    private Action prevAction;
    private Action nextAction;
    private JLabel pageLabel;
    private JTable birthdaysTable;
    private BirthdaysTableModel birthdaysTableModel;

    public BirthdaysForm(BirthdaysContent content) {
        this.content = content;
        int width = 640;
        int height = 480;
        setBounds(Util.centerOnScreen(width, height));
        setModal(true);
        setVisible(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        firstAction = new FirstAction(this, content);
        lastAction = new LastAction(this, content);
        prevAction = new PrevAction(this, content);
        nextAction = new NextAction(this, content);

        firstButton = new JButton(firstAction);
        lastButton = new JButton(lastAction);
        prevButton = new JButton(prevAction);
        nextButton = new JButton(nextAction);
        pageLabel = new JLabel("XXX");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(firstButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(pageLabel);
        buttonPanel.add(nextButton);
        buttonPanel.add(lastButton);

        birthdaysTableModel = new BirthdaysTableModel();
        birthdaysTable = new JTable(birthdaysTableModel);

        JComponent panel = (JComponent) getContentPane();
        panel.setLayout(new BorderLayout());

        panel.add(buttonPanel, BorderLayout.SOUTH);

        birthdaysTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        birthdaysTable.getTableHeader().setResizingAllowed(true);
        birthdaysTable.getTableHeader().setReorderingAllowed(true);
        birthdaysTable.setColumnSelectionAllowed(false);
        birthdaysTable.setRowSelectionAllowed(true);
        birthdaysTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        birthdaysTable.setRowHeight(birthdaysTable.getRowHeight() + 20);
        birthdaysTable.setBackground(getBackground());
        panel.add(new JScrollPane(birthdaysTable), BorderLayout.CENTER);

        setTitle("Обрати внимание на дни рождения следующих личностей");
        content.goTo(0);
        adjustButtons();

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getModifiers() == 0 && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    dispose();
                    e.consume();
                }
            }
        });
    }

    public void adjustButtons() {
        Person2 persons[] = content.current();
        birthdaysTableModel.setPersons(persons);
        prevAction.setEnabled(content.hasPrev());
        nextAction.setEnabled(content.hasNext());
        pageLabel.setText(Integer.toString(content.getCurrentPage() + 1));
        repaint();
    }
}
