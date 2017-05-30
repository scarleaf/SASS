/* 
 * Copyright (C) 2017 Laboratory of Experimental Biophysics
 * Ecole Polytechnique Federale de Lausanne
 * 
 * Author: Marcel Stefko
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package beanshell;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.util.JConsole;
import commandline.CommandLineInterface;
import ij.plugin.frame.PlugInFrame;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stefko
 */
public class ConsoleFrame extends PlugInFrame {

    private final JConsole console;
    private final Interpreter interpreter;
    
    /**
     * Initialize the new frame
     */
    public ConsoleFrame() {
        super("SASS - BeanShell console");
        this.console = new JConsole();
        initComponents();
        this.console_panel.add(this.console);
        this.console.setSize(500, 400);

        interpreter = new Interpreter( console );
        interpreter.setShowResults(true);
    }
    
    /**
     * 
     * @return BeanShell interpreter associated with this ConsoleFrame
     */
    public Interpreter getInterpreter() {
        return interpreter;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        console_panel = new javax.swing.JPanel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        console_panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                console_panelComponentResized(evt);
            }
        });
        console_panel.setLayout(new javax.swing.BoxLayout(console_panel, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(console_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(console_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void console_panelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_console_panelComponentResized
        this.console.setSize(this.console_panel.getWidth(), this.console_panel.getHeight());
    }//GEN-LAST:event_console_panelComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel console_panel;
    // End of variables declaration//GEN-END:variables
}
