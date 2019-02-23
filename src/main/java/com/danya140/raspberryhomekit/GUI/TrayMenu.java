package com.danya140.raspberryhomekit.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TrayMenu {

    public void displayTray() throws AWTException{
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        PopupMenu popupMenu = new PopupMenu();
        MenuItem item = new MenuItem("Exit");
        item.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        popupMenu.add(item);

        trayIcon.setPopupMenu(popupMenu);

    }
}
