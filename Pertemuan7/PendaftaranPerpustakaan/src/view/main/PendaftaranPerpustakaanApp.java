package view.main;

import javax.swing.*;

public class PendaftaranPerpustakaanApp extends JFrame {
    public PendaftaranPerpustakaanApp() {
        setTitle("Aplikasi Pendaftaran Perpustakaan");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
       JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItemReset = new JMenuItem("Update");
        JMenuItem menuItemExit = new JMenuItem("Delete");

        menuItemReset.addActionListener(e -> ((PendaftaranPerpustakaanApp) getContentPane().getComponent(0)).resetForm());
                menuItemExit.addActionListener(e -> System.exit(0));
        
                menu.add(menuItemReset);
                menu.add(menuItemExit);
                menuBar.add(menu);
                setJMenuBar(menuBar);
        
                add(new PendaftaranPerpustakaanApp());
            }
        
            private Object resetForm() {
                throw new UnsupportedOperationException("Unimplemented method 'resetForm'");
            }
        
            public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PendaftaranPerpustakaanApp app = new PendaftaranPerpustakaanApp();
            app.setVisible(true);
        });
    }
}