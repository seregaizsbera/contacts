package su.sergey.contacts.birthdays.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import su.sergey.contacts.birthdays.model.BirthdaysContent;
import su.sergey.contacts.birthdays.view.BirthdaysForm;

public class PrevAction extends AbstractAction {
    private BirthdaysForm form;
    private BirthdaysContent content;

    public PrevAction(BirthdaysForm form, BirthdaysContent content) {
        this.form = form;
        this.content = content;
        putValue(Action.SHORT_DESCRIPTION, "Предыдущая страница");
        putValue(Action.LONG_DESCRIPTION, "Переход на предыдущую страницу");
        putValue(Action.NAME, "Назад");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("PAGE_UP"));
    }

    public void actionPerformed(ActionEvent event) {
        content.prev();
        form.adjustButtons();
    }
}
