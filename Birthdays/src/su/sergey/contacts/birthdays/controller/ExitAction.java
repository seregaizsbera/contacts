package su.sergey.contacts.birthdays.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import su.sergey.contacts.birthdays.view.BirthdaysForm;

public class ExitAction extends AbstractAction {
    private BirthdaysForm form;

    public ExitAction(BirthdaysForm form) {
        this.form = form;
        putValue(Action.SHORT_DESCRIPTION, "Выход");
        putValue(Action.LONG_DESCRIPTION, "Завершить работу с программой");
        putValue(Action.NAME, "Выход");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ESCAPE"));
    }

    public void actionPerformed(ActionEvent event) {
        form.dispose();
    }
}
