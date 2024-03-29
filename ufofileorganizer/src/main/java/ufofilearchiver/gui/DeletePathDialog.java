/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DeletePathDialog.java
 *
 * Created on 16-ago-2009, 11.18.03
 */

package ufofilearchiver.gui;

import ufofileorganizer.core.commands.facade.DeleteContainerStructure;
import ufofileorganizer.core.commands.facade.DeleteGroupStructure;
import ufofileorganizer.core.commands.facade.Save;
import ufofileorganizer.core.node.TreeNodeVector;
import ufofileorganizer.core.node.TreeNode_Container;
import ufofileorganizer.core.node.TreeNode_Group;

/**
 *
 * @author ufo
 */
public class DeletePathDialog extends javax.swing.JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Creates new form DeletePathDialog */
    public DeletePathDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_Path = new javax.swing.JLabel();
        jButton_Close = new javax.swing.JButton();
        jButton_Ok = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel_Path.setText("Path to delete");
        jLabel_Path.setName("jLabel_Path"); // NOI18N

        jButton_Close.setText("Close");
        jButton_Close.setName("jButton_Close"); // NOI18N
        jButton_Close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_CloseMouseClicked(evt);
            }
        });

        jButton_Ok.setText("Ok");
        jButton_Ok.setName("jButton_Ok"); // NOI18N
        jButton_Ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_OkMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_Ok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Close))
                    .addComponent(jLabel_Path, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_Path, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Close)
                    .addComponent(jButton_Ok))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_CloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_CloseMouseClicked
        setVisible(false);
    }//GEN-LAST:event_jButton_CloseMouseClicked

    private void jButton_OkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_OkMouseClicked
        if (_treeNodeVector instanceof TreeNode_Group) {
            new DeleteGroupStructure(( (TreeNode_Group) _treeNodeVector  ).get_group()   ).execute();
        }
        if (_treeNodeVector instanceof TreeNode_Container) {
           new DeleteContainerStructure( ((TreeNode_Container) _treeNodeVector).get_container()  ).execute();
        }
        
        new Save().execute();
        
        setVisible(false);
    }//GEN-LAST:event_jButton_OkMouseClicked


    public void setPath(TreeNodeVector aTreeNodeVector){
        this._treeNodeVector = aTreeNodeVector;
        //this.groupID = aTreeNodeVector.getID();
        this.groupName = aTreeNodeVector.getName();
        jLabel_Path.setText("Remove '" + groupName + "' " + aTreeNodeVector.getType() + "?");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Close;
    private javax.swing.JButton jButton_Ok;
    private javax.swing.JLabel jLabel_Path;
    // End of variables declaration//GEN-END:variables

    private String groupName;
    private TreeNodeVector _treeNodeVector;
}
