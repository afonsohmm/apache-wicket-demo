package com.jjdev.tasks.login;

import com.jjdev.tasks.tasks.JListTask;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

/**
 *
 * @author jgilson
 */
public class JLogin extends WebPage {

    private TextField txtEmail;
    private TextField txtPassword;

    public JLogin() {
        initComponents();
    }

    private void initComponents() {

        Form frmLogin = new Form("frmLogin") {
            @Override
            protected void onSubmit() {
                //login here
                setResponsePage(JListTask.class);
            }
        };

        txtEmail = new TextField("txtEmail", new Model(""));
        frmLogin.add(txtEmail);

        txtPassword = new TextField("txtPassword", new Model(""));
        frmLogin.add(txtPassword);

        add(frmLogin);
    }

}
