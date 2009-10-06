/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package articlesectionclassifier;

import articlesectionclassifier.beans.ClassificationInput;
import articlesectionclassifier.beans.StringValuePair;
import articlesectionclassifier.classifying.ArticleSectionClassifier;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A main class providing a simple GUI to access the classifier {@link ArticleSectionClassifier}.
 * 
 * @author James
 */
public class Gui {

    /** 
     * Creates a new instance of Gui.
     * 
     */
    public Gui() {
        final ArticleSectionClassifier s = new ArticleSectionClassifier();
        JFrame jf = new JFrame("Article Section Classifier");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jp = new JPanel(new BorderLayout());
        JPanel results = new JPanel(new BorderLayout());
        final JTextField jtf = new JTextField();
        final JTextArea data = new JTextArea();
        data.setEditable(false);
        jtf.setEditable(false);
        JScrollPane jsp2 = new JScrollPane(data, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp2.setPreferredSize(new Dimension(1024, 300));
        results.add(jtf, BorderLayout.SOUTH);
        results.add(jsp2, BorderLayout.CENTER);
        final JTextArea jta = new JTextArea();
        final JScrollPane jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        final JPopupMenu jpm = new JPopupMenu();
        JMenuItem it = new JMenuItem("Paste");
        it.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
                try {
                    String s = (String) c.getData(DataFlavor.stringFlavor);
                    jta.append(s);
                } catch (UnsupportedFlavorException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        jpm.add(it);

        jta.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    doPopup(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    doPopup(e);
                }
            }

            private void doPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    jpm.show(e.getComponent(),
                            e.getX(), e.getY());
                }
            }
        });

        JButton jb = new JButton("Classify Text");
        //final DecimalFormat df2 = new DecimalFormat("0.####");
        jb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                s.classifyText(new ClassificationInput(jta.getText()));

                String st = "";
                jtf.setText("Classified as: " + s.getLastClassificationResult().getClassifiedAs());
                st += "Classified as: " + s.getLastClassificationResult().getClassifiedAs() + "\n\n";
                st += "Classification data:\n\n";
                st += "Token\tLog likelihood\n";

                LinkedList<StringValuePair> l = s.getLastClassificationResult().getTokensUsedForClassification();
                if (l != null) {
                    for (StringValuePair elem : l) {
                        st += "\"" + elem.getString() + "\"\t" + elem.getValue() + "\n";
                    }
                    st += "\n";
                    LinkedList<StringValuePair> l2 = s.getLastClassificationResult().getAllLikelihoods();
                    for (StringValuePair elem : l2) {
                        st += elem.toString() + "\n";
                    }
                }

                data.setText(st);
                //data.setCaretPosition(0);
            }
        });
        JButton jb2 = new JButton("Reset");
        jb2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jta.setText("");
            }
        });
        JPanel buttons = new JPanel();
        buttons.add(jb);
        buttons.add(jb2);

        jp.setPreferredSize(new Dimension(1024, 768));
        jp.add(jsp, BorderLayout.CENTER);
        jp.add(buttons, BorderLayout.NORTH);
        jp.add(results, BorderLayout.SOUTH);
        jf.setContentPane(jp);
        jf.pack();
        jf.setVisible(true);
    }
}
