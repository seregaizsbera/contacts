package su.sergey.contacts.birthdays.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
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
public class NextAction extends AbstractAction {
    private BirthdaysForm form;
    private BirthdaysContent content;

    public NextAction(BirthdaysForm form, BirthdaysContent content) {
        this.form = form;
        this.content = content;
        putValue(Action.SHORT_DESCRIPTION, "Следующая страница");
        putValue(Action.LONG_DESCRIPTION, "Переход на следующую страницу");
        putValue(Action.NAME, "Вперед");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(0, KeyEvent.VK_RIGHT));
    }

    public void actionPerformed(ActionEvent event) {
        content.next();
        form.adjustButtons();
    }
}
