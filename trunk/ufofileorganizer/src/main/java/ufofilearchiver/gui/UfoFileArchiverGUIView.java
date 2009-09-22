/*
 * UfoFileArchiverGUIView.java
 */

package ufofilearchiver.gui;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.*;
import java.util.Collections;

import javax.swing.*;
import ufofileorganizer.core.*;
import ufofileorganizer.core.commands.ICommand;
import ufofileorganizer.core.commands.facade.Close;
import ufofileorganizer.core.commands.facade.MainTree;
import ufofileorganizer.core.commands.facade.Save;
import ufofileorganizer.core.node.*;
import ufofilearchiver.gui.TreeTransferHandler.TreeTransferHandler;
import ufofilearchiver.gui.tableModel.*;
import ufofilearchiver.gui.utils.StringFormatter;

import javax.swing.JFrame;
import javax.swing.tree.*;
//import sqlUtils.*;


/**
 * The application's main frame.
 */
public class UfoFileArchiverGUIView extends FrameView implements ScanObserver{

   
    public UfoFileArchiverGUIView(SingleFrameApplication app) {
        super(app);
        
        /**
         * TODO 
         * il parametro del file va passato scelto dall'utente
         */
        //data.open("/home/ufo/Scrivania/Francesco/workspace/java/UfoFileArchiver/UfoFileArchiver/data","test","sa","");
        data = ApplicationBuilder.getApplicationBuilder();//("data","test","sa","");

        initComponents();
        
        data.attach(this);
        
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                } else if ("message".equals(propertyName)) {
                	@SuppressWarnings("unused")
                	String text = (String)(evt.getNewValue());
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    @SuppressWarnings("unused")
					int value = (Integer)(evt.getNewValue());
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = UfoFileArchiverGUI.getApplication().getMainFrame();
            aboutBox = new AboutBoxDialog(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        UfoFileArchiverGUI.getApplication().show(aboutBox);
    }

     public void showFindDialog() {
        if (findDialog == null) {
            JFrame mainFrame = UfoFileArchiverGUI.getApplication().getMainFrame();
            findDialog = new FindDialog(mainFrame, false);
            findDialog.setLocationRelativeTo(mainFrame);
        }
        UfoFileArchiverGUI.getApplication().show(findDialog);
    }
     
     public void showInsertContainerDialog() {
         if (insertNewContainer == null) {
             JFrame mainFrame = UfoFileArchiverGUI.getApplication().getMainFrame();
             insertNewContainer = new InsertNewContainer(mainFrame, false);
             insertNewContainer.setLocationRelativeTo(mainFrame);
         }
         UfoFileArchiverGUI.getApplication().show(insertNewContainer);
     }
     

     public void showInsertPathDialog() {
        if (insertPathDialog == null) {
            JFrame mainFrame = UfoFileArchiverGUI.getApplication().getMainFrame();
            insertPathDialog = new InsertNewPathDialog(mainFrame, false);
            insertPathDialog.setLocationRelativeTo(mainFrame);
        }
        UfoFileArchiverGUI.getApplication().show(insertPathDialog);
    }
     
    public void showRemovePathDialog(TreeNodeVector vect) {
        if ( !(vect instanceof TreeNode_Group) &&
        		!(vect instanceof TreeNode_Container) ) {
           return;
        }    	
    	
    	if (removePathDialog == null) {
            JFrame mainFrame = UfoFileArchiverGUI.getApplication().getMainFrame();
            removePathDialog = new DeletePathDialog(mainFrame, false);
            removePathDialog.setLocationRelativeTo(mainFrame);
        }
        
        removePathDialog.setPath(vect);
        UfoFileArchiverGUI.getApplication().show(removePathDialog);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_Volume = new javax.swing.JTextField();
        jTextField_Location = new javax.swing.JTextField();
        jTextField_Size = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_Contains = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        AddContainer = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(UfoFileArchiverGUIView.class);
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonFindMouseClicked(evt);
            }
        });

        jSplitPane1.setName("jSplitPane1"); // NOI18N

        jSplitPane2.setDividerLocation(90);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setName("jSplitPane2"); // NOI18N

        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setName("jSplitPane3"); // NOI18N

        jScrollPane3.setMinimumSize(new java.awt.Dimension(250, 25));
        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setName("Table1");
        jScrollPane3.setViewportView(jTable1);

        jSplitPane3.setLeftComponent(jScrollPane3);

        jScrollPane4.setMaximumSize(new java.awt.Dimension(80, 32767));
        jScrollPane4.setMinimumSize(new java.awt.Dimension(80, 25));
        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jPanel2.setMinimumSize(new java.awt.Dimension(80, 80));
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(644, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(298, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel2);

        jSplitPane3.setRightComponent(jScrollPane4);

        jSplitPane2.setRightComponent(jSplitPane3);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(80, 80));
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        jScrollPane2.setPreferredSize(new java.awt.Dimension(80, 80));

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jTextField_Volume.setEditable(false);
        jTextField_Volume.setFont(resourceMap.getFont("jTextField_Volume.font")); // NOI18N
        jTextField_Volume.setText(resourceMap.getString("jTextField_Volume.text")); // NOI18N
        jTextField_Volume.setName("jTextField_Volume"); // NOI18N

        jTextField_Location.setEditable(false);
        jTextField_Location.setText(resourceMap.getString("jTextField_Location.text")); // NOI18N
        jTextField_Location.setName("jTextField_Location"); // NOI18N

        jTextField_Size.setEditable(false);
        jTextField_Size.setText(resourceMap.getString("jTextField_Size.text")); // NOI18N
        jTextField_Size.setName("jTextField_Size"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jTextField_Contains.setEditable(false);
        jTextField_Contains.setText(resourceMap.getString("jTextField_Contains.text")); // NOI18N
        jTextField_Contains.setName("jTextField_Contains"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_Volume, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField_Size, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Contains, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                    .addComponent(jTextField_Location, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_Volume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_Contains, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        jSplitPane2.setLeftComponent(jScrollPane2);

        jSplitPane1.setRightComponent(jSplitPane2);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(80, 25));
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(80, 382));

        jTree1.setName("jTree1"); // NOI18N
        
	ICommand mainTreeBuilder = new MainTree();
        mainTreeBuilder.execute();
        
        jTree1 = new javax.swing.JTree( (TreeNodeVector) mainTreeBuilder.result());	
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jTree1.setTransferHandler(new TreeTransferHandler(jTree1));
        jTree1.setDropMode(DropMode.ON);
        jTree1.setDragEnabled(true);
        jTree1.getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION);
        jScrollPane1.setViewportView(jTree1);

        jSplitPane1.setLeftComponent(jScrollPane1);

        AddContainer.setText(resourceMap.getString("AddContainer.text")); // NOI18N
        AddContainer.setName("AddContainer"); // NOI18N
        AddContainer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddContainerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddContainer)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(AddContainer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
       try {
    	   TreeNodeVector vect = (TreeNodeVector) ( (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent()).getUserObject();
    	   
    	   
    	   TreeNodeVector sameTypeVector = vect.getChildrenOfSameType();
    	   Collections.sort( sameTypeVector, new TreeNodeComparator());
    	   TreeNodeVector subTypeVector = vect.getChildrenOfSubType();
    	   Collections.sort( subTypeVector, new TreeNodeComparator());

    	   TreeNodeVector childrenVector = TreeNodeFactory.getTreeNodeVector(null);
    	   childrenVector.addAll(sameTypeVector);
    	   childrenVector.addAll(subTypeVector);
    	   
           jTable1.setModel(new ArchiverTableModel(childrenVector));
           volumeInfo_Update(vect);
       }
       catch (RuntimeException e){
    	   
       }
       
    }//GEN-LAST:event_jTree1ValueChanged

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        showInsertPathDialog();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        TreeNodeVector vect = (TreeNodeVector) ( (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent()).getUserObject();
       // System.out.println("Nome del gruppo da rimuovere: " + vect.getName() + "; Id del gruppo: " + vect.getID());
        //data.removePath(vect.getID());ghj
        showRemovePathDialog(vect);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButtonFindMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFindMouseClicked
        showFindDialog();
    }//GEN-LAST:event_jButtonFindMouseClicked

    private void AddContainerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddContainerMouseClicked
    	showInsertContainerDialog();
    }//GEN-LAST:event_AddContainerMouseClicked


    private void volumeInfo_Update(TreeNodeVector vect){
        jTextField_Contains.setText("");
        jTextField_Location.setText(vect.getLocation());
        jTextField_Size.setText( StringFormatter.formatSize( vect.getSize().toString() ) );
        jTextField_Volume.setText(vect.getParentGroupName());
    }


    public void update(){
        //System.out.println("Update lanciato; DA IMPLEMENTARE LE POLITICHE DI AGGIORNAMENTO DELL'INTERFACCIA");
        
        
        ICommand mainTreeBuilder = new MainTree();
        mainTreeBuilder.execute();
        
        jTree1 = new javax.swing.JTree((TreeNodeVector) mainTreeBuilder.result());
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jTree1.setTransferHandler(new TreeTransferHandler(jTree1));
        jTree1.setDropMode(DropMode.ON);
        jTree1.setDragEnabled(true);
        jTree1.getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION);

        jScrollPane1.setViewportView(jTree1);
        
        return;
    }
    
    public void close(){
    	//new Save().execute();
    	new Close().execute();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddContainer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_Contains;
    private javax.swing.JTextField jTextField_Location;
    private javax.swing.JTextField jTextField_Size;
    private javax.swing.JTextField jTextField_Volume;
    private javax.swing.JTree jTree1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    public ApplicationBuilder data = null;
    private JDialog aboutBox;
    private InsertNewPathDialog insertPathDialog;
    private DeletePathDialog removePathDialog;
    private FindDialog findDialog;
    private InsertNewContainer insertNewContainer;






}
