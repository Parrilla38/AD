/*
 * Copyright (C) 2020 Javier Tejedor
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
package hundirlaflota.interfaz;

import hundirlaflota.Controladora;
import hundirlaflota.jugador.ModoJuego;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * Ventana para seleccionador el modo de juego
 *
 * @author Javier Tejedor
 */
public class PantallaSelectorModo extends javax.swing.JFrame {

    private Controladora c;

    /**
     * Crea un nuevo formulario de seleccion de modo de juego,
     *
     * @param c Una instancia de controladora
     */
    public PantallaSelectorModo(Controladora c) {
        initComponents();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setSize(width / 3, height / 6);

        setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(Controladora.ICONO_APP).getImage());
        this.setTitle("SELECCIONA UN MODO");
        this.c = c;
        this.cboModo.setModel(new DefaultComboBoxModel(ModoJuego.values()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboModo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        cbTuto = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cboModo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Empezar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cbTuto.setText("Mostrar tutorial");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(cboModo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(74, 74, 74))
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(cbTuto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboModo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbTuto)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.c.getMaquina().setModo((ModoJuego) this.cboModo.getSelectedItem());
        this.setVisible(false);
        new VentanaCrearTableroUsuario(c).setVisible(true);
        if (cbTuto.isSelected()) {
            JOptionPane.showMessageDialog(this, "Tienes 3 barcos de longitud 2, 3, 4.");
            JOptionPane.showMessageDialog(this, "Para colocarlos necesitas seleccionar dos casillas la inical y la final y dar al bot??n aceptar");
            JOptionPane.showMessageDialog(this, "Si has seleccionado dos casillas no validas te saldr?? una advertencia");
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbTuto;
    private javax.swing.JComboBox<String> cboModo;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
