package su.sergey.contacts.birthdays.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import su.sergey.contacts.birthdays.model.BirthdaysContent;
import su.sergey.contacts.birthdays.view.BirthdaysForm;

public class LastAction extends AbstractAction {
    private BirthdaysForm form;
    private BirthdaysContent content;

    public LastAction(BirthdaysForm form, BirthdaysContent content) {
        this.form = form;
        this.content = content;
        putValue(Action.SHORT_DESCRIPTION, "Последняя страница");
        putValue(Action.LONG_DESCRIPTION, "Переход на последнюю страницу");
        putValue(Action.NAME, "Kонец");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("END"));
    }

    public void actionPerformed(ActionEvent event) {
        content.goTo(content.getNumberOfPages() - 1);
        form.adjustButtons();
    }
}
