package com.badkush.kushtrayicon;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.UIManager;
import javax.swing.LookAndFeel;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jordan/Righteous
 */
public class KushTrayIcon {

    private static final String iconImageLoc
            = "https://i.imgur.com/AZGkd2A.png";

    private static Timer notificationTimer = new Timer();
    public static int delay;
    private DateFormat timeFormat = SimpleDateFormat.getTimeInstance();

    public static void main(String[] args) {
        int firstArg = 0;
        if (args.length > 0) {
            try {
                firstArg = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
        delay = firstArg;
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            System.out.println("args" + args.toString());
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Unsupported look and feel!");
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGui();
                System.setProperty("http.agent", RandomUserAgent.getRandomUserAgent());

            }
        });
    }

    public static void createGui() {
        try {
            final URL imageLoc = new URL(
                    iconImageLoc
            );

            final Image image = ImageIO.read(imageLoc);

            if (SystemTray.isSupported()) {
                // get the SystemTray instance
                final SystemTray tray = SystemTray.getSystemTray();
                ActionListener exitListener;
                ActionListener settingsListener = null;

                exitListener = (ActionEvent e) -> {
                    notificationTimer.cancel();
                    System.exit(0);
                };

                // create a popup menu
                final PopupMenu popup = new PopupMenu();
                // create menu item for the default action
                final MenuItem exitItem = new MenuItem("Exit");
                exitItem.addActionListener(exitListener);
                popup.add(exitItem);
                final TrayIcon trayIcon = new TrayIcon(image, "KushTrayAlert", popup);
                trayIcon.addActionListener(exitListener);
                trayIcon.setPopupMenu(popup);

                notificationTimer.schedule(
                        new TimerTask() {
                    @Override
                    public void run() {
                        javax.swing.SwingUtilities.invokeLater(()
                                -> trayIcon.displayMessage(
                                        "KushAlert",
                                        "Event in: " + BadKushInfo.getBossEventTime(),
                                        TrayIcon.MessageType.WARNING
                                )
                        );
                    }
                },
                        delay,
                        100_000
                );

                try {
                    tray.add(trayIcon);
                } catch (AWTException e) {
                    System.err.println(e);
                }
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
