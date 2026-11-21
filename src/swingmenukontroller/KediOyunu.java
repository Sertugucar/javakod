package swingmenukontroller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioSystem;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class KediOyunu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame pencere = new JFrame("Minik Kedi - Ev Macerası");
            pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pencere.setContentPane(new EvPaneli());
            pencere.pack();
            pencere.setLocationRelativeTo(null);
            pencere.setVisible(true);
        });
    }

    static class EvPaneli extends JPanel {
        private int kediX = 370, kediY = 370;
        private int hedefX = 370, hedefY = 370;
        private int mama = 65, sevgi = 60, enerji = 80;
        private int tirmlama;
        private int olaySayaci = 0, tirmalama = 0, animasyon = 0;
        private final Image kediResmi;
        private String mesaj = "Merhaba! Kedine iyi bak.";
        private boolean balikGorunur = true, ipGorunur = true;
        private final JButton mamaButon = new JButton("Mama ver");
        private final JButton sevButon = new JButton("Sev 🖐");
        private final JButton oyuncakButon = new JButton("Oyuncakla oyna");
        private final JButton sifirlaButon = new JButton("Sıfırla");

        EvPaneli() {
            Image yuklenenResim = null;
            try {
                File resimDosyasi = new File("src/swingmenukontroller/kedi.png");
                if (resimDosyasi.isFile()) yuklenenResim = ImageIO.read(resimDosyasi);
            } catch (IOException ex) {
                System.err.println("kedi.png okunamadı: " + ex.getMessage());
            }
            kediResmi = yuklenenResim;
            setLayout(null); setPreferredSize(new java.awt.Dimension(850, 580));
            setBackground(new Color(244, 225, 190));
            mamaButon.setBounds(30, 515, 120, 36); sevButon.setBounds(160,515,120,36);
            oyuncakButon.setBounds(290,515,160,36); sifirlaButon.setBounds(710,515,110,36);
            add(mamaButon); add(sevButon); add(oyuncakButon); add(sifirlaButon);
            mamaButon.addActionListener(e -> mamaVer());
            sevButon.addActionListener(e -> sev());
            oyuncakButon.addActionListener(e -> oyna());
            sifirlaButon.addActionListener(e -> sifirla());
            addMouseListener(new MouseAdapter() {
                @Override public void mouseClicked(MouseEvent e) {
                    if(e.getY()<500 && e.getX()>20 && e.getX()<820) {
                        hedefX=e.getX(); hedefY=e.getY(); mesaj="Mırmır merakla etrafa bakıyor.";
                    }
                }
            });
            new Timer(35,e -> guncelle()).start();
            new Timer(9000,e -> rastgeleOlay()).start();
        }

        private void mamaVer() {
            if(mama>=100) { mesaj="Mama kabı zaten dolu!"; return; }
            mama=Math.min(100,mama+28); enerji=Math.min(100,enerji+8);
            hedefX=120; hedefY=420; mesaj="Mırmır iştahla mamasını yiyor."; repaint();
        }
        private void sev() {
            sevgi=Math.min(100,sevgi+18); mesaj="Mırmır sevildiği için mutlu! Mırlıyor...";
            Thread ses=new Thread(KediOyunu::mirildamaSesi,"kedi-mirlama"); ses.setDaemon(true); ses.start(); repaint();
        }
        private void oyna() {
            if(enerji<10) {mesaj="Mırmır yoruldu. Biraz dinlensin.";return;}
            enerji=Math.max(0,enerji-12); balikGorunur=true; ipGorunur=true;
            hedefX=540; hedefY=420; mesaj="Mırmır oyuncak balığın peşinden koşuyor!"; repaint();
        }
        private void rastgeleOlay() {
            olaySayaci++;
            if(olaySayaci%3==0) {
                tirmlamaOlayi();
            } else if(olaySayaci%3==1) {
                hedefX=80+(int)(Math.random()*680); hedefY=245+(int)(Math.random()*205);
                mesaj="Mırmır evin içinde geziniyor.";
            } else {
                hedefX=500; hedefY=420; mesaj="Mırmır ip yumağıyla oynuyor.";
                ipGorunur=true;
            }
            repaint();
        }
        private void tirmlamaOlayi() {
            tirmlama++; sevgi=Math.max(0,sevgi-5);
            mesaj="Yaramazlık! Mırmır koltuğu tırmaladı! ("+tirmlama+")"; repaint();
        }
        private void sifirla() {
            mama=65;sevgi=60;enerji=80;olaySayaci=0;tirmlama=0;
            kediX=hedefX=370;kediY=hedefY=370;mesaj="Merhaba! Kedine iyi bak.";repaint();
        }
        private void guncelle() {
            if(kediX<hedefX)kediX+=Math.min(3,hedefX-kediX); else if(kediX>hedefX)kediX-=Math.min(3,kediX-hedefX);
            if(kediY<hedefY)kediY+=Math.min(2,hedefY-kediY); else if(kediY>hedefY)kediY-=Math.min(2,kediY-hedefY);
            animasyon++; repaint();
        }

        @Override protected void paintComponent(Graphics g0) {
            super.paintComponent(g0); Graphics2D g=(Graphics2D)g0.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            // Ev duvarı ve ahşap zemin
            g.setColor(new Color(248,237,216));g.fillRect(0,0,850,480);
            g.setColor(new Color(164,111,72));g.fillRect(0,475,850,35);
            g.setColor(new Color(205,155,105)); for(int x=0;x<850;x+=85)g.drawLine(x,475,x,510);
            // Pencere
            g.setColor(new Color(118,190,220));g.fillRoundRect(55,55,150,115,8,8);
            g.setColor(Color.WHITE);g.setStroke(new BasicStroke(7));g.drawLine(130,57,130,168);g.drawLine(58,112,202,112);
            // Halı
            g.setColor(new Color(194,117,111));g.fillRoundRect(245,340,355,130,24,24);
            g.setColor(new Color(231,174,145));g.setStroke(new BasicStroke(3));g.drawRoundRect(258,352,329,105,18,18);
            // Koltuk
            g.setColor(new Color(60,130,145));g.fillRoundRect(625,270,170,165,28,28);
            g.setColor(new Color(42,103,118));g.fillRoundRect(610,330,200,112,22,22);
            g.setColor(new Color(80,155,165));g.fillRoundRect(642,285,136,75,20,20);
            g.setColor(new Color(225,215,195));g.setFont(new Font("SansSerif",Font.BOLD,18));g.drawString("〰 〰",695,402);
            // Mama kabı
            g.setColor(new Color(80,145,190));g.fillOval(72,425,90,34);g.setColor(new Color(120,185,220));g.fillOval(78,420,78,24);
            g.setColor(new Color(135,85,45));g.fillOval(91,425,12,8);g.fillOval(112,430,12,8);g.fillOval(133,424,12,8);
            // Oyuncaklar
            if(balikGorunur){g.setColor(new Color(239,173,62));g.fillOval(520,415,43,23);g.fillPolygon(new int[]{554,573,554},new int[]{426,415,438},3);}
            if(ipGorunur){g.setColor(new Color(160,90,190));g.setStroke(new BasicStroke(5));g.drawArc(465,435,34,30,0,300);g.drawArc(476,432,30,30,160,300);}
            // Kedi yüzü ve gövdesi
            int ziplama=(animasyon/8)%2==0?0:3;
            if (kediResmi != null) {
                g.drawImage(kediResmi, kediX - 52, kediY - 55 + ziplama, 104, 100, this);
            } else {
                cizUcBoyutluKedi(g, ziplama);
            }
            // Durum göstergeleri ve mesaj
            g.setColor(new Color(60,55,50));g.setFont(new Font("SansSerif",Font.BOLD,19));g.drawString("Mırmır'ın Evi",25,30);
            g.setFont(new Font("SansSerif",Font.PLAIN,15));g.drawString("Mama",270,31);bar(g,320,17,mama,new Color(215,153,65));
            g.drawString("Sevgi",475,31);bar(g,525,17,sevgi,new Color(225,115,135));
            g.drawString("Enerji",665,31);bar(g,715,17,enerji,new Color(105,175,110));
            g.setColor(new Color(255,255,255,220));g.fillRoundRect(230,75,455,45,15,15);
            g.setColor(new Color(60,55,50));g.setFont(new Font("SansSerif",Font.BOLD,15));g.drawString(mesaj,245,103);
            g.dispose();
        }
        private void bar(Graphics2D g,int x,int y,int deger,Color renk){
            g.setColor(new Color(220,215,205));g.fillRoundRect(x,y,100,14,8,8);
            g.setColor(renk);g.fillRoundRect(x,y,deger,14,8,8);
        }

        private void cizUcBoyutluKedi(Graphics2D g, int ziplama) {
            int x=kediX, y=kediY+ziplama;
            g.setColor(new Color(65,48,38,55)); g.fillOval(x-43,kediY+24,88,15);
            g.setStroke(new BasicStroke(13,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
            g.setColor(new Color(166,91,45)); g.drawArc(x+18,y-3,43,45,205,205);
            g.setStroke(new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
            g.setColor(new Color(255,190,112)); g.drawArc(x+20,y-5,39,42,210,180);
            g.setPaint(new LinearGradientPaint(new Point2D.Float(x-35,y-25),new Point2D.Float(x+35,y+27),
                    new float[]{0f,0.48f,1f},new Color[]{new Color(255,204,133),new Color(236,145,72),new Color(163,83,43)}));
            g.fillOval(x-43,y-24,86,55);
            g.setPaint(new LinearGradientPaint(new Point2D.Float(x-25,y+4),new Point2D.Float(x+25,y+24),
                    new float[]{0f,1f},new Color[]{new Color(255,213,157),new Color(191,105,52)}));
            g.fillRoundRect(x-33,y+12,25,19,12,12); g.fillRoundRect(x+8,y+12,25,19,12,12);
            g.setColor(new Color(177,91,47));
            g.fillPolygon(new int[]{x-34,x-29,x-4},new int[]{y-23,y-59,y-35},3);
            g.fillPolygon(new int[]{x+4,x+29,x+34},new int[]{y-35,y-59,y-23},3);
            g.setColor(new Color(255,182,163));
            g.fillPolygon(new int[]{x-28,x-26,x-11},new int[]{y-31,y-49,y-35},3);
            g.fillPolygon(new int[]{x+11,x+26,x+28},new int[]{y-35,y-49,y-31},3);
            g.setPaint(new LinearGradientPaint(new Point2D.Float(x-25,y-52),new Point2D.Float(x+23,y+1),
                    new float[]{0f,0.55f,1f},new Color[]{new Color(255,211,151),new Color(242,161,91),new Color(177,91,48)}));
            g.fillOval(x-32,y-48,64,57);
            g.setColor(new Color(255,225,185,170)); g.fillOval(x-23,y-43,27,12);
            g.setColor(new Color(157,86,48,190)); g.setStroke(new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
            g.drawArc(x-9,y-45,18,21,245,50); g.drawArc(x-18,y-42,13,17,245,45); g.drawArc(x+5,y-42,13,17,250,45);
            g.drawLine(x-25,y-14,x-17,y-11); g.drawLine(x+17,y-11,x+25,y-14);
            g.setPaint(new LinearGradientPaint(new Point2D.Float(x,y-18),new Point2D.Float(x,y+9),
                    new float[]{0f,1f},new Color[]{new Color(255,239,211),new Color(232,179,135)}));
            g.fillOval(x-22,y-17,22,22); g.fillOval(x,y-17,22,22);
            g.setColor(new Color(74,57,47)); g.fillOval(x-17,y-29,8,11); g.fillOval(x+9,y-29,8,11);
            g.setColor(new Color(255,255,235)); g.fillOval(x-15,y-28,3,4); g.fillOval(x+11,y-28,3,4);
            g.setColor(new Color(208,105,115)); g.fillOval(x-4,y-12,8,6);
            g.setColor(new Color(111,71,61)); g.setStroke(new BasicStroke(1.5f));
            g.drawArc(x-5,y-9,6,6,200,140); g.drawArc(x,y-9,6,6,200,140);
            g.setColor(new Color(255,245,224,210)); g.setStroke(new BasicStroke(1.2f));
            g.drawLine(x-15,y-8,x-37,y-13); g.drawLine(x-15,y-3,x-39,y-3); g.drawLine(x-14,y+1,x-35,y+8);
            g.drawLine(x+15,y-8,x+37,y-13); g.drawLine(x+15,y-3,x+39,y-3); g.drawLine(x+14,y+1,x+35,y+8);
        }
    }

    private static void mirildamaSesi() {
        // Kısa, düşük frekanslı titreşimle oluşturulan mırlama benzeri ses.
        AudioFormat format=new AudioFormat(22050,16,1,true,false);
        try (SourceDataLine line=AudioSystem.getSourceDataLine(format)) {
            line.open(format); line.start(); byte[] ses=new byte[4096];
            for(int blok=0;blok<10;blok++) {
                for(int i=0;i<ses.length;i+=2) {
                    double t=(blok*ses.length/2+i/2)/22050.0;
                    double zarf=(0.35+0.65*Math.sin(Math.PI*(blok+1)/11.0));
                    double temel=Math.sin(2*Math.PI*45*t);
                    double uyumlu=Math.sin(2*Math.PI*90*t)*0.25;
                    short ornek=(short)Math.max(Short.MIN_VALUE,Math.min(Short.MAX_VALUE,(temel+uyumlu)*zarf*12500));
                    ses[i]=(byte)(ornek&0xff);ses[i+1]=(byte)((ornek>>8)&0xff);
                }
                line.write(ses,0,ses.length);
            }
            line.drain();
        } catch(Exception e) { java.awt.Toolkit.getDefaultToolkit().beep(); }
    }
}
