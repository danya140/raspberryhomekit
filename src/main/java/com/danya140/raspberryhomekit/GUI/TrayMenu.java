package com.danya140.raspberryhomekit.GUI;

import com.danya140.raspberryhomekit.models.Episode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Процесс в трее
 */
public class TrayMenu {

    private static TrayMenu instance;

    private TrayIcon trayIcon;

    private TrayMenu() {
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("icon.jpg"));
        trayIcon = new TrayIcon(image, "Lostfilm Demo");
        trayIcon.setImageAutoSize(true);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        PopupMenu popupMenu = new PopupMenu();
        MenuItem exitMenuItem = new MenuItem("Закрыть");
        exitMenuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        MenuItem stopMenuItem = new MenuItem("Остановить закачки");
        stopMenuItem.addActionListener(new StopMenuListener());

        popupMenu.add(stopMenuItem);


        popupMenu.add(exitMenuItem);
        trayIcon.setPopupMenu(popupMenu);
    }

    public static TrayMenu getInstance() {
        if (instance == null) {
            instance = new TrayMenu();
        }
        return instance;
    }

    /**
     * Показать уведомление о завершении скачивания эпизода
     *
     * @param episode эпизод, который был скачан
     */
    public void showDownloadCompleteNotification(Episode episode) {
        trayIcon.displayMessage("Загрузка завершена", "Загрузка " + episode.getSeriesName() + " сезон " + episode.getSeriesSeason() + " серия " + episode.getSeriesEpisode() + " завершена", TrayIcon.MessageType.INFO);
    }

    /**
     * Показать уведомление
     *
     * @param caption Заголовок
     * @param text    Текст сообщения
     */
    public void showNotification(String caption, String text) {
        trayIcon.displayMessage(caption, text, TrayIcon.MessageType.INFO);
    }
}
