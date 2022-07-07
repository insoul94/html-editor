package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.actions.*;
import com.javarush.task.task32.task3209.listeners.TextEditMenuListener;
import com.javarush.task.task32.task3209.listeners.UndoMenuListener;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuHelper {
    public static JMenuItem addMenuItem(JMenu parent, String text, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(actionListener);
        parent.add(menuItem);
        return menuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, String text, Action action) {
        JMenuItem menuItem = addMenuItem(parent, action);
        menuItem.setText(text);
        return menuItem;
    }

    public static JMenuItem addMenuItem(JMenu parent, Action action) {
        JMenuItem menuItem = new JMenuItem(action);
        parent.add(menuItem);
        return menuItem;
    }

    public static void initFileMenu(View view, JMenuBar menuBar) {
        JMenu fileMenu = new JMenu(MenuCommand.FILE);
        menuBar.add(fileMenu);

        addMenuItem(fileMenu, MenuCommand.NEW, view);
        addMenuItem(fileMenu, MenuCommand.OPEN, view);
        addMenuItem(fileMenu, MenuCommand.SAVE, view);
        addMenuItem(fileMenu, MenuCommand.SAVE_AS, view);
        fileMenu.addSeparator();
        addMenuItem(fileMenu, MenuCommand.EXIT, view);
    }

    public static void initEditMenu(View view, JMenuBar menuBar) {
        JMenu editMenu = new JMenu(MenuCommand.EDIT);
        menuBar.add(editMenu);

        JMenuItem undoItem = addMenuItem(editMenu, MenuCommand.UNDO, new UndoAction(view));
        JMenuItem redoItem = addMenuItem(editMenu, MenuCommand.REDO, new RedoAction(view));
        addMenuItem(editMenu, MenuCommand.CUT, new DefaultEditorKit.CutAction());
        addMenuItem(editMenu, MenuCommand.COPY, new DefaultEditorKit.CopyAction());
        addMenuItem(editMenu, MenuCommand.PASTE, new DefaultEditorKit.PasteAction());

        editMenu.addMenuListener(new UndoMenuListener(view, undoItem, redoItem));
    }

    public static void initStyleMenu(View view, JMenuBar menuBar) {
        JMenu styleMenu = new JMenu(MenuCommand.STYLE);
        menuBar.add(styleMenu);

        addMenuItem(styleMenu, MenuCommand.BOLD, new StyledEditorKit.BoldAction());
        addMenuItem(styleMenu, MenuCommand.UNDERLINE, new StyledEditorKit.UnderlineAction());
        addMenuItem(styleMenu, MenuCommand.ITALIC, new StyledEditorKit.ItalicAction());

        styleMenu.addSeparator();

        addMenuItem(styleMenu, MenuCommand.SUBSCRIPT, new SubscriptAction());
        addMenuItem(styleMenu, MenuCommand.SUPERSCRIPT, new SuperscriptAction());
        addMenuItem(styleMenu, MenuCommand.STRIKE_THROUGH, new StrikeThroughAction());

        styleMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initAlignMenu(View view, JMenuBar menuBar) {
        JMenu alignMenu = new JMenu(MenuCommand.ALIGN);
        menuBar.add(alignMenu);

        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction(MenuCommand.ALIGN_LEFT, StyleConstants.ALIGN_LEFT));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction(MenuCommand.ALIGN_CENTER, StyleConstants.ALIGN_CENTER));
        addMenuItem(alignMenu, new StyledEditorKit.AlignmentAction(MenuCommand.ALIGN_RIGHT, StyleConstants.ALIGN_RIGHT));

        alignMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initColorMenu(View view, JMenuBar menuBar) {
        JMenu colorMenu = new JMenu(MenuCommand.COLOR);
        menuBar.add(colorMenu);

        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction(MenuCommand.COLOR_RED, Color.red));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction(MenuCommand.COLOR_ORANGE, Color.orange));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction(MenuCommand.COLOR_YELLOW, Color.yellow));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction(MenuCommand.COLOR_GREEN, Color.green));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction(MenuCommand.COLOR_BLUE, Color.blue));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction(MenuCommand.COLOR_CYAN, Color.cyan));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction(MenuCommand.COLOR_MAGENTA, Color.magenta));
        addMenuItem(colorMenu, new StyledEditorKit.ForegroundAction(MenuCommand.COLOR_BLACK, Color.black));

        colorMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initFontMenu(View view, JMenuBar menuBar) {
        JMenu fontMenu = new JMenu(MenuCommand.FONT);
        menuBar.add(fontMenu);

        JMenu fontTypeMenu = new JMenu(MenuCommand.FONT);
        fontMenu.add(fontTypeMenu);

        String[] fontTypes = {Font.SANS_SERIF, Font.SERIF, Font.MONOSPACED, Font.DIALOG, Font.DIALOG_INPUT};
        for (String fontType : fontTypes) {
            addMenuItem(fontTypeMenu, fontType, new StyledEditorKit.FontFamilyAction(fontType, fontType));
        }

        JMenu fontSizeMenu = new JMenu(MenuCommand.FONT_SIZE);
        fontMenu.add(fontSizeMenu);

        String[] fontSizes = {"6", "8", "10", "12", "14", "16", "20", "24", "32", "36", "48", "72"};
        for (String fontSize : fontSizes) {
            addMenuItem(fontSizeMenu, fontSize, new StyledEditorKit.FontSizeAction(fontSize, Integer.parseInt(fontSize)));
        }

        fontMenu.addMenuListener(new TextEditMenuListener(view));
    }

    public static void initHelpMenu(View view, JMenuBar menuBar) {
        JMenu helpMenu = new JMenu(MenuCommand.HELP);
        menuBar.add(helpMenu);

        addMenuItem(helpMenu, MenuCommand.ABOUT, view);
    }

}
