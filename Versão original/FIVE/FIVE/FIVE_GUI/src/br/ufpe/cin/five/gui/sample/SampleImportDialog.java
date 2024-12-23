/**
 * Copyright 2011 Federal University of Pernambuco.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * This file is part of FIVE (Framework for an Integrated Voice Environment).
 *
 */
package br.ufpe.cin.five.gui.sample;

import br.ufpe.cin.five.core.project.Project;
import br.ufpe.cin.five.core.project.ProjectUtil;
import br.ufpe.cin.five.core.sample.Sample;
import br.ufpe.cin.five.core.speaker.Speaker;
import br.ufpe.cin.five.core.utterance.Utterance;
import br.ufpe.cin.five.core.sample.Environment;
import br.ufpe.cin.five.facade.Facade;
import br.ufpe.cin.five.register.ProjectRegister;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import org.apache.log4j.Logger;

/**
 *
 * @author Alexandre
 */
public class SampleImportDialog extends javax.swing.JDialog {

    private static final Logger logger = Logger.getLogger(SampleDialog.class);   
    private Project project = Facade.getInstance().getProject();
    private ArrayList<File> filesOnList = new ArrayList<File>();

    /** Creates new form DataBaseImportDialog */
    public SampleImportDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        logger.info("Inicializando Sample Import Dialog.");
        initComponents();
        
        DefaultComboBoxModel defaultModel = new DefaultComboBoxModel(Environment.values());
        defaultModel.insertElementAt(" ", 0);
        cbEnvironment.setModel(defaultModel);
        cbEnvironment.setSelectedIndex(0);
        
        defaultModel = new DefaultComboBoxModel(project.getSpeakers().toArray());
        defaultModel.insertElementAt(" ", 0);
        cbSpeaker.setModel(defaultModel);
        cbSpeaker.setSelectedIndex(0);
        
        defaultModel = new DefaultComboBoxModel(project.getUtterances().toArray());
        defaultModel.insertElementAt(" ", 0);
        cbUtterance.setModel(defaultModel);
        cbUtterance.setSelectedIndex(0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblIdade = new javax.swing.JLabel();
        tfIdade = new javax.swing.JTextField();
        tfRegiao = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rbFeminino = new javax.swing.JRadioButton();
        rbMasculino = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        cbSpeaker = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taSelectedUtterance = new javax.swing.JTextArea();
        cbUtterance = new javax.swing.JComboBox();
        cbEnvironment = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbGenuine = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListWaves = new javax.swing.JList();
        btAdd = new javax.swing.JButton();
        btRemove = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btImport = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Amostras");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel2.setText("Dados do Locutor");

        lblIdade.setText("Idade:");

        tfIdade.setEditable(false);
        tfIdade.setToolTipText("");

        tfRegiao.setEditable(false);
        tfRegiao.setToolTipText("");

        jLabel5.setText("Região:");

        rbFeminino.setText("Feminino");
        rbFeminino.setEnabled(false);

        rbMasculino.setText("Masculino");
        rbMasculino.setEnabled(false);

        jLabel7.setText("Locutor:");

        cbSpeaker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSpeakerActionPerformed(evt);
            }
        });

        jLabel9.setText("Sexo:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(cbSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdade)
                            .addComponent(tfIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(tfRegiao, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(rbMasculino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbFeminino))
                            .addComponent(jLabel9)))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblIdade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfIdade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfRegiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbMasculino)
                            .addComponent(rbFeminino)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSpeaker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel6.setText("Dados da Locução");

        taSelectedUtterance.setColumns(20);
        taSelectedUtterance.setLineWrap(true);
        taSelectedUtterance.setRows(5);
        taSelectedUtterance.setWrapStyleWord(true);
        taSelectedUtterance.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        taSelectedUtterance.setEnabled(false);
        jScrollPane2.setViewportView(taSelectedUtterance);

        cbUtterance.setAutoscrolls(true);
        cbUtterance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUtteranceActionPerformed(evt);
            }
        });

        jLabel3.setText("Ambiente:");

        jLabel8.setText("Locução:");

        jLabel10.setText("Genunína:");

        cbGenuine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sim", "Não" }));

        jLabel11.setText("Descrição:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cbEnvironment, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbGenuine, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10)))
                    .addComponent(jLabel8)
                    .addComponent(cbUtterance, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbUtterance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEnvironment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbGenuine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setViewportView(jListWaves);

        btAdd.setText("Adicionar");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        btRemove.setText("Remover");
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel12.setText("Dados da Amostra");

        jLabel1.setText("Amostras:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btAdd)
                            .addComponent(btRemove)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btRemove)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        btImport.setText("Importar");
        btImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImportActionPerformed(evt);
            }
        });

        btCancel.setText("Cancelar");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btImport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btCancel)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btImport)
                    .addComponent(btCancel))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//GEN-FIRST:event_cbSpeakerActionPerformed
