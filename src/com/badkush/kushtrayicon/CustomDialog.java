/*
 * I dont care what you do with my code.
 * Feel free to copy it, spit on it, or whatever makes you happy.
 * Github: https://www.github.com/Righteous
 */
package com.badkush.kushtrayicon;

import javax.swing.JOptionPane;

/**
 *
 * @author jordan
 */
public class CustomDialog {

    public CustomDialog() {
        
    }
    
    public static void main(String[] args) {
        String[] arguments = new String[] {delay()};
        KushTrayIcon.main(arguments);
    }
    
    public static String delay() {
        try {
            String delay = JOptionPane.showInputDialog("Delay in milliseconds?:");
            Integer.parseInt(delay);
            return delay;
        } catch (NumberFormatException e) {
            System.out.println("Not a int so default value returned.");
            return "10000";
        }
    }
}
