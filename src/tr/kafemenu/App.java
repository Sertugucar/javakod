package tr.kafemenu;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import tr.kafemenu.ui.MainFrame;

/** Uygulamanın başlangıç noktası. */
public final class App {
    private App() {
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Sistem teması yüklenemezse Swing'in varsayılan teması kullanılır.
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
