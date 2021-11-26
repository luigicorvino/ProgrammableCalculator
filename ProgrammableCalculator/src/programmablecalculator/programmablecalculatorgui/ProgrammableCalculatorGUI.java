/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmablecalculator.programmablecalculatorgui;

/**
 *
 * @author bad-b
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static javax.swing.JOptionPane.showMessageDialog;
import ProgrammableCalculatorController.ProgrammableCalculatorController;
public class ProgrammableCalculatorGUI extends javax.swing.JFrame {

    /**
     * Creates new form ProgrammableCalculatorGUI
     */
    public ProgrammableCalculatorGUI() {
       
        initComponents();
        
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CalculatorPanel = new javax.swing.JPanel();
        InputField = new javax.swing.JTextField();
        LabelTextField = new javax.swing.JLabel();
        ProcessInputButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ProgrammableCalculator");

        InputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputFieldActionPerformed(evt);
            }
        });

        LabelTextField.setText("Inserisci numeri e/o operazioni da eseguire");

        ProcessInputButton.setText("Insert");
        ProcessInputButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProcessInputButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CalculatorPanelLayout = new javax.swing.GroupLayout(CalculatorPanel);
        CalculatorPanel.setLayout(CalculatorPanelLayout);
        CalculatorPanelLayout.setHorizontalGroup(
            CalculatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalculatorPanelLayout.createSequentialGroup()
                .addComponent(InputField, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(ProcessInputButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(CalculatorPanelLayout.createSequentialGroup()
                .addComponent(LabelTextField)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        CalculatorPanelLayout.setVerticalGroup(
            CalculatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalculatorPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(LabelTextField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CalculatorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InputField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProcessInputButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(211, 211, 211))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(CalculatorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CalculatorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 287, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InputFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputFieldActionPerformed
      
    }//GEN-LAST:event_InputFieldActionPerformed

    private void ProcessInputButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProcessInputButtonActionPerformed
     String input;
     input=this.checkInputField();
     if (input!=null)
         controller.elaborateInput(input);
     else
         InputField.setText("");
     
         
     
    }//GEN-LAST:event_ProcessInputButtonActionPerformed
    public String checkInputField(){
     String numberWithNoSpace=InputField.getText().replaceAll("\\s","");
     String inputText=InputField.getText().toLowerCase();
     Pattern patternBothParts= Pattern.compile("([-]?[0-9]+\\.?[0-9]*)([-|+]+[0-9]*\\.?[0-9]*)[j$]+");
     Pattern patternRealNumber= Pattern.compile("([-]?[0-9]+\\.?[0-9]*)$");
     Pattern patternOnlyImmaginaryPart= Pattern.compile("([-]?[0-9]*\\.?[0-9]*)[j$]");
     Matcher matcherBothParts = patternBothParts.matcher(inputText);
     Matcher matcherRealNumber = patternRealNumber.matcher(inputText);
     Matcher matcherOnlyImmaginaryPart = patternOnlyImmaginaryPart.matcher(inputText);
     if(matcherBothParts.find() || matcherRealNumber.find()|| matcherOnlyImmaginaryPart.find() )
         return inputText;
     else if(inputText.matches("[+{1}]") || inputText.matches("[-{1}]"))
         return inputText;
     else{
         showMessageDialog(null,"Errore nel formato del numero o dell'operazione");
         return null;
     } 
    }    
    public void setTextField(String text){
            InputField.setText(text);
    }
     
    
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
            java.util.logging.Logger.getLogger(ProgrammableCalculatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgrammableCalculatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgrammableCalculatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgrammableCalculatorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new ProgrammableCalculatorGUI().setVisible(true);
                
            }
        });
    }
    public ProgrammableCalculatorController controller=new ProgrammableCalculatorController();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CalculatorPanel;
    private javax.swing.JTextField InputField;
    private javax.swing.JLabel LabelTextField;
    private javax.swing.JButton ProcessInputButton;
    // End of variables declaration//GEN-END:variables
}