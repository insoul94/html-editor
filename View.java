package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    // Manages a list of undoable edits.
    private UndoManager undoManager = new UndoManager();
    // Listens to edits that can be undone.
    private UndoListener undoListener = new UndoListener(undoManager);

    // Main component contains of 2 editor tabs.
    private JTabbedPane tabbedPane = new JTabbedPane();
    // Component for visual html editing.
    private JTextPane htmlTextPane = new JTextPane();
    // Component for plain html editing with tags and content.
    private JEditorPane plainTextPane = new JEditorPane();


    public View()  {
        super();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public UndoListener getUndoListener() {
        return undoListener;
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
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);

        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        JScrollPane htmlTextScrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", htmlTextScrollPane);

        JScrollPane plainTextScrollPane = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Text", plainTextScrollPane);

        tabbedPane.setPreferredSize(new Dimension(400,300));
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
    }

    public void exit() {
        controller.exit();
    }

    public void selectedTabChanged() {

    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void undo() {
        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotRedoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected() {
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update() {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout() {
        JOptionPane.showMessageDialog(
                this,
                String.format("HTML Editor developed with openJDK 17 by J.Belan.\nv.%s 2022", controller.getAppVersion()),
                "About",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
