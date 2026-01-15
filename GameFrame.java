package JigsawGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class GameFrame extends JFrame implements KeyListener{
    int[] arr = randomizeArr();
    int pos = get0Index(arr);
    int moveCount = 0;
    final int[] ARR = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
    int[] tempArr = new int[16];
    boolean realVictory = false;
    boolean successActivated = false;
    String[] jigsawImage = {"\u7D20\u6750\\\\sishuo\\\\", "\u7D20\u6750\\\\GIA\\\\", "\u7D20\u6750\\\\Minecraft\\\\", "\u7D20\u6750\\\\wood\\\\"};
    int jigsawIndex = 0;

    //创建游戏主界面
    public GameFrame(){
        initJFrame();
        setUpMenu();
        initImage();
        this.addKeyListener(null);
        this.setVisible(true);//窗口可见(放在最后)
    }

    public void initJFrame(){
        this.setTitle("Jigsaw Game");
        this.setSize(600,700);
        this.setLocationRelativeTo(null);//显示在正中央
        this.setDefaultCloseOperation(3);//关闭窗口后停止程序运行
        this.addKeyListener(this);//本类里有keyListener，直接用this调用
    }

    public void setUpMenu(){
        JMenuBar menu = new JMenuBar();//菜单栏
        JMenu functionMenu = new JMenu("Function");//菜单栏内的选项
        JMenu aboutMenu = new JMenu("About us");
        this.setJMenuBar(menu);//设置菜单
        menu.add(functionMenu);
        menu.add(aboutMenu);

        JMenuItem restartMI = new JMenuItem("Restart");//选项hover
        // JMenuItem reLogInMI = new JMenuItem("re-Log In");
        JMenuItem changeIconMI = new JMenuItem("Change Image");
        JMenuItem exitMI = new JMenuItem("Exit");

        functionMenu.add(restartMI);
        functionMenu.add(changeIconMI);
        // functionMenu.add(reLogInMI);
        functionMenu.add(exitMI);

        JMenuItem advertisementMI = new JMenuItem("Advertisement");
        aboutMenu.add(advertisementMI);
        
        restartMI.addActionListener(new ActionListener() {
            //重新开始游戏
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Restart");
                GameFrame.this.arr = randomizeArr();
                GameFrame.this.pos = get0Index(GameFrame.this.arr);
                GameFrame.this.realVictory = false;
                GameFrame.this.successActivated = false;
                GameFrame.this.moveCount = 0;
                initImage();
            }
            
        });

        changeIconMI.addActionListener(new ActionListener() {
            //改变图片
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Change Picture");
                Random r = new Random();
                GameFrame.this.jigsawIndex = r.nextInt(GameFrame.this.jigsawImage.length);
                initImage();
            }
            
        });

        exitMI.addActionListener(new ActionListener() {
            //退出游戏
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit game");
                System.exit(0);
            }
            
        });

        advertisementMI.addActionListener(new ActionListener() {
            //广告
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Please look at my netease.");
                JDialog jd = new JDialog();//弹窗对象
                jd.setTitle("Netease Cloud Music");
                jd.setSize(500,500);
                jd.setLocationRelativeTo(null);
                jd.setAlwaysOnTop(true);
                ImageIcon iii = new ImageIcon("\u7D20\u6750\\\u7D20\u6750\\image\\ad.png");
                JLabel jjll = new JLabel(iii);
                jjll.setSize(500,500);
                jd.add(jjll);
                jd.setModal(true);//让下面的页面不关掉就操作不了！
                jd.setVisible(true);
            }
            
        });
    }

    

    public void initImage(){
        this.getContentPane().removeAll();

        //取消默认的居中放置
        this.setLayout(null);

        if (realVictory && successActivated){
            success();
        }

        JLabel countJLabel = new JLabel("Move Count:" + this.moveCount);
        countJLabel.setBounds(50, 30, 100, 20);
        this.getContentPane().add(countJLabel);
        
        int[] arr = this.arr;//只能打乱一次，所以放在成员变量处打乱

        for (int i = 0; i < 16; i++) {
            //ImageIcon对象先创建
            ImageIcon img = new ImageIcon(this.jigsawImage[this.jigsawIndex] + arr[i] + ".jpg");
            //JLabel容器里加入图片
            JLabel jLabel = new JLabel(img);
            //加入到页面里，利用坐标系获取位置
            jLabel.setBounds(105*((i)%4) + 83, 105*((i)/4) + 134 , 105, 105);
            jLabel.setBorder(new BevelBorder(1));//斜边框。0是图片会凸起来，1是图片会凹进去
            this.getContentPane().add(jLabel);
        }

        //背景图片
        ImageIcon background = new ImageIcon("素材\\素材\\image\\background.png");//相对路径,从项目名开始。
        JLabel backJlabel = new JLabel(background);
        backJlabel.setBounds(40,40,508,560);
        this.getContentPane().add(backJlabel);//先加载的图片在上面，后加载的图片在下面
        
        this.getContentPane().repaint();//刷新页面
    }
    public static int[] randomizeArr(){
        int[] arr = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random r = new Random();
        //打乱数组
        for (int i = 0; i < arr.length; i++) {
            int j = r.nextInt(0,16);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }
    public static int get0Index(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0){
                return i;
            }
        }return -1;//nobody gets here
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //bruh
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //按下长按没这么玩的，所以在released之后才执行变换位置代码
        if (e.getKeyCode() == 'A'){//效果预览
            this.tempArr = this.arr;
            this.arr = ARR;
            initImage();
            this.arr = this.tempArr;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.successActivated){
            return;
            //因为已经成功了，这时不能再有别的什么操作
        }
        if (e.getKeyCode() == 37){//左
            System.out.println("Move Left");
            if (this.pos != 3 && this.pos != 7 && this.pos != 11 && this.pos != 15){
                this.arr[this.pos] = this.arr[this.pos + 1];
                this.arr[this.pos + 1] = 0;
                this.pos = this.pos + 1;
                this.moveCount++;
                if (determineSuccess() && successActivated == false){
                    realVictory = true;
                    successActivated = true;
                    System.out.println(this.moveCount);
                }
                initImage();
                realVictory = false;
            }
        
        }else if (e.getKeyCode() == 38){//上
            System.out.println("Move Up");
            if (this.pos != 12 && this.pos != 13 && this.pos != 14 && this.pos != 15){
                this.arr[this.pos] = this.arr[this.pos + 4];
                this.arr[this.pos + 4] = 0;
                this.pos = this.pos + 4;
                this.moveCount++;
                if (determineSuccess() && successActivated == false){
                    realVictory = true;
                    successActivated = true;
                    System.out.println(this.moveCount);
                }
                initImage();
                realVictory = false;
            }
        }else if (e.getKeyCode() == 39){//右
            System.out.println("Move right");
            if (this.pos != 0 && this.pos != 4 && this.pos != 8 && this.pos != 12){
                this.arr[this.pos] = this.arr[this.pos - 1];
                this.arr[this.pos - 1] = 0;
                this.pos = this.pos - 1;
                this.moveCount++;
                if (determineSuccess() && successActivated == false){
                    realVictory = true;
                    successActivated = true;
                    System.out.println(this.moveCount);
                }
                initImage();
                realVictory = false;
            }
        }else if (e.getKeyCode() == 40){//下
            System.out.println("Move down");
            if (this.pos != 0 && this.pos != 1 && this.pos != 2 && this.pos != 3){
                this.arr[this.pos] = this.arr[this.pos - 4];
                this.arr[this.pos - 4] = 0;
                this.pos = this.pos - 4;
                this.moveCount++;
                if (determineSuccess() && successActivated == false){
                    realVictory = true;
                    successActivated = true;
                    System.out.println(this.moveCount);
                }
                initImage();
                realVictory = false;
            }
        }else if (e.getKeyCode() == 'Z'){//一键通关
            realVictory = true;
            successActivated = true;
            System.out.println("Clearance");
            this.arr = ARR;
            this.pos = 15;
            initImage();
            realVictory = false;
        }else if (e.getKeyCode() == 'A'){//效果预览松开
            System.out.println("Preview");
            this.arr = this.tempArr;
            initImage();
        }
    }
    
    public void success(){
        ImageIcon successIcon = new ImageIcon("素材\\素材\\image\\win.png");
        JLabel successFrame = new JLabel(successIcon);
        successFrame.setBounds(150,300,197,73);
        this.getContentPane().add(successFrame);
    }

    public boolean determineSuccess(){
        for (int i = 0; i < this.arr.length; i++) {
            if (this.arr[i] == this.ARR[i]){
                if (i == 15)
                    return true;
            }else
                return false;
        }return false;
    }
}
    
