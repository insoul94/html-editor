package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    // Component contains of 2 editor panes.
    private JTabbedPane tabbedPane = new JTabbedPane();
    // Component for visual html editing.
    private JTextPane htmlTextPane = new JTextPane();
    // Component for plain html editing with tags and content.
    private JEditorPane plainTextPane = new JEditorPane();

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }

    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public void initMenuBar() {

    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        JScrollPane htmlTextScrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML", htmlTextScrollPane);

        JScrollPane plainTextScrollPane = new JScrollPane(plainTextPane);
        tabbedPane.add("Text", plainTextScrollPane);

        tabbedPane.setPreferredSize(new Dimension(400,300));
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
    }

    public void exit() {
        controller.exit();
    }

    public void selectedTabChanged() {

    }
}
