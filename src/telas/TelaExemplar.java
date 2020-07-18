/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import ENUMERADORES.EnumDisponibilidadeExemplar;
import ENUMERADORES.EnumNomesDeArquivos;
import ENUMERADORES.EnumSituacaoExemplar;
import classes.Exemplar;
import classes.Livro;
import controle.ControleExemplar;
import controle.ControleLivro;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roger
 */
public class TelaExemplar extends javax.swing.JDialog {

    ControleExemplar controleExemplar = null;
    Exemplar exemplar = null;
    Exemplar exemplarAnterior = null;
    ControleLivro controleLivro = null;
    Date data = null;
    SimpleDateFormat formatar = null;
    String dataAtual = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaExemplar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        data = new Date();
        formatar = new SimpleDateFormat("dd/MM/yyyy");
        dataAtual = formatar.format(data);

        desabilitaCampos();
        jButtonIncluir.setEnabled(false);
        jButtonSalvarAlteraracao.setEnabled(false);
        jTextFieldMotivoInativacao.requestFocus();
        jButtonPesquisarExemplar.setEnabled(false);

        try {

            controleExemplar = new ControleExemplar(EnumNomesDeArquivos.EXEMPLARES + ".txt");
            exemplarAnterior = new Exemplar();
            exemplar = new Exemplar();

            controleLivro = new ControleLivro(EnumNomesDeArquivos.LIVROS + ".txt");
            ArrayList<Livro> listaDeLivros = controleLivro.recuperar();
            for (int pos = 0; pos < listaDeLivros.size(); pos++) {
                Livro aux = listaDeLivros.get(pos);
                jComboBoxLivros.addItem(aux.getId() + "-" + aux.getTituloLivro().toUpperCase());
            }

            for (EnumSituacaoExemplar situacao : EnumSituacaoExemplar.values()) {
                jComboBoxSituacao.addItem(situacao.toString());
            }

            for (EnumDisponibilidadeExemplar disponibilidade : EnumDisponibilidadeExemplar.values()) {
                jComboBoxDisponibilidade.addItem(disponibilidade.toString());
            }
            imprimirDadosDoExemplarNaTabela(controleExemplar.recuperar());

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
    }

    public void habilitaCampos() {
        jFormattedTextFieldDataAquisicao.setEnabled(true);
        jFormattedTextFieldPrecoExemplar.setEnabled(true);
        jTextFieldQtdeExemplaresAdquiridos.setEnabled(true);
        jComboBoxSituacao.setEnabled(true);
        jComboBoxLivros.setEnabled(true);
        jComboBoxDisponibilidade.setEnabled(true);
        jTextFieldMotivoInativacao.setEnabled(true);

    }

    public void desabilitaCampos() {
        jFormattedTextFieldDataAquisicao.setEnabled(false);
        jFormattedTextFieldPrecoExemplar.setEnabled(false);
        jTextFieldQtdeExemplaresAdquiridos.setEnabled(false);
        jComboBoxSituacao.setEnabled(false);
        jComboBoxLivros.setEnabled(false);
        jComboBoxDisponibilidade.setEnabled(false);
        jTextFieldMotivoInativacao.setEnabled(false);
    }

