package su.sergey.contacts.birthdays.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import su.sergey.contacts.birthdays.model.BirthdaysContent;
import su.sergey.contacts.birthdays.view.BirthdaysForm;

public class FirstAction extends AbstractAction {
    private BirthdaysForm form;
    private BirthdaysContent content;

    public FirstAction(BirthdaysForm form, BirthdaysContent content) {
        this.form = form;
        this.content = content;
        putValue(Action.SHORT_DESCRIPTION, "Первая страница");
        putValue(Action.LONG_DESCRIPTION, "Переход на первую страницу");
        putValue(Action.NAME, "Начало");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("HOME"));
    }

    public void actionPerformed(ActionEvent event) {
        content.goTo(0);
        form.adjustButtons();
    }
}
