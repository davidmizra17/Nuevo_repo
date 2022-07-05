/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2edd;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import static proyecto2edd.ManejoArchivo.lecturaArchivo;
import static proyecto2edd.ManejoArchivo.*;

/**
 *
 * @author ernes
 */
public class Interfaz extends javax.swing.JFrame {

    public static Lista listaArticulos = new Lista();
    public static Articulo articulo = new Articulo();
    public static HashTable hashTitulo = new HashTable(1000);
    public static HashTable hashAutores = new HashTable(1000);
    public static HashTable hashPalabras = new HashTable(1000);

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BotonAgregar = new javax.swing.JButton();
        BotonBPAutor = new javax.swing.JButton();
        BotonBPPC = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BotonAgregar.setText("Agregar archivo");
        BotonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(BotonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 130, -1));

        BotonBPAutor.setText("Autor");
        jPanel1.add(BotonBPAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, -1, -1));

        BotonBPPC.setText("Palabras claves");
        jPanel1.add(BotonBPPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, -1, -1));

        jLabel1.setText("Proyecto 2 / Ernesto Lugo - David Mizrahi");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 200, 30));

        jLabel2.setText("Para agregar un articulo a la base de datos ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel3.setText("Para ver los articulos almacenados puede hacerlo por:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jButton1.setText("ver listaArticulos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, -1, -1));

        jButton2.setText("ver listaDesplegable");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarActionPerformed
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Selecciona un archivo de texto (.txt)");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);

        //Si el usuario agrega un txt.
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String auxpath = jfc.getSelectedFile().getPath();

            //Empezamos a leer el archivo cargado
            StringBuilder sb = new StringBuilder();
            String txt = "";

            try ( BufferedReader br = Files.newBufferedReader(Paths.get(auxpath))) {

                //Lectura linea por linea
                String line;

                while ((line = br.readLine()) != null) {

                    if (!line.isEmpty()) {
                        txt += line + "\n";
                    }

                }

                lecturaArchivo(txt, articulo, listaArticulos, hashTitulo, hashAutores, hashPalabras);
                //PRUEBA
//                leerBDD();
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }

        }

    }//GEN-LAST:event_BotonAgregarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        listaArticulos.imprimirValores();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String titulosconcatenados = "";
        String concatenadobien = "";

        //TODO a
        if (!listaArticulos.esVacio()) {

            Nodo aux = listaArticulos.getpFirst();

            for (int k = 0; k < listaArticulos.Size(); k++) {

                Articulo aux1 = (Articulo) aux.getInfo();
                titulosconcatenados += aux1.getTitulo() + "//";

                aux = aux.getpNext();

            }
            concatenadobien = titulosconcatenados.substring(0, titulosconcatenados.length() - 2);

            String[] concatenadobiensplit = concatenadobien.split("//");

            JFrame jFrame = new JFrame("Articulos por titulo");
            JComboBox<String> jComboBox = new JComboBox<>(concatenadobiensplit);
            jComboBox.setBounds(20, 50, 400, 40);

            JButton jButton = new JButton("Escoger");
            jButton.setBounds(170, 100, 90, 20);

            JLabel jLabel = new JLabel();
            jLabel.setBounds(10, 100, 400, 100);

            jFrame.add(jButton);
            jFrame.add(jComboBox);
            jFrame.add(jLabel);

            jFrame.setLayout(null);
            jFrame.setSize(450, 200);
            jFrame.setVisible(true);
            jFrame.setLocationRelativeTo(null);

            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String tituloseleccionado = "Haz escogido " + jComboBox.getItemAt(jComboBox.getSelectedIndex());
                    jLabel.setText(tituloseleccionado);
                    String titulobueno = jComboBox.getItemAt(jComboBox.getSelectedIndex());
                    int key = hashTitulo.stringtoNum(titulobueno);
                    int index = hashTitulo.getBucketIndex(key);
                    Articulo aux = (Articulo) hashTitulo.getBucketArray()[index].getValue();
                    aux.mostrarInfoTuneado(aux);
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Error. No existen elementos en la base de datos.");
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAgregar;
    private javax.swing.JButton BotonBPAutor;
    private javax.swing.JButton BotonBPPC;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