    private void imprimirDadosDoExemplarNaTabela(ArrayList<Exemplar> lista) throws Exception {
        try {
            DefaultTableModel model = (DefaultTableModel) jTableCadExemplares.getModel();
            model.setNumRows(0);
            Collections.sort(lista);
            for (int pos = 0; pos < lista.size(); pos++) {
                String[] linha = new String[7];
                Exemplar aux = lista.get(pos);
                linha[0] = "" + aux.getId();
                linha[1] = aux.getLivro().getId() + "-" + aux.getLivro().getTituloLivro().toUpperCase();
                linha[2] = "" + new SimpleDateFormat("dd/MM/yyyy").format(aux.getDataAquisicao());
                linha[3] = String.format("%.2f", aux.getPrecoExemplar());
                linha[4] = aux.getSituacao().toString();
                linha[5] = aux.getDisponibilidade().toString();
                linha[6] = aux.getMotivoInativacao().toUpperCase();
                model.addRow(linha);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(rootPane, erro.getMessage());
        }
    }

    public void limpaCampos() {
        jFormattedTextFieldDataAquisicao.setText("");
        jFormattedTextFieldPrecoExemplar.setText("");
        jTextFieldQtdeExemplaresAdquiridos.setText("");
        jTextFieldMotivoInativacao.setText("");
        jComboBoxLivros.setSelectedIndex(0);
        jComboBoxSituacao.setSelectedIndex(0);
        jComboBoxDisponibilidade.setSelectedIndex(0);

    }

    public void preencherFormulario() {
        try {
            int linha = jTableCadExemplares.getSelectedRow();

            if (linha >= 0) {
                String campoID = jTableCadExemplares.getValueAt(linha, 0).toString();
                String campoLivro = jTableCadExemplares.getValueAt(linha, 1).toString();
                String campoData = jTableCadExemplares.getValueAt(linha, 2).toString();
                String campoPreco = jTableCadExemplares.getValueAt(linha, 3).toString();
                String campoSituacao = jTableCadExemplares.getValueAt(linha, 4).toString();
                String campoDisponibilidade = jTableCadExemplares.getValueAt(linha, 5).toString();
                String campoMotivo = jTableCadExemplares.getValueAt(linha, 6).toString();

                exemplarAnterior.setId(Integer.parseInt(campoID));

                jComboBoxLivros.setSelectedItem(campoLivro);
                jFormattedTextFieldDataAquisicao.setText(campoData);
                jFormattedTextFieldPrecoExemplar.setText(campoPreco);
                jComboBoxSituacao.setSelectedItem(campoSituacao);
                jComboBoxDisponibilidade.setSelectedItem(campoDisponibilidade);
                jTextFieldMotivoInativacao.setText(campoMotivo);

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

        jPanelCadExemplar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextFieldDataAquisicao = new javax.swing.JFormattedTextField();
        jComboBoxLivros = new javax.swing.JComboBox<>();
        jComboBoxSituacao = new javax.swing.JComboBox<>();
        jComboBoxDisponibilidade = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCadExemplares = new javax.swing.JTable();
        jButtonFechar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jButtonSalvarAlteraracao = new javax.swing.JButton();
        jButtonIncluir = new javax.swing.JButton();
        jTextFieldMotivoInativacao = new javax.swing.JTextField();
        jFormattedTextFieldPrecoExemplar = new javax.swing.JFormattedTextField();
        jButtonEditar = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldQtdeExemplaresAdquiridos = new javax.swing.JTextField();
        jTextFieldPesquisar = new javax.swing.JTextField();
        jButtonPesquisarExemplar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Exemplar");

        jLabel2.setText("Data da Aquisição:");

        jLabel3.setText("Preço  R$");

        jLabel4.setText("Livro Correspondente:");

        jLabel5.setText("Situação deste Exemplar:");

        jLabel6.setText("Disponibilidade deste Exemplar:");

        jLabel7.setText("Motivo da Desativação/Estado Físico do livro:");

        try {
            jFormattedTextFieldDataAquisicao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jComboBoxLivros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecione o Livro>" }));

        jComboBoxSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecione a Situação do Exemplar>" }));
        jComboBoxSituacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxSituacaoMouseClicked(evt);
            }
        });
        jComboBoxSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSituacaoActionPerformed(evt);
            }
        });

        jComboBoxDisponibilidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Selecione a Disponibilidade do Exemplar>" }));

        jTableCadExemplares.setAutoCreateRowSorter(true);
        jTableCadExemplares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Exem.", "Livro", "Dt. Aquisição", "Preço  R$", "Situação", "Disponibilidade", "Motivo de Inativação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCadExemplares.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCadExemplaresMouseClicked(evt);
            }
        });
        jTableCadExemplares.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableCadExemplaresKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableCadExemplares);
        if (jTableCadExemplares.getColumnModel().getColumnCount() > 0) {
            jTableCadExemplares.getColumnModel().getColumn(0).setMinWidth(50);
            jTableCadExemplares.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        jButtonFechar.setText("Fechar");
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        jButtonLimpar.setText("Limpar");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        jButtonSalvarAlteraracao.setText("Salvar Alteração");
        jButtonSalvarAlteraracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarAlteraracaoActionPerformed(evt);
            }
        });

        jButtonIncluir.setText("Incluir");
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        jTextFieldMotivoInativacao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldMotivoInativacaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldMotivoInativacaoFocusLost(evt);
            }
        });

        jFormattedTextFieldPrecoExemplar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0,00"))));

        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonNovo.setText("Novo");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jLabel8.setText("Quant.");

        jTextFieldPesquisar.setForeground(new java.awt.Color(102, 102, 102));
        jTextFieldPesquisar.setText("Digite aqui o título do livro");
        jTextFieldPesquisar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPesquisarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPesquisarFocusLost(evt);
            }
        });

        jButtonPesquisarExemplar.setText("Pesquisar");
        jButtonPesquisarExemplar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarExemplarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCadExemplarLayout = new javax.swing.GroupLayout(jPanelCadExemplar);
        jPanelCadExemplar.setLayout(jPanelCadExemplarLayout);
        jPanelCadExemplarLayout.setHorizontalGroup(
            jPanelCadExemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadExemplarLayout.createSequentialGroup()
                .addGroup(jPanelCadExemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelCadExemplarLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(161, 161, 161))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCadExemplarLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelCadExemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jComboBoxDisponibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCadExemplarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelCadExemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCadExemplarLayout.createSequentialGroup()
                                .addComponent(jButtonNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonIncluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSalvarAlteraracao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonLimpar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonFechar))
                            .addComponent(jTextFieldMotivoInativacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelCadExemplarLayout.createSequentialGroup()
                        .addComponent(jTextFieldPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPesquisarExemplar))
                    .addGroup(jPanelCadExemplarLayout.createSequentialGroup()
                        .addGroup(jPanelCadExemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCadExemplarLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldDataAquisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldPrecoExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldQtdeExemplaresAdquiridos, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCadExemplarLayout.createSequentialGroup()
                                .addComponent(jComboBoxLivros, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jComboBoxSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelCadExemplarLayout.setVerticalGroup(
            jPanelCadExemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCadExemplarLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelCadExemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jFormattedTextFieldDataAquisicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextFieldPrecoExemplar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldQtdeExemplaresAdquiridos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanelCadExemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCadExemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxLivros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxDisponibilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldMotivoInativacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanelCadExemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPesquisarExemplar)
                    .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelCadExemplarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFechar)
                    .addComponent(jButtonLimpar)
                    .addComponent(jButtonSalvarAlteraracao)
                    .addComponent(jButtonIncluir)
                    .addComponent(jButtonEditar)
                    .addComponent(jButtonNovo)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCadExemplar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCadExemplar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxSituacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxSituacaoMouseClicked

    }//GEN-LAST:event_jComboBoxSituacaoMouseClicked

    private void jComboBoxSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSituacaoActionPerformed
        if (jComboBoxSituacao.getSelectedIndex() == 1) {
            jTextFieldMotivoInativacao.setText("Livro em bom estado");
        } else if (jComboBoxSituacao.getSelectedIndex() == 2) {
            jComboBoxDisponibilidade.setSelectedIndex(2);
            jTextFieldMotivoInativacao.setText("");
            JOptionPane.showMessageDialog(null, "Informe o motivo da inatividade do livro");
            jTextFieldMotivoInativacao.requestFocus();
        }
    }//GEN-LAST:event_jComboBoxSituacaoActionPerformed

    private void jTableCadExemplaresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCadExemplaresMouseClicked
        preencherFormulario();
        jButtonSalvarAlteraracao.setEnabled(true);
        jButtonIncluir.setEnabled(false);
    }//GEN-LAST:event_jTableCadExemplaresMouseClicked

    private void jTableCadExemplaresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableCadExemplaresKeyReleased
        preencherFormulario();
    }//GEN-LAST:event_jTableCadExemplaresKeyReleased

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButtonFecharActionPerformed

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        try {
            limpaCampos();
            jFormattedTextFieldDataAquisicao.requestFocus();
            imprimirDadosDoExemplarNaTabela(controleExemplar.recuperar());
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jButtonSalvarAlteraracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarAlteraracaoActionPerformed

        if (jFormattedTextFieldDataAquisicao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "A data de aquisição do exemplar deve ser informada.\n");
            jFormattedTextFieldDataAquisicao.requestFocus();
        } else if (jFormattedTextFieldPrecoExemplar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preço do exemplar dese ser informado.\n");
            jFormattedTextFieldPrecoExemplar.requestFocus();
        } else if (jComboBoxLivros.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "O livro deste exemplar deve ser informado.\n");
        } else if (jComboBoxSituacao.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "A situação deste exemplar deve ser informada.\n");
        } else if (jComboBoxDisponibilidade.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "A disponibilidade do exemplar deve ser informada.\n");
        } else if (jTextFieldMotivoInativacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "O estado físico do exemplar deve ser informado.\n");
            jTextFieldMotivoInativacao.requestFocus();
        } else {
            try {

                int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente alterar este exemplar ?");
                if (opcao == 0) {

                    Livro auxLivro = new Livro();
                    auxLivro.setLivroSplit(jComboBoxLivros.getSelectedItem().toString());

                    exemplar.setId(exemplarAnterior.getId());
                    exemplar.setLivro(auxLivro);
                    exemplar.setDataAquisicao(new SimpleDateFormat("dd/MM/yyyy").parse(jFormattedTextFieldDataAquisicao.getText()));
                    exemplar.setPrecoExemplar(Float.parseFloat(jFormattedTextFieldPrecoExemplar.getText().replace(",", ".").replace(" ", "")));
                    exemplar.setSituacao(EnumSituacaoExemplar.valueOf(jComboBoxSituacao.getSelectedItem().toString()));
                    exemplar.setDisponibilidade(EnumDisponibilidadeExemplar.valueOf(jComboBoxDisponibilidade.getSelectedItem().toString()));
                    exemplar.setMotivoInativacao(jTextFieldMotivoInativacao.getText().toString());
                    controleExemplar.alterar(exemplar);

                    imprimirDadosDoExemplarNaTabela(controleExemplar.recuperar());
                }
                limpaCampos();
                desabilitaCampos();
                jButtonSalvarAlteraracao.setEnabled(false);
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());

            }
        }
    }//GEN-LAST:event_jButtonSalvarAlteraracaoActionPerformed

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed

        if (jFormattedTextFieldDataAquisicao.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "A data de aquisição do exemplar deve ser informada.\n");
            jFormattedTextFieldDataAquisicao.requestFocus();
        } else if (jFormattedTextFieldPrecoExemplar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O preço do exemplar dese ser informado.\n");
            jFormattedTextFieldPrecoExemplar.requestFocus();
        } else if (jComboBoxLivros.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "O livro deste exemplar deve ser informado.\n");
        } else if (jComboBoxSituacao.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "A situação deste exemplar deve ser informada.\n");
        } else if (jComboBoxDisponibilidade.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "A disponibilidade do exemplar deve ser informada.\n");
        } else if (jTextFieldMotivoInativacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "O estado físico do exemplar deve ser informado.\n");
            jTextFieldMotivoInativacao.requestFocus();
        } else if (jTextFieldQtdeExemplaresAdquiridos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe a quantidade de exemplares.\n");
            jTextFieldQtdeExemplaresAdquiridos.requestFocus();
        } else {

            try {
                Livro auxLivro = new Livro();
                auxLivro.setLivroSplit(jComboBoxLivros.getSelectedItem().toString());

                int quantExemplaresAdquiridos = Integer.parseInt(jTextFieldQtdeExemplaresAdquiridos.getText());
                for (int i = 0; i < quantExemplaresAdquiridos; i++) {

                    exemplar.setLivro(auxLivro);
                    exemplar.setDataAquisicao(new SimpleDateFormat("dd/MM/yyyy").parse(jFormattedTextFieldDataAquisicao.getText()));
                    exemplar.setPrecoExemplar(Float.parseFloat(jFormattedTextFieldPrecoExemplar.getText().replace(",", ".").replace(" ", "")));
                    exemplar.setSituacao(EnumSituacaoExemplar.valueOf(jComboBoxSituacao.getSelectedItem().toString()));
                    exemplar.setDisponibilidade(EnumDisponibilidadeExemplar.valueOf(jComboBoxDisponibilidade.getSelectedItem().toString()));
                    exemplar.setMotivoInativacao(jTextFieldMotivoInativacao.getText().toString());

                    controleExemplar.incluir(exemplar);
                }
                imprimirDadosDoExemplarNaTabela(controleExemplar.recuperar());
                limpaCampos();
                desabilitaCampos();
                jButtonIncluir.setEnabled(false);
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
        }
    }//GEN-LAST:event_jButtonIncluirActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        habilitaCampos();
        jButtonIncluir.setEnabled(false);
        jFormattedTextFieldDataAquisicao.requestFocus();
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        habilitaCampos();
        limpaCampos();
        jFormattedTextFieldDataAquisicao.requestFocus();
        jButtonIncluir.setEnabled(true);
        jButtonSalvarAlteraracao.setEnabled(false);
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonPesquisarExemplarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarExemplarActionPerformed
        try {
            imprimirDadosDoExemplarNaTabela(controleExemplar.pesquisarExemplar(jTextFieldPesquisar.getText()));
            jTextFieldPesquisar.requestFocus();
            jTextFieldPesquisar.setText("");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
             jTextFieldPesquisar.requestFocus();
            jTextFieldPesquisar.setText("");
        }
    }//GEN-LAST:event_jButtonPesquisarExemplarActionPerformed

    private void jTextFieldMotivoInativacaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldMotivoInativacaoFocusGained
        
    }//GEN-LAST:event_jTextFieldMotivoInativacaoFocusGained

    private void jTextFieldMotivoInativacaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldMotivoInativacaoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMotivoInativacaoFocusLost

    private void jTextFieldPesquisarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarFocusGained
        if (jTextFieldPesquisar.getText().equals("Digite aqui o título do livro")) {
            jTextFieldPesquisar.setText("");
            jButtonPesquisarExemplar.setEnabled(true);
        }
    }//GEN-LAST:event_jTextFieldPesquisarFocusGained

    private void jTextFieldPesquisarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarFocusLost
        if (jTextFieldPesquisar.getText().equals("")) {
            jTextFieldPesquisar.setText("Digite aqui o título do livro");
           jButtonPesquisarExemplar.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldPesquisarFocusLost

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
            java.util.logging.Logger.getLogger(TelaExemplar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaExemplar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaExemplar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaExemplar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaExemplar dialog = new TelaExemplar(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonPesquisarExemplar;
    private javax.swing.JButton jButtonSalvarAlteraracao;
    private javax.swing.JComboBox<String> jComboBoxDisponibilidade;
    private javax.swing.JComboBox<String> jComboBoxLivros;
    private javax.swing.JComboBox<String> jComboBoxSituacao;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataAquisicao;
    private javax.swing.JFormattedTextField jFormattedTextFieldPrecoExemplar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanelCadExemplar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCadExemplares;
    private javax.swing.JTextField jTextFieldMotivoInativacao;
    private javax.swing.JTextField jTextFieldPesquisar;
    private javax.swing.JTextField jTextFieldQtdeExemplaresAdquiridos;
    // End of variables declaration//GEN-END:variables
}
