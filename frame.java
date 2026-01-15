package JigsawGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class frame{
    public static void main(String[] args) {
        //创建游戏主界面
        JFrame gameFrame = new JFrame("Game window");
        gameFrame.setSize(600,620);
        gameFrame.setLayout(null);
        gameFrame.setDefaultCloseOperation(3);
        gameFrame.setAlwaysOnTop(true);

        boolean startNew = true;
        Timer t = new Timer(1000,new ActionListener() {
            int time = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                time++;
                System.out.println("TIME IS TICKING " + time + " seconds");
            }
            
        });//计时器

        JMenuBar menu = new JMenuBar();
        JMenu m1 = new JMenu("Welcome");
        JMenu m2 = new JMenu("About us");
        JMenu m3 = new JMenu("Function");

        gameFrame.setJMenuBar(menu);
        menu.add(m1);
        menu.add(m2);
        menu.add(m3);

        JMenuItem i1 = new JMenuItem("Welcome bro");
        JMenuItem i2 = new JMenuItem("Netease");
        JMenuItem i3 = new JMenuItem("Bilibili");
        JMenuItem i4 = new JMenuItem("Rules");
        JMenuItem i5 = new JMenuItem("Start game");
        JMenuItem i6 = new JMenuItem("Stop Timer");

        m1.add(i1);
        m2.add(i2);
        m2.add(i3);
        m3.add(i4);
        m3.add(i5);
        m3.add(i6);

        m2.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                System.out.println("Look at advertisement please");
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                System.out.println("Why not check the advertisement?");
            }

            @Override
            public void menuCanceled(MenuEvent e) {
                System.out.println("Fine");
            }
            
        });
        i2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Please look at my netease.");
                JFrame jf = new JFrame("Netease Cloud Music");
                jf.setSize(500,500);
                jf.setLayout(null);
                jf.setAlwaysOnTop(true);
                ImageIcon iii = new ImageIcon("C:\\Users\\zhangj\\Desktop\\HTML\\java\\JigsawGame\\\u7D20\u6750\\\u7D20\u6750\\image\\ad.png");
                JLabel jjll = new JLabel(iii);
                jjll.setSize(500,500);
                jf.add(jjll);
                jf.setVisible(true);
            }
            
        });

        i4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Rules for the button game");
                JFrame jf = new JFrame("Button Game Rules");
                jf.setSize(564,300);
                jf.setLayout(null);
                jf.setAlwaysOnTop(true);
                ImageIcon iii = new ImageIcon("C:\\Users\\zhangj\\Desktop\\HTML\\java\\JigsawGame\\\u7D20\u6750\\\u7D20\u6750\\image\\rule1.png");
                JLabel jjll = new JLabel(iii);
                jjll.setSize(564,300);
                jf.add(jjll);
                jf.setVisible(true);
            }
            
        });

        i5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("START THE TIMER!!!");
                t.restart();
            }
            
        });

        i6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (t.isRunning()){
                    System.out.println("Timer stopped.");
                    System.out.println("Why would you stop it early?");
                    t.stop();
                }else{
                    System.out.println("IS THIS FUNNY ?");
                }
            }
            
        });

        

        JButton button1 = new JButton("Click me");
        button1.setSize(100, 100);
        // "C:\\Users\\zhangj\\Desktop\\HTML\\java\\JigsawGame\\素材\\GIA\\all.jpg")
        Random r = new Random();

        button1.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                button1.setLocation(r.nextInt(500), r.nextInt(500));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("You are disgusting, you just moved the button!!!");
            }
            
        });
        JTextField jlll = new JTextField("You clicked the button 100 times");
        jlll.setLocation(10,300);
        jlll.setVisible(true);
        gameFrame.add(jlll);
        
        
        // t.start();

        button1.addMouseListener(new MouseListener() {
            int count = 0;
            boolean gaming = false;
            public int reset(Timer t, int count, boolean gaming){
                boolean gaming1 = isGaming(t, count, gaming);
                if (t.isRunning() && !gaming){
                    gaming = gaming1;
                    count = 0;
                    return 0;
                }return count;
            }
            public boolean isGaming(Timer t, int count, boolean gaming){
                if (t.isRunning() && !gaming){
                    return true;
                }else if (t.isRunning() && gaming){
                    return true;
                }
                return false;//逻辑是，只有非running的时候才需要重新reset
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("What");
                button1.setLocation(r.nextInt(500), r.nextInt(500));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                count = reset(t, count, gaming);
                gaming = isGaming(t, count, gaming);
                
                button1.setText("Don't click");
                count++;
                System.out.println("You have clicked the button " + count + " times.");
                if (count == 100){
                    t.stop();
                    System.out.println("TIMER STOPPED!");
                    
                    System.out.println("YOUR SCORE IS null");
                }else if (count == 200){
                    System.out.println("BE AWARE");
                    JFrame jf = new JFrame("WARNING");
                    jf.setSize(1024,800);
                    jf.setLayout(null);
                    jf.setAlwaysOnTop(true);
                    ImageIcon iii = new ImageIcon("C:\\Users\\zhangj\\Desktop\\HTML\\java\\JigsawGame\\\u7D20\u6750\\\u7D20\u6750\\image\\warning.png");
                    JLabel jjll = new JLabel(iii);
                    jjll.setSize(1024,800);
                    jf.add(jjll);
                    jf.setVisible(true);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Bruh");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button1.setText("Move away");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button1.setText("Click me");
                System.out.println("Thank you");
            }
            
        });
        gameFrame.add(button1);

        ImageIcon ii = new ImageIcon("C:\\Users\\zhangj\\Desktop\\HTML\\java\\JigsawGame\\素材\\GIA\\all.jpg");
        JLabel jl = new JLabel(ii);
        jl.setLocation(50,50);
        jl.setSize(500,500);
        gameFrame.add(jl);
        jl.setVisible(false);

        JRadioButton what = new JRadioButton("Display GIA");
        what.setSize(120, 40);

        what.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jl.setVisible(!jl.isVisible());
            }
        });
        gameFrame.add(what);



        gameFrame.setVisible(true);


        // while(true){
        //     Scanner sc = new Scanner(System.in);
        //     System.out.println("Bruh");
        //     String whatever = sc.next();
        //     if (whatever.equals("exit")){
        //         System.out.println("Ok");
        //         break;
        //     }
        //     else{
        //         System.out.println("Not the code");
        //     }
        // }
        
    }
}