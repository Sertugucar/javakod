package swingmenukontroller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MancinikOyunu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame pencere = new JFrame("Mancınık Oyunu - Kuleyi Devirelim!");
            pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pencere.setContentPane(new OyunPaneli());
            pencere.pack();
            pencere.setLocationRelativeTo(null);
            pencere.setVisible(true);
        });
    }

    static class OyunPaneli extends JPanel {
        private static final int ZEMIN = 510;
        private static final Point MANCINIK = new Point(155, 420);
        private final List<Blok> bloklar = new ArrayList<>();
        private final List<Dusman> dusmanlar = new ArrayList<>();
        private double topX = MANCINIK.x, topY = MANCINIK.y, hizX, hizY;
        private boolean topHazir = true, surukleniyor, atisSuruyor;
        private int atisSayisi;
        private String mesaj = "Topu fareyle geriye çekip bırak!";

        OyunPaneli() {
            setPreferredSize(new Dimension(900, 600));
            setBackground(new Color(190, 225, 250));
            kuleyiKur();
            new Timer(16, e -> guncelle()).start();
            MouseAdapter fare = new MouseAdapter() {
                @Override public void mousePressed(MouseEvent e) {
                    if (e.getX() >= 738 && e.getX() <= 873 && e.getY() <= 62) {
                        oyunuSifirla(); return;
                    }
                    if (topHazir && e.getPoint().distance(topX, topY) < 30) surukleniyor = true;
                }
                @Override public void mouseDragged(MouseEvent e) {
                    if (!surukleniyor) return;
                    topX = Math.max(85, Math.min(205, e.getX()));
                    topY = Math.max(340, Math.min(470, e.getY()));
                    repaint();
                }
                @Override public void mouseReleased(MouseEvent e) {
                    if (!surukleniyor) return;
                    surukleniyor = false; topHazir = false; atisSuruyor = true; atisSayisi++;
                    hizX = (MANCINIK.x - topX) * 0.22;
                    hizY = (MANCINIK.y - topY) * 0.22;
                    mesaj = "Atış! Bakalım kuleye isabet edecek mi?";
                }
            };
            addMouseListener(fare); addMouseMotionListener(fare);
        }

        private void kuleyiKur() {
            bloklar.clear(); dusmanlar.clear();
            // 8 katlı yüksek kule; bloklar zemine düzgün oturur.
            for (int kat = 0; kat < 8; kat++) {
                int y = ZEMIN - 20 - kat * 40;
                if (kat % 2 == 0) {
                    bloklar.add(new Blok(671, y)); bloklar.add(new Blok(713, y));
                } else bloklar.add(new Blok(692, y));
            }
            dusmanlar.add(new Dusman(692, 452));
            dusmanlar.add(new Dusman(692, 372));
            dusmanlar.add(new Dusman(692, 292));
            dusmanlar.add(new Dusman(692, 212));
        }

        private void oyunuSifirla() {
            kuleyiKur(); topX = MANCINIK.x; topY = MANCINIK.y; hizX = hizY = 0;
            topHazir = true; atisSuruyor = false; surukleniyor = false; atisSayisi = 0;
            mesaj = "Topu fareyle geriye çekip bırak!"; repaint();
        }

        private void guncelle() {
            if (!atisSuruyor) return;
            // Yerçekimi ve önceki konumla süpürme, hızlı topun blokların içinden geçmesini önler.
            double eskiX = topX, eskiY = topY;
            hizY += 0.30; topX += hizX; topY += hizY;
            boolean isabet = false;
            for (Blok b : bloklar) {
                if (!b.aktif) continue;
                if (topX + 15 >= b.x - 21 && topX - 15 <= b.x + 21
                        && topY + 15 >= b.y - 20 && topY - 15 <= b.y + 20) {
                    b.aktif = false; isabet = true;
                    b.vx = hizX * 0.45; b.vy = hizY * 0.35;
                    hizX *= 0.72; hizY *= 0.72;
                    break;
                }
            }
            for (Dusman d : dusmanlar) if (d.aktif && Math.hypot(topX-d.x, topY-d.y) < 25) {
                d.aktif = false; isabet = true;
            }
            for (Blok b : bloklar) if (b.aktif) {
                b.vy += 0.28; b.x += b.vx; b.y += b.vy; b.vx *= 0.98;
                if (b.y + 20 >= ZEMIN) { b.y = ZEMIN - 20; b.vy = 0; }
                if (Math.abs(b.vx) + Math.abs(b.vy) > 2)
                    for (Dusman d : dusmanlar) if (d.aktif && Math.hypot(b.x-d.x,b.y-d.y)<32) d.aktif=false;
            }
            boolean durdu = Math.abs(hizX) < 0.35 && Math.abs(hizY) < 0.7 && topY > ZEMIN - 30;
            boolean ekranDisi = topX < -30 || topX > getWidth()+30 || topY > getHeight()+30;
            if (durdu || ekranDisi) {
                atisSuruyor = false; topHazir = true; topX=MANCINIK.x; topY=MANCINIK.y;
                boolean kazandi = dusmanlar.stream().noneMatch(d -> d.aktif);
                mesaj = kazandi ? "Tebrikler, kuleyi yıktın! Yeni oyun için sağ üstteki düğmeye bas." :
                    (isabet ? "İsabet ettin! Yeni atış için topu çek." : "Iska! Açıyı ve çekiş gücünü değiştirip tekrar dene.");
            }
            repaint();
        }

        @Override protected void paintComponent(Graphics grafik) {
            super.paintComponent(grafik);
            Graphics2D g=(Graphics2D)grafik.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.WHITE); g.fillOval(70,55,110,34); g.fillOval(120,42,95,48);
            g.setColor(new Color(88,160,78)); g.fillRect(0,ZEMIN,getWidth(),getHeight()-ZEMIN);
            g.setColor(new Color(112,73,45)); g.fillRect(113,444,18,66); g.fillRect(179,444,18,66);
            g.setStroke(new BasicStroke(8)); g.setColor(new Color(118,74,42));
            g.drawLine(MANCINIK.x,MANCINIK.y,(int)topX,(int)topY);
            for (Blok b:bloklar) if(b.aktif) {
                g.setColor(new Color(178,111,59)); g.fillRoundRect((int)b.x-21,(int)b.y-20,42,40,7,7);
                g.setColor(new Color(235,179,108)); g.setStroke(new BasicStroke(2)); g.drawRoundRect((int)b.x-21,(int)b.y-20,42,40,7,7);
            }
            for(Dusman d:dusmanlar) if(d.aktif) {
                g.setColor(new Color(220,70,58)); g.fillOval(d.x-12,d.y-12,24,24);
                g.setColor(Color.WHITE); g.fillOval(d.x-6,d.y-5,4,4); g.fillOval(d.x+3,d.y-5,4,4);
            }
            g.setColor(new Color(55,55,65)); g.fillOval((int)topX-15,(int)topY-15,30,30);
            g.setColor(Color.LIGHT_GRAY); g.drawOval((int)topX-15,(int)topY-15,30,30);
            g.setFont(new Font("SansSerif",Font.BOLD,22)); g.setColor(new Color(42,54,71)); g.drawString("MANCINIK OYUNU",25,34);
            g.setFont(new Font("SansSerif",Font.PLAIN,15)); g.drawString(mesaj,25,65); g.drawString("Atış: "+atisSayisi,25,90);
            g.setColor(new Color(45,55,66)); g.fillRoundRect(738,22,135,40,12,12);
            g.setColor(Color.WHITE); g.setFont(new Font("SansSerif",Font.BOLD,14)); g.drawString("YENİ OYUN",758,48);
            g.dispose();
        }
    }

    static class Blok {
        double x,y,vx,vy; boolean aktif=true;
        Blok(double x,double y){this.x=x;this.y=y;}
    }
    static class Dusman {
        int x,y; boolean aktif=true;
        Dusman(int x,int y){this.x=x;this.y=y;}
    }
}