//GEN-LAST:event_cbSpeakerActionPerformed
    private void cbUtteranceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUtteranceActionPerformed
        taSelectedUtterance.setText(cbUtterance.getSelectedItem().toString());
}//GEN-LAST:event_cbUtteranceActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return f != null ? f.getPath().endsWith(".wav") || f.isDirectory() : false;
            }

            @Override
            public String getDescription() {
                return "*.wav";
            }
        });
        jfc.setMultiSelectionEnabled(true);
        jfc.showOpenDialog(null);
        File[] files = jfc.getSelectedFiles();
        if (files != null) {
            for (File file : files) {
                filesOnList.add(file);
            }
            jListWaves.setListData(files);
        }
    }//GEN-LAST:event_btAddActionPerformed

    private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
        Object[] objects = jListWaves.getSelectedValues();
        if (objects != null) {
            for (Object object : objects) {
                filesOnList.remove((File) object);
            }
        }
        jListWaves.setListData(filesOnList.toArray());
    }//GEN-LAST:event_btRemoveActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btCancelActionPerformed

    private void btImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImportActionPerformed
        if (checkFields()) {
            try {
                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);
                textArea.append("Os seguintes waves foram importados:" + "\n");
                for (File file : filesOnList) {

                    Sample sample = new Sample();
                    
                    Speaker speaker = (Speaker) cbSpeaker.getSelectedItem();
                    sample.setSpeaker(speaker);
                    
                    Utterance utterance = (Utterance) cbUtterance.getSelectedItem();
                    sample.setUtterance(utterance);
                    
                    sample.setEnvironment((Environment) cbEnvironment.getSelectedItem());
                    sample.setGenuine((cbGenuine.getSelectedIndex() == 0) ? true : false);
                            
                    String projectName = project.getName();
                    String selectedSpeaker = speaker.getId()+"";
                    String selectedUtterance = utterance.getId() + "";
                    String nextSampleId = ProjectRegister.getNextSampleId(project) + "";
                    
                    String fileName = projectName + "_" + selectedSpeaker + "_" + selectedUtterance + "_" + nextSampleId + ".wav";                    
                    sample.setAudioFile(fileName);
                    
                    String extractionFile = fileName.substring(0, sample.getAudioFile().length() - 3) + "mfc";
                    sample.setFeatureFile(extractionFile);                    
                    
                    //Copiando o arquivo para a pasta waves                    
                    fileName = "samples" + File.separator + fileName;
                    textArea.append(file.getName() + " > " + fileName + "\n");
                    fileName = project.getDirectory() + File.separator + fileName;
                    ProjectUtil.copyFile(file.getPath(), fileName);
                    Facade.getInstance().insertSample(sample);                                       
                }
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(350, 150));
                logger.info("Amostras inseridas com sucesso.");
                JOptionPane.showMessageDialog(null, scrollPane, "Amostras importadas com sucesso!", JOptionPane.INFORMATION_MESSAGE);

                this.setVisible(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_btImportActionPerformed
    private void cbSpeakerActionPerformed(java.awt.event.ActionEvent evt) {
        if (cbSpeaker.getSelectedIndex() != 0) {
            Speaker speaker = (Speaker) cbSpeaker.getSelectedItem();
            tfIdade.setText(speaker.getAge() + "");
            tfRegiao.setText(speaker.getRegion());
            switch (speaker.getGender()) {
                case MASCULINO:
                    rbFeminino.setSelected(false);
                    rbMasculino.setSelected(true);
                    break;
                case FEMININO:
                    rbFeminino.setSelected(true);
                    rbMasculino.setSelected(false);
                    break;
            }
        } else {
            tfIdade.setText("");
            tfRegiao.setText("");
            rbFeminino.setSelected(false);
            rbMasculino.setSelected(false);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btImport;
    private javax.swing.JButton btRemove;
    private javax.swing.JComboBox cbEnvironment;
    private javax.swing.JComboBox cbGenuine;
    private javax.swing.JComboBox cbSpeaker;
    private javax.swing.JComboBox cbUtterance;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListWaves;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIdade;
    private javax.swing.JRadioButton rbFeminino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JTextArea taSelectedUtterance;
    private javax.swing.JTextField tfIdade;
    private javax.swing.JTextField tfRegiao;
    // End of variables declaration//GEN-END:variables

    public boolean checkFields() {
        if (cbEnvironment.getSelectedIndex() == 0 || cbSpeaker.getSelectedIndex() == 0 || cbUtterance.getSelectedIndex() == 0 || filesOnList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.");
            return false;
        }
        return true;
    }
}
