/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * cetak_laporan.java
 *
 * Created on Mar 16, 2021, 12:10:19 AM
 */

package view;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author fadil
 */
public class cetak_laporan extends javax.swing.JFrame {

    
   
    /** Creates new form cetak_laporan */
    public cetak_laporan(){
        initComponents();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bt_cetak = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bt_cetak.setText("CETAK");
        bt_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cetakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(bt_cetak)
                .addContainerGap(183, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(bt_cetak)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cetakActionPerformed
        // TODO add your handling code here:
        try {
           Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db_mhs?user=root&password");
           Statement st = (Statement) conn.createStatement();
           ResultSet rs = st.executeQuery("select * from db_data_siswa");

           String path_laporan= System.getProperty("user.dir")+"/src/laporan/report2.jrxml";
           JasperDesign design = JRXmlLoader.load(path_laporan);
           JasperReport nilailaporan = JasperCompileManager.compileReport(design);
           JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
           JasperPrint cetak= JasperFillManager.fillReport(nilailaporan, new HashMap(),resultSetDataSource);
           JasperViewer.viewReport(cetak);
        } catch (SQLException ex) {
            Logger.getLogger(cetak_laporan.class.getName()).log(Level.SEVERE,null,ex);
        }
        catch(JRException ex){
            Logger.getLogger(cetak_laporan.class.getName()).log(Level.SEVERE,null,ex);
        }
    }//GEN-LAST:event_bt_cetakActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new cetak_laporan().setVisible(true);
                }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cetak;
    // End of variables declaration//GEN-END:variables

}