package su.sergey.contacts.birthdays.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import su.sergey.contacts.birthdays.model.BirthdaysContent;
import su.sergey.contacts.birthdays.view.BirthdaysForm;

public class NextAction extends AbstractAction {
    private BirthdaysForm form;
    private BirthdaysContent content;

    public NextAction(BirthdaysForm form, BirthdaysContent content) {
        this.form = form;
        this.content = content;
        putValue(Action.SHORT_DESCRIPTION, "Следующая страница");
        putValue(Action.LONG_DESCRIPTION, "Переход на следующую страницу");
        putValue(Action.NAME, "Вперед");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("PAGE_DOWN"));
    }

    public void actionPerformed(ActionEvent event) {
        content.next();
        form.adjustButtons();
    }
}
