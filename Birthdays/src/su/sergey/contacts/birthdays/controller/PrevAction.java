package su.sergey.contacts.birthdays.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import su.sergey.contacts.birthdays.model.BirthdaysContent;
import su.sergey.contacts.birthdays.view.BirthdaysForm;

/*
 * Created by IntelliJ IDEA.
 * User: sergey
 * Date: 06.12.2003
 * Time: 1:12:09
 * To change template for new class use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
public class PrevAction extends AbstractAction {
    private BirthdaysForm form;
    private BirthdaysContent content;

    public PrevAction(BirthdaysForm form, BirthdaysContent content) {
        this.form = form;
        this.content = content;
        putValue(Action.SHORT_DESCRIPTION, "Предыдущая страница");
        putValue(Action.LONG_DESCRIPTION, "Переход на предыдущую страницу");
        putValue(Action.NAME, "Назад");
        putValue(Action.MNEMONIC_KEY, "Shift+Right");
        putValue(Action.ACTION_COMMAND_KEY, "Shift+Right");
    }

    public void actionPerformed(ActionEvent event) {
        content.prev();
        form.adjustButtons();
    }
}
