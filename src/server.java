import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.text.*;

import java.net.*;
import java.io.*;



public class server implements ActionListener{
    JTextField text;
    static JPanel j2;
    static Box vertical = Box.createVerticalBox();
    static JFrame f =new JFrame();
    static DataOutputStream dout;
    static EmojiPicker emojiPicker;
    server(){
        f.setLayout(null);
        
        JPanel j1 =new JPanel();
        j1.setBackground(new Color(31,44,52,255));
        j1.setBounds(0, 0, 450, 50);
        j1.setLayout(null);
        f.add(j1);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2=i1.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back=new JLabel(i3);
        back.setBounds(10,15,20,20);
        j1.add(back);

        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/profile.png"));
        Image i5=i4.getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile=new JLabel(i6);
        profile.setBounds(30,5,50,40);
        j1.add(profile);

        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i8=i7.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel phone=new JLabel(i9);
        phone.setBounds(300,12,30,30);
        j1.add(phone);

        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i11=i10.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel video=new JLabel(i12);
        video.setBounds(350,12,30,30);
        j1.add(video);

        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14=i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel settings=new JLabel(i15);
        settings.setBounds(410,14,10,25);
        j1.add(settings);

        JLabel name=new JLabel("Pradeep");
        name.setBounds(80,10,100,30);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        j1.add(name);

        j2=new JPanel();
        j2.setBounds(3,60,430,540);
        j2.setBackground(new Color(9,19,26,255));
        f.add(j2);

        text = new JTextField();
        text.setBounds(60,600,275,60);
        text.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 15));
        text.setForeground(Color.BLACK);
        f.add(text);

        JButton emojiButton = new JButton("😊");
        emojiButton.setBounds(0, 600, 60, 60);
        emojiButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        emojiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (emojiPicker == null) {
                    emojiPicker = new EmojiPicker(f, text);
                }
                emojiPicker.setVisible(true);
            }
        });
        f.add(emojiButton);
       
        JButton send =new JButton("Send");
        send.setBounds(334,600,100,60);
        send.setForeground(Color.WHITE);
        send.setBackground(new Color(7,94,84));
        send.addActionListener(this);
        f.add(send);

        back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });

        f.setSize(450, 700);
        f.setLocation(200, 50);
        f.getContentPane().setBackground(Color.BLACK);

        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        try {
            String msg = text.getText();
            JPanel  j3 = formatlabel(msg);
            j2.setLayout(new BorderLayout());
            JPanel right=new JPanel(new BorderLayout());
            right.add(j3,BorderLayout.LINE_END);
            vertical.add(right);
            right.setBackground(new Color(9,19,26,255));
            vertical.add(Box.createVerticalStrut(15));
            j2.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(msg);


            text.setText("");

            f.repaint();
            f.invalidate();
            f.validate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    public static JPanel formatlabel(String msg){
        JPanel panel =new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output =new JLabel(msg);
        output.setFont(new Font("Segoe UI Emoji",Font.BOLD,16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,15));
        panel.add(output);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.setBackground(new Color(9,19,26,255));
        time.setForeground(Color.WHITE);
        panel.add(time);
        return panel;
    }

    class EmojiPicker extends JDialog {
        @SuppressWarnings("unused")
        private JTextField textField;
    
        EmojiPicker(Frame parent, JTextField textField) {
            super(parent, "Emoji Picker", true);
            this.textField = textField;
            setLayout(new GridLayout(4, 8));
            setResizable(false);
            setSize(300, 200);
            setLocationRelativeTo(parent);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
            String[] emojis = {
                "\uD83D\uDE0A", "\uD83D\uDE02", "\uD83D\uDE0D", "\uD83D\uDE0E", "\uD83D\uDE07", 
                "\uD83D\uDE1C", "\uD83D\uDE21", "\uD83D\uDE2D", "\u2764\uFE0F", "\uD83D\uDC4D", 
                "\uD83D\uDC4E", "\uD83D\uDCAA", "\uD83D\uDE4C", "\uD83D\uDC4F", "\uD83D\uDE4F", 
                "\uD83D\uDD25", "\uD83C\uDF89", "\uD83C\uDF88", "\u2728", "\uD83D\uDE19", 
                "\uD83D\uDD95", "\uD83C\uDF1F", "\uD83C\uDF4E", "\uD83C\uDF55", "\uD83D\uDE97", 
                "\u26BD", "\uD83C\uDFB8", "\uD83C\uDFA4", "\uD83C\uDFAE", "\uD83D\uDCF7", 
                "\uD83D\uDCBB", "\uD83D\uDCDA", "\u2708\uFE0F", "\uD83C\uDFD6\uFE0F", "\uD83D\uDC7B", 
                "\uD83D\uDE08"
            };            
                       
            for (String emoji : emojis) {
                JButton button = new JButton() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g.create();
                        Font font = new Font("Segoe UI Emoji", Font.PLAIN, 20);
                        g2d.setFont(font);
                        FontMetrics fm = g2d.getFontMetrics();
                        int x = (getWidth() - fm.stringWidth(emoji)) / 2;
                        int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                        g2d.drawString(emoji, x, y);
                        g2d.dispose();
                    }
                };
                button.setPreferredSize(new Dimension(50, 50));
                button.addActionListener(e -> textField.setText(textField.getText() + emoji));
                add(button);
            }
        }
    }
   public static void main(String[] args) {
       new server();

       try(ServerSocket skt=new ServerSocket(6001)){
        
        while(true){
            Socket s= skt.accept();
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
            while(true){
                j2.setLayout(new BorderLayout());
                String m = din.readUTF();
                JPanel panel = formatlabel(m);
                JPanel left = new JPanel(new BorderLayout() );
                left.add(panel,BorderLayout.LINE_START);
                left.setBackground(new Color(9,19,26,255));
                vertical.add(left);
                j2.add(vertical,BorderLayout.PAGE_START);
                f.validate();
            }
        }

       } catch(Exception e){

        e.printStackTrace();
       }
   }
}