package tw.gol;

import tw.gol.GUI.GameOfLifePanel;
import tw.gol.GUI.Imp.ControlPanel;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.File;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        final GameOfLifePanel golPanel = new GameOfLifePanel(200, 200);
        golPanel.randInitial();


        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);


        final JMenu menuStart = new JMenu("开始");
        menu.add(menuStart);
        final JMenu menuRandom = new JMenu("随机生命");
        menu.add(menuRandom);
        final JMenu menuStop = new JMenu("停止");
        menu.add(menuStop);
        final JMenu menuLoad = new JMenu("加载文件");
        menu.add(menuLoad);

        menuStart.setEnabled(true);
        menuStop.setEnabled(false);
        menuRandom.setEnabled(true);
        menuLoad.setEnabled(true);

        menuLoad.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                JFileChooser jfc=new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
                jfc.showDialog(new JLabel(), "选择");
                File file=jfc.getSelectedFile();
                if(file.isDirectory()){
                    System.out.println("文件夹:"+file.getAbsolutePath());
                }else if(file.isFile()){
                    System.out.println("文件:"+file.getAbsolutePath());
                }
                System.out.println(jfc.getSelectedFile().getName());
                golPanel.loadFromFile(jfc.getSelectedFile().toString());
            }

            public void menuDeselected(MenuEvent e) {
            }

            public void menuCanceled(MenuEvent e) {
            }
        });
        menuStart.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {

                System.out.println("-----start-----------------");
                golPanel.startGame();
                menuStart.setEnabled(false);
                menuRandom.setEnabled(false);
                menuLoad.setEnabled(false);
                menuStop.setEnabled(true);
            }

            public void menuDeselected(MenuEvent e) {
            }

            public void menuCanceled(MenuEvent e) {
            }
        });


        menuStop.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {

                golPanel.stopGame();
                menuStart.setEnabled(true);
                menuStop.setEnabled(false);
                menuRandom.setEnabled(true);
                menuLoad.setEnabled(true);
            }

            public void menuDeselected(MenuEvent e) {
            }

            public void menuCanceled(MenuEvent e) {
            }
        });


        menuRandom.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {

                golPanel.randInitial();
            }

            public void menuDeselected(MenuEvent e) {
            }

            public void menuCanceled(MenuEvent e) {
            }
        });

//        golPanel.setBounds(10, 10, golPanel.getWidth(), golPanel.getHeight());
        frame.add(golPanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("width:" + golPanel.getWidth() + "  height:" + golPanel.getHeight());
        frame.setSize(golPanel.getWidth(), golPanel.getHeight() + 40);
        frame.setTitle("Game of Life");

        frame.setVisible(true);

    }

    private static ControlPanel initControlPanel(final GameOfLifePanel golPanel) {
        ControlPanel controlPanel = new ControlPanel();
//        controlPanel.setBackground(new Color(0,0,0));
        controlPanel.setSize(300, 300);

        final JButton btStart = new JButton("start");
        final JButton btRandom = new JButton("randInitial");
        final JButton btStop = new JButton("stopGame");

//        controlPanel.setLayout(new BorderLayout());

        //设置初始状态
        btStart.setEnabled(true);
        btStop.setEnabled(false);
        btRandom.setEnabled(true);

        btStart.setSize(20, 30);
        controlPanel.add("start", btStart);
//        controlPanel.add(btStart,BorderLayout.EAST);
        controlPanel.setSize(300, 300);

        btStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                golPanel.startGame();
                btStart.setEnabled(false);
                btRandom.setEnabled(false);
                btStop.setEnabled(true);
            }
        });


        btRandom.setSize(20, 30);
        controlPanel.add("randInitial", btRandom);
        btRandom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                golPanel.randInitial();

            }
        });


        btStop.setSize(20, 30);
        controlPanel.add("stopGame", btStop);
        controlPanel.setSize(300, 300);

        btStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                golPanel.stopGame();
                btStart.setEnabled(true);
                btStop.setEnabled(false);
                btRandom.setEnabled(true);
            }
        });
        return controlPanel;
    }

    public void initGui() {

    }
}
