package su.sergey.contacts.birthdays.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import su.sergey.contacts.birthdays.controller.ExitAction;
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
    private Action exitAction;
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
	exitAction = new ExitAction(this);

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
	
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	ActionMap actionMap = panel.getActionMap();
	
	registerAction(inputMap, actionMap, firstAction);
	registerAction(inputMap, actionMap, lastAction);
	registerAction(inputMap, actionMap, prevAction);
	registerAction(inputMap, actionMap, nextAction);
	registerAction(inputMap, actionMap, exitAction);
	
	registerAction(inputMap, actionMap, firstAction, KeyStroke.getKeyStroke("F1"));
	registerAction(inputMap, actionMap, lastAction, KeyStroke.getKeyStroke("F2"));
	registerAction(inputMap, actionMap, prevAction, KeyStroke.getKeyStroke("F3"));
	registerAction(inputMap, actionMap, nextAction, KeyStroke.getKeyStroke("F4"));
	registerAction(inputMap, actionMap, exitAction, KeyStroke.getKeyStroke("F5"));
	
        setTitle("Обрати внимание на дни рождения следующих личностей");
        content.goTo(0);
        adjustButtons();
    }

    public void adjustButtons() {
        Person2 persons[] = content.current();
        birthdaysTableModel.setPersons(persons);
        firstAction.setEnabled(content.hasPrev());
        prevAction.setEnabled(content.hasPrev());
        nextAction.setEnabled(content.hasNext());
        lastAction.setEnabled(content.hasNext());
        pageLabel.setText(Integer.toString(content.getCurrentPage() + 1));
        repaint();
    }
    
    private void registerAction(InputMap inputMap, ActionMap actionMap, Action action, KeyStroke keyStroke) {
	Object actionMapKey = action.getValue(Action.NAME);
	if (keyStroke == null || actionMapKey == null) {
	    return;
	}
        inputMap.put(keyStroke, actionMapKey);
	actionMap.put(actionMapKey, action);
	InputMap tableInputMap = SwingUtilities.getUIInputMap(birthdaysTable, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	if (tableInputMap == null) {
	    return;
	}
	if (tableInputMap.get(keyStroke) != null) {
            tableInputMap.remove(keyStroke);
	}
    }
    
    private void registerAction(InputMap inputMap, ActionMap actionMap, Action action) {
        KeyStroke keyStroke = (KeyStroke) action.getValue(Action.ACCELERATOR_KEY);
	registerAction(inputMap, actionMap, action, keyStroke);
    }
}
