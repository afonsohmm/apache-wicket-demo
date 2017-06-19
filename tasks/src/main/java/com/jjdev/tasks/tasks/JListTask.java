package com.jjdev.tasks.tasks;

import com.leucotron.tarefasmodel.JServiceLocator;
import com.leucotron.tarefasmodel.entity.JTask;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author jgilson
 */
public class JListTask extends WebPage {

    private WebMarkupContainer ctnList;

    private JTask task;
    private List tasks;

    public JListTask() {
        task = new JTask();
        tasks = new ArrayList<>();
        initComponents();
        loadData();
    }

    private void loadData() {
        tasks.clear();
        for (JTask taskItem : JServiceLocator.getTaskService().readByCriteria(null)) {
            tasks.add(taskItem);
        }
    }

    private void initComponents() {

        Form frmTask = new Form("frmTask") {
            @Override
            protected void onSubmit() {
                task.setDate((new Date()).getTime());
                task.setDone(Boolean.FALSE);
                JServiceLocator.getTaskService().create(task);

                setResponsePage(JListTask.class);
            }
        };

        frmTask.add(new TextField("txtName", new PropertyModel(task, "name")));

        add(frmTask);

        //--------
        ctnList = new WebMarkupContainer("ctnList");
        ctnList.setOutputMarkupId(true);
        
        ctnList.add(new DataView("grdTasks", new ListDataProvider(tasks)) {
            @Override
            protected void populateItem(Item item) {
                final JTask task = (JTask) item.getModelObject();

                item.add(new Label("lblId", task.getId()));
                item.add(new Label("lblName", task.getName()));
                item.add(new Label("lblDate", task.getDate()));
                item.add(new AjaxCheckBox("chkDone", new PropertyModel(task, "done")) {
                    @Override
                    protected void onUpdate(AjaxRequestTarget art) {
                        JServiceLocator.getTaskService().update(task);
                    }
                });
                item.add(new AjaxLink("lnkDelete") {
                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        JServiceLocator.getTaskService().delete(task.getId());
                        loadData();
                        target.add(ctnList);
                    }
                });
            }
        });
        
        add(ctnList);
    }

}
