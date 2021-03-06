/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import classes.Sugestao;
import controle.ControleSugestao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roger
 */
public class ViewListaSugestões extends javax.swing.JDialog {
    
    ControleSugestao controle = null;
    ArrayList<Sugestao> lista = null;

    /**
     * Creates new form ViewListaSugestões
     */
    public ViewListaSugestões(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        jButtonPesquisarSugestão.setEnabled(false);
        jTextFieldQtdeRegistros.requestFocus();
        
         try {
            controle = new ControleSugestao("Sugestoes.txt"); 
            imprimirDadosSugestões(controle.recuperar());
            lista = new ArrayList<>(controle.recuperar());
            jTextFieldQtdeRegistros.setText("" + lista.size());
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }
    
     public void imprimirDadosSugestões(ArrayList<Sugestao> lista)throws Exception{
        try {
            DefaultTableModel model = (DefaultTableModel) jTableListaDeSugestões.getModel();
            model.setNumRows(0);
            Collections.sort(lista);
            
            for (int pos = 0; pos < lista.size(); pos++) {
                String linha [] = new String [7];
                Sugestao aux = lista.get(pos);
                linha[0] = "" + aux.getId();
                linha[1] = new SimpleDateFormat("dd/MM/yyyy").format(aux.getDataDaSugestao());
                linha[2] = aux.getUsuario().getMatriculaCpf();
                linha[3] = aux.getUsuario().getNome().toUpperCase();
                linha[4] = aux.getUsuario().getTipoDeUsuario().toString();
                linha[5] = aux.getSugestao().toUpperCase();
                linha[6] = aux.getStatusDaSugestao().toString();
                model.addRow(linha);
                
            }
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTableListaSugestões = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListaDeSugestões = new javax.swing.JTable();
        jButtonFechar = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldQtdeRegistros = new javax.swing.JTextField();
        jTextFieldDigittarDados = new javax.swing.JTextField();
        jButtonPesquisarSugestão = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório de Sugestões");

        jTableListaDeSugestões.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DATA", "MAT. USUARIO", "NOME DO USUARIO", "TIPO USUARIO", "SUGESTÃO DE AQUISIÇÃO", "STATUS DA SUGESTÃO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableListaDeSugestões);
        if (jTableListaDeSugestões.getColumnModel().getColumnCount() > 0) {
            jTableListaDeSugestões.getColumnModel().getColumn(0).setMinWidth(45);
            jTableListaDeSugestões.getColumnModel().getColumn(0).setMaxWidth(45);
            jTableListaDeSugestões.getColumnModel().getColumn(1).setMinWidth(80);
            jTableListaDeSugestões.getColumnModel().getColumn(1).setMaxWidth(80);
            jTableListaDeSugestões.getColumnModel().getColumn(2).setMinWidth(100);
            jTableListaDeSugestões.getColumnModel().getColumn(2).setMaxWidth(100);
            jTableListaDeSugestões.getColumnModel().getColumn(3).setMinWidth(180);
            jTableListaDeSugestões.getColumnModel().getColumn(3).setMaxWidth(180);
            jTableListaDeSugestões.getColumnModel().getColumn(4).setMinWidth(115);
            jTableListaDeSugestões.getColumnModel().getColumn(4).setMaxWidth(115);
            jTableListaDeSugestões.getColumnModel().getColumn(6).setMinWidth(110);
            jTableListaDeSugestões.getColumnModel().getColumn(6).setMaxWidth(110);
        }

        jButtonFechar.setText("FECHAR");
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        jButtonImprimir.setText("IMPRIMIR");

        jLabel2.setText("Quant. Registros:");

        jTextFieldDigittarDados.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldDigittarDados.setText("Digite aqui a sugestão ou nome ou cpf do usuário");
        jTextFieldDigittarDados.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDigittarDadosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldDigittarDadosFocusLost(evt);
            }
        });

        jButtonPesquisarSugestão.setText("Pesquisar");
        jButtonPesquisarSugestão.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarSugestãoActionPerformed(evt);
            }
        });

        jButtonLimpar.setText("LIMPAR");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTableListaSugestõesLayout = new javax.swing.GroupLayout(jPanelTableListaSugestões);
        jPanelTableListaSugestões.setLayout(jPanelTableListaSugestõesLayout);
        jPanelTableListaSugestõesLayout.setHorizontalGroup(
            jPanelTableListaSugestõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTableListaSugestõesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldQtdeRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonLimpar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonImprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonFechar)
                .addContainerGap())
            .addGroup(jPanelTableListaSugestõesLayout.createSequentialGroup()
                .addComponent(jTextFieldDigittarDados, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPesquisarSugestão)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelTableListaSugestõesLayout.setVerticalGroup(
            jPanelTableListaSugestõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTableListaSugestõesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTableListaSugestõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDigittarDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisarSugestão))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelTableListaSugestõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFechar)
                    .addComponent(jButtonImprimir)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldQtdeRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLimpar))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanelTableListaSugestões, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTableListaSugestões, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonFecharActionPerformed

    private void jButtonPesquisarSugestãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarSugestãoActionPerformed
        try {
            imprimirDadosSugestões(controle.pesquisar(jTextFieldDigittarDados.getText()));
            jTextFieldDigittarDados.requestFocus();
            jTextFieldDigittarDados.setText("");
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
            jTextFieldDigittarDados.requestFocus();
            jTextFieldDigittarDados.setText("");
        }
    }//GEN-LAST:event_jButtonPesquisarSugestãoActionPerformed

    private void jTextFieldDigittarDadosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDigittarDadosFocusGained
        if (jTextFieldDigittarDados.getText().equals("Digite aqui a sugestão ou nome ou cpf do usuário")) {
            jTextFieldDigittarDados.setText("");
            jButtonPesquisarSugestão.setEnabled(true);
        }
    }//GEN-LAST:event_jTextFieldDigittarDadosFocusGained

    private void jTextFieldDigittarDadosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDigittarDadosFocusLost
         if (jTextFieldDigittarDados.getText().equals("")) {
            jTextFieldDigittarDados.setText("Digite aqui a sugestão ou nome ou cpf do usuário");
            jButtonPesquisarSugestão.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldDigittarDadosFocusLost

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        try {
            imprimirDadosSugestões(controle.recuperar());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }//GEN-LAST:event_jButtonLimparActionPerformed

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
            java.util.logging.Logger.getLogger(ViewListaSugestões.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewListaSugestões.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewListaSugestões.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewListaSugestões.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewListaSugestões dialog = new ViewListaSugestões(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonPesquisarSugestão;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelTableListaSugestões;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListaDeSugestões;
    private javax.swing.JTextField jTextFieldDigittarDados;
    private javax.swing.JTextField jTextFieldQtdeRegistros;
    // End of variables declaration//GEN-END:variables
}
