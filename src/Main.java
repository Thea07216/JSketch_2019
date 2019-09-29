import javafx.scene.shape.Circle;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.Graphics;
import  java.awt.geom.Ellipse2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
//import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Scanner;
//import javafx.scene.shape.Line;
import java.io.ObjectInputStream;
import javax.swing.Action;
import java.beans.EventHandler;
import java.awt.Dialog;
import javax.swing.JFileChooser;

public class Main {
    public static Color cur_color = Color.PINK;
    public static int shape_num = 0;

    public static int thickness = 5;
    public static int cur_shape_index = 0;
    public static int cur_shape = 0;

    public static ArrayList<Point> pointStart = new ArrayList<Point>();
    public static ArrayList<Point> pointEnd = new ArrayList<Point>();

    public static Point startSinglePoint = new Point();
    public static Point endSinglePoint = new Point();
    public static ArrayList<Integer> shapelist = new ArrayList<Integer>();
    public static ArrayList<Color> colorlist = new ArrayList<Color>();
    public static ArrayList<Integer> thinknesslist = new ArrayList<Integer>();
    public static ArrayList<Color> filllist = new ArrayList<Color>();
    public static ArrayList<Integer> fillbool = new ArrayList<Integer>();
    public static ArrayList<Integer> groupindex = new ArrayList<Integer>();
    public static Point startGroupPoint = new Point();
    public static Point endGroupPoint = new Point();
   // public static File dataFile = new File("file1.txt");
   // public static String FILE_NAME = "file1.txt";
    public static int selected = 0;
    public static Panel1 p1;
    public static Panel2 p2;
    public static Panel3 p4;
    public static JButton Group;
    public static JButton Grouped;
    public static int select_change_index;
    public static Canvasj c;

    public static void reset() {
        groupindex.clear();
        Group.setBorder(BorderFactory.createEmptyBorder());
        Grouped.setBorder(BorderFactory.createEmptyBorder());
    }

    public static void select_reset(Panel1 p1, int shape, Panel2 p2, Color c,Panel3 p3, int thickness){
        p1.setEmptyBorder();
        p2.setEmptyBorder();
        p3.setEmptyBorder();
        if(shape == 3){
            p1.setBorderp3();
        } else if(shape == 4){
            p1.setBorderp4();
        } else if (shape == 5){
            p1.setBorderp5();
        }
        if (c == Color.BLUE){
            p2.setBorderp1();
        } else if (c == Color.RED){
            p2.setBorderp2();
        } else if (c == Color.ORANGE){
            p2.setBorderp3();
        } else if (c == Color.YELLOW){
            p2.setBorderp4();
        } else if (c == Color.GREEN){
            p2.setBorderp5();
        } else if (c == Color.PINK){
            p2.setBorderp6();
        }
        if (thickness == 5){
            p3.setBorderp1();
        } else if (thickness == 8){
            p3.setBorderp2();
        } else if (thickness == 10){
            p3.setBorderp3();
        }
    }

   /* public static class myArraylist implements Serializable {
        ArrayList<Object> a;


        myArraylist(ArrayList<Object> a) {
           a = a;
        }

        public myArraylist getmyArraylist() {
            return this;
        }
    } */


    public static void saveData() {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream("output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (fos != null) {
                ObjectOutputStream oos = null;
                try {

                    oos = new ObjectOutputStream(fos);

                    if (oos != null) {
                      //  myArraylist arrayList = new myArraylist(pointStart, pointEnd, shapelist,
                      //          colorlist, thinknesslist, filllist, fillbool, groupindex);
                      ArrayList<Object> arrayLists = new ArrayList<Object>();
                        arrayLists.add(pointStart);
                        arrayLists.add(pointEnd);
                        arrayLists.add(shapelist);
                        arrayLists.add(colorlist);
                        arrayLists.add(thinknesslist);
                        arrayLists.add(filllist);
                        arrayLists.add(fillbool);

                      //oos.add((pointStart);

                        oos.writeObject(arrayLists);
                    }
                    assert oos != null;
                    oos.close();
                    c.repaintCanvas();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loadData() {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream("output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            if (fis != null) {
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(fis);

                    if (ois != null) {

                        try {
                            ArrayList<Object> arrayList1 =  (ArrayList<Object>)  ois.readObject();
                            pointStart = (ArrayList<Point>) arrayList1.get(0);
                            pointEnd =  (ArrayList<Point>) arrayList1.get(1);
                            shapelist =(ArrayList<Integer>) arrayList1.get(2);
                            colorlist =  (ArrayList<Color>) arrayList1.get(3);
                            thinknesslist = (ArrayList<Integer>) arrayList1.get(4);
                            filllist = (ArrayList<Color>) arrayList1.get(5);
                            fillbool = (ArrayList<Integer>) arrayList1.get(6);
                           // System.out.println(fillbool);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {

                    }
                    assert ois != null;
                    ois.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }





    public static void main(String[] args) {
        JFrame f = new JFrame("CS349A1");
        f.setSize(600, 600);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c = new Canvasj();
        c.setLocation(100, 30);
        // c.setBorder();hjh
        c.setBorder(BorderFactory.createLineBorder(Color.black));
        c.setBounds(100, 30, 499, 569);
        f.add(c);
        JMenu menu = new JMenu("File");





        for (String s : new String[]{"New", "Load", "Save"}) {
            JMenuItem mi = new JMenuItem(s);
            if (s == "New") {
                mi.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pointEnd.clear();
                        pointStart.clear();
                        shapelist.clear();
                        pointEnd.clear();
                        colorlist.clear();
                        thinknesslist.clear();
                        filllist.clear();
                        fillbool.clear();
                        c.repaintCanvas();

                        //JOptionPane.showMessageDialog(null, "New");
                    }
                });
            }
            else if (s == "Load") {
                mi.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //  JOptionPane.showMessageDialog(null, "Load");
                       // System.out.println("999999");
                        /*JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setCurrentDirectory(new java.io.File("."));
                        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        if (fileChooser.showOpenDialog(f) == JFileChooser.APPROVE_OPTION) {
                            File file = fileChooser.getSelectedFile();
                            // load from file */

                            loadData();
                            //  System.out.println("!!!!!!!!!");
                            // System.out.println(fillbool.get(0));
                            c.repaintCanvas();
                        }
                });
            } else {
                mi.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            //save("out.txt");
                            /*JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setCurrentDirectory(new java.io.File("."));
                           // fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                            fileChooser.setFileSelectionMode(JFileChooser.CUSTOM_DIALOG);
                            fileChooser.showDialog(null,"Please Select the File");
                            fileChooser.setVisible(true);
                            File filename = fileChooser.getSelectedFile();
                            System.out.println("File name "+filename.getName());
                                System.out.println(filename); */
                                saveData();
                            } catch (Exception j){

                        }
                        //Read more: http://mrbool.com/how-to-store-data-with-java/25483#ixzz5qom6R3g6
                    }
                });
            }
            menu.add(mi);
        }
        JMenuBar menubar = new JMenuBar();
        menubar.add(menu);
        menubar.setBounds(0, 0, 100, 30);
        f.add(menubar);

        p1 = new Panel1();
        f.add(p1);
        p1.setBounds(0, 30, 99, 120);
        p1.setBorder(BorderFactory.createLineBorder(Color.black));


        Group = new JButton("GroupS");
        Group.setSize(50, 35);

        Group.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape_num = 7;
                Group.setBorder(BorderFactory.createLineBorder(Color.black));
            }
        });

        f.add(Group);
        Group.setBounds(0, 155, 50, 20);
        //Group.setBorder(BorderFactory.createLineBorder(Color.black));
        Grouped = new JButton("GroupC");
        Grouped.setSize(45, 35);

        Grouped.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape_num = 8;
                Grouped.setBorder(BorderFactory.createLineBorder(Color.black));
            }
        });



        f.add(Grouped);
        Grouped.setBounds(52, 155, 50, 20);
        //Grouped.setBorder(BorderFactory.createLineBorder(Color.black));



        /*f.addKeyListener(new KeyAdapter() {
                                @Override
                                public void keyPressed(KeyEvent e) {
                                    if ((int) e.getKeyChar() == 27) {
                                        if (shape_num == 1) {
                                            shape_num = 0;
                                            System.out.println(99999999);
                                            c.repaintCanvas();
                                        }
                                    }
                                }
                            }
        );*/





        p2 = new Panel2();
        p2.setBounds(0, 185, 99, 150);
        p2.setBorder(BorderFactory.createLineBorder(Color.black));
        f.add(p2);

        JButton button = new JButton("Chooser");
        button.setSize(50, 25);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cur_color = JColorChooser.showDialog(null, "Choose a color", Color.blue);
               // System.out.println("The selected color was:" + cur_color);
                button.setBorder(BorderFactory.createLineBorder(Color.black));
            }
        });

        f.add(button);
        button.setBounds(0, 340, 99, 25);


        //button.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton dr = new JButton("drag");
        dr.setSize(50, 25);

        dr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape_num = 10;
            }
        });

        f.add(dr);
        dr.setBounds(0, 367, 99, 25);
        dr.setBorder(BorderFactory.createLineBorder(Color.black));


        p4 = new Panel3();
        f.add(p4);
        p4.setBounds(0, 400, 100, 150);
        p4.setBorder(BorderFactory.createLineBorder(Color.black));

        f.setVisible(true);
    }


    // define the layout
    // http://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html

    public static class Panel3 extends JPanel {
        JPanel pp1;
        JPanel pp2;
        JPanel pp3;

        public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
            Image img = icon.getImage();
            Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }
        Panel3() {
            pp1 = new JPanel(new BorderLayout());
            pp2 = new JPanel(new BorderLayout());
            pp3 = new JPanel(new BorderLayout());


            // pp1.setBackground(Color.WHITE);

            JButton b1 = new JButton();

            ImageIcon water = new ImageIcon("line1.PNG");
            b1.setIcon(resizeIcon(water, 99, 50));

            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    thickness = 5;
                    setEmptyBorder();
                    pp1.setBorder(BorderFactory.createLineBorder(Color.black));
                    if (shape_num == 1){
                        thinknesslist.set(select_change_index,thickness);
                        c.repaintCanvas();
                    }
                }
            });
            //b1.setOpaque(false);
            b1.setContentAreaFilled(false);
            b1.setBorderPainted(false);
            pp1.add(b1);
            //pp2.setBackground(Color.WHITE);
            JButton b2 = new JButton();
            water = new ImageIcon("line2.PNG");
            b2.setIcon(resizeIcon(water, 99, 50));
            b2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    thickness = 8;
                    setEmptyBorder();
                    pp2.setBorder(BorderFactory.createLineBorder(Color.black));
                    if (shape_num == 1){
                        thinknesslist.set(select_change_index,thickness);
                        c.repaintCanvas();
                    }
                }
            });
            //pp1.setBounds(0, 400, 99, 50);
            // b2.setOpaque(false);
            b2.setContentAreaFilled(false);
            b2.setBorderPainted(false);
            pp2.add(b2);
            //pp3.setBackground(Color.WHITE);
            JButton b3 = new JButton();
            water = new ImageIcon("line3.PNG");
            b3.setIcon(resizeIcon(water, 99, 50));
            b3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    thickness = 10;
                    setEmptyBorder();
                    pp3.setBorder(BorderFactory.createLineBorder(Color.black));
                    if (shape_num == 1){
                        thinknesslist.set(select_change_index,thickness);
                        c.repaintCanvas();
                    }
                }
            });
            //b3.setOpaque(false);
            b3.setContentAreaFilled(false);
            b3.setBorderPainted(false);
            pp3.add(b3);
            this.setLayout(new GridLayout(3, 1));
            this.add(pp1);
            this.add(pp2);
            this.add(pp3);


        }

        public void setEmptyBorder() {
            pp1.setBorder(BorderFactory.createEmptyBorder());
            pp2.setBorder(BorderFactory.createEmptyBorder());
            pp3.setBorder(BorderFactory.createEmptyBorder());
        }
        public void setBorderp1() {
            pp1.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void setBorderp2() {
            pp2.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void setBorderp3() {
            pp3.setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }

    public static class Panel1 extends JPanel {
        JPanel pp1;
        JPanel pp2;
        JPanel pp3;
        JPanel pp4;
        JPanel pp5;
        JPanel pp6;

        public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
            Image img = icon.getImage();
            Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        }

        Panel1() {
            pp1 = new JPanel(new BorderLayout());
            pp2 = new JPanel(new BorderLayout());
            pp3 = new JPanel(new BorderLayout());
            pp4 = new JPanel(new BorderLayout());
            pp5 = new JPanel(new BorderLayout());
            pp6 = new JPanel(new BorderLayout());

            pp1.setBackground(Color.WHITE);
            ImageIcon water = new ImageIcon("arrow.PNG");
            JButton b1 = new JButton();
            b1.setIcon(resizeIcon(water, 50, 40));
            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shape_num = 1;
                    setEmptyBorder();
                    pp1.setBorder(BorderFactory.createLineBorder(Color.black));
                    reset();
                }
            });
            b1.setOpaque(false);
            b1.setContentAreaFilled(false);
            b1.setBorderPainted(false);
            //b1.setIcon(new ImageIcon("black.PNG"));

            //JButton button = new JButton(water);
            pp1.add(b1);
            pp2.setBackground(Color.WHITE);
            water = new ImageIcon("erase.PNG");
            JButton b2 = new JButton();
            b2.setIcon(resizeIcon(water, 50, 40));
            b2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shape_num = 2;
                    setEmptyBorder();
                    pp2.setBorder(BorderFactory.createLineBorder(Color.black));
                    reset();
                }
            });
            // b2.setOpaque(false);
            b2.setContentAreaFilled(false);
            b2.setBorderPainted(false);
            pp2.add(b2);
            // pp3.setBackground(Color.WHITE);
            //JButton b3 = new JButton();
            water = new ImageIcon("line.PNG");
            JButton b3 = new JButton();
            b3.setIcon(resizeIcon(water, 50, 40));
            b3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shape_num = 3;
                    setEmptyBorder();
                    pp3.setBorder(BorderFactory.createLineBorder(Color.black));
                    reset();
                }
            });
            //b3.setOpaque(false);
            b3.setContentAreaFilled(false);
            b3.setBorderPainted(false);
            pp3.add(b3);
            // pp4.setBackground(Color.WHITE);

            // JButton b4 = new JButton();
            water = new ImageIcon("circle.PNG");
            JButton b4 = new JButton();
            b4.setIcon(resizeIcon(water, 50, 40));
            b4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shape_num = 4;
                    setEmptyBorder();
                    pp4.setBorder(BorderFactory.createLineBorder(Color.black));
                    reset();
                }
            });
            //b4.setOpaque(false);
            b4.setContentAreaFilled(false);
            b4.setBorderPainted(false);
            pp4.add(b4);
            // pp5.setBackground(Color.WHITE);
            // JButton b5 = new JButton();
            water = new ImageIcon("rect.PNG");
            JButton b5 = new JButton();
            b5.setIcon(resizeIcon(water, 50, 40));
            b5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shape_num = 5;
                    setEmptyBorder();
                    pp5.setBorder(BorderFactory.createLineBorder(Color.black));
                    reset();
                }
            });
            // b5.setOpaque(false);
            b5.setContentAreaFilled(false);
            b5.setBorderPainted(false);
            pp5.add(b5);
            // pp6.setBackground(Color.WHITE);
            //JButton b6 = new JButton("");
            water = new ImageIcon("fill.PNG");
            JButton b6 = new JButton();
            b6.setIcon(resizeIcon(water, 50, 40));
            b6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shape_num = 6;
                    setEmptyBorder();
                    pp6.setBorder(BorderFactory.createLineBorder(Color.black));
                    reset();
                }
            });
            // b6.setOpaque(false);
            b6.setContentAreaFilled(false);
            b6.setBorderPainted(false);
            pp6.add(b6);
            this.setLayout(new GridLayout(3, 2));
            this.add(pp1);
            this.add(pp2);
            this.add(pp3);
            this.add(pp4);
            this.add(pp5);
            this.add(pp6);

        }

        public void setEmptyBorder() {
            pp1.setBorder(BorderFactory.createEmptyBorder());
            pp2.setBorder(BorderFactory.createEmptyBorder());
            pp3.setBorder(BorderFactory.createEmptyBorder());
            pp4.setBorder(BorderFactory.createEmptyBorder());
            pp5.setBorder(BorderFactory.createEmptyBorder());
            pp6.setBorder(BorderFactory.createEmptyBorder());
        }
        public void setBorderp1() {
            pp1.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void setBorderp2() {
            pp2.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void setBorderp3() {
            pp3.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void setBorderp4() {
            pp4.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void setBorderp5() {
            pp5.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void setBorderp6() {
            pp6.setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }


    public static class Panel2 extends JPanel {
        JPanel pp1;
        JPanel pp2;
        JPanel pp3;
        JPanel pp4;
        JPanel pp5;
        JPanel pp6;

        Panel2() {
            pp1 = new JPanel();
            pp2 = new JPanel();
            pp3 = new JPanel();
            pp4 = new JPanel();
            pp5 = new JPanel();
            pp6 = new JPanel();

            pp1.setBackground(Color.BLUE);
            JButton b1 = new JButton();
            b1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cur_color = Color.BLUE;
                    setEmptyBorder();
                    pp1.setBorder(BorderFactory.createLineBorder(Color.black));
                    if (shape_num == 1){
                        colorlist.set(select_change_index,cur_color);
                        c.repaintCanvas();
                    }
                }
            });
            b1.setOpaque(false);
            b1.setContentAreaFilled(false);
            b1.setBorderPainted(false);
            pp1.add(b1);
            pp2.setBackground(Color.RED);
            JButton b2 = new JButton();
            b2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cur_color = Color.RED;
                    setEmptyBorder();
                    pp2.setBorder(BorderFactory.createLineBorder(Color.black));
                    if (shape_num == 1){
                        colorlist.set(select_change_index,cur_color);
                        c.repaintCanvas();
                    }
                }
            });
            b2.setOpaque(false);
            b2.setContentAreaFilled(false);
            b2.setBorderPainted(false);
            pp2.add(b2);
            pp3.setBackground(Color.ORANGE);
            JButton b3 = new JButton();
            b3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cur_color = Color.ORANGE;
                    setEmptyBorder();
                    pp3.setBorder(BorderFactory.createLineBorder(Color.black));
                    if (shape_num == 1){
                        colorlist.set(select_change_index,cur_color);
                        c.repaintCanvas();
                    }
                }
            });
            b3.setOpaque(false);
            b3.setContentAreaFilled(false);
            b3.setBorderPainted(false);
            pp3.add(b3);
            pp4.setBackground(Color.YELLOW);

            JButton b4 = new JButton();
            b4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cur_color = Color.YELLOW;
                    setEmptyBorder();
                    pp4.setBorder(BorderFactory.createLineBorder(Color.black));
                    if (shape_num == 1){
                        colorlist.set(select_change_index,cur_color);
                        c.repaintCanvas();
                    }
                }
            });
            b4.setOpaque(false);
            b4.setContentAreaFilled(false);
            b4.setBorderPainted(false);
            pp4.add(b4);
            pp5.setBackground(Color.GREEN);
            JButton b5 = new JButton();
            b5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cur_color = Color.GREEN;
                    setEmptyBorder();
                    pp5.setBorder(BorderFactory.createLineBorder(Color.black));
                    if (shape_num == 1){
                        colorlist.set(select_change_index,cur_color);
                        c.repaintCanvas();
                    }
                }
            });
            b5.setOpaque(false);
            b5.setContentAreaFilled(false);
            b5.setBorderPainted(false);
            pp5.add(b5);
            pp6.setBackground(Color.PINK);
            JButton b6 = new JButton();
            b6.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cur_color = Color.PINK;
                    setEmptyBorder();
                    pp6.setBorder(BorderFactory.createLineBorder(Color.black));
                    if (shape_num == 1){
                        colorlist.set(select_change_index,cur_color);
                        c.repaintCanvas();
                    }
                }
            });
            b6.setOpaque(false);
            b6.setContentAreaFilled(false);
            b6.setBorderPainted(false);
            pp6.add(b6);
            this.setLayout(new GridLayout(3, 2));
            this.add(pp1);
            this.add(pp2);
            this.add(pp3);
            this.add(pp4);
            this.add(pp5);
            this.add(pp6);

        }

        public void setEmptyBorder() {
            pp1.setBorder(BorderFactory.createEmptyBorder());
            pp2.setBorder(BorderFactory.createEmptyBorder());
            pp3.setBorder(BorderFactory.createEmptyBorder());
            pp4.setBorder(BorderFactory.createEmptyBorder());
            pp5.setBorder(BorderFactory.createEmptyBorder());
            pp6.setBorder(BorderFactory.createEmptyBorder());
        }
        public void setBorderp1() {
            pp1.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void setBorderp2() {
            pp2.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void setBorderp3() {
            pp3.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void setBorderp4() {
            pp4.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void setBorderp5() {
            pp5.setBorder(BorderFactory.createLineBorder(Color.black));
        }
        public void setBorderp6() {
            pp6.setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }



    public static class Canvasj extends JComponent {



        public int find_shape(Point p) {
            for (int i =  pointStart.size() -1 ; i != 0; i--) {
                if (shapelist.get(i) == 3) {
                    Line2D.Double l = new Line2D.Double(pointStart.get(i).x, pointStart.get(i).y, pointEnd.get(i).x, pointEnd.get(i).y);
                    double hi = l.ptSegDist(p.getX(),p.getY());
                    if (hi <= 1) {
                        return i;
                    }
                } else if (shapelist.get(i) == 4) {
                    int radius = (int) Math.sqrt(Math.pow(Math.abs(pointStart.get(i).x - pointEnd.get(i).x), 2) + Math.pow(Math.abs(pointStart.get(i).y - pointEnd.get(i).y), 2));
                    Circle c = new Circle(pointStart.get(i).x, pointStart.get(i).y, radius);
                    if (c.contains(p.getX(), p.getY())) {
                        return i;
                    }
                } else if (shapelist.get(i) == 5) {
                    int width = pointEnd.get(i).x - pointStart.get(i).x;
                    width = Math.abs(width);
                    int height = pointEnd.get(i).y - pointStart.get(i).y;
                    height = Math.abs(height);
                    int x1 = pointStart.get(i).x;;
                    int y1 = pointStart.get(i).y;
                    int x2 = pointEnd.get(i).x;
                    int y2 = pointEnd.get(i).y;
                    int drawx;
                    int drawy;
                    if (x1 < x2){
                        drawx = x1;
                    } else {
                        drawx = x2;
                    }
                    if (y1 < y2 ){
                        drawy = y1;
                    }else {
                        drawy = y2;
                    }
                    //Rectangle r = new Rectangle(pointStart.get(i).x, pointStart.get(i).y, width, height);
                    Rectangle r = new Rectangle(drawx, drawy, width, height);
                    if (r.contains(p.getX(), p.getY())) {
                        return i;
                    }
                }
            }
            return 0;
        }



        public Canvasj() {

            setSize(getPreferredSize());


            this.addMouseListener(new MouseAdapter() {

                public void mousePressed(MouseEvent e) {
                    startSinglePoint = e.getPoint(); // used to the draw line when

                    if (shape_num == 1) {
                        Point p = e.getPoint();
                        cur_shape_index = find_shape(p);
                        cur_color = colorlist.get(cur_shape_index);
                        thickness = thinknesslist.get(cur_shape_index);
                        cur_shape = shapelist.get(cur_shape_index);
                        select_change_index = cur_shape_index;
                        select_reset(p1,cur_shape, p2, cur_color,p4,thickness);
                    }  else if (shape_num == 6 || shape_num == 2){
                        Point p = e.getPoint();
                        cur_shape_index = find_shape(p);
                    } else if (shape_num == 7) {
                        Point p = e.getPoint();
                        groupindex.add(find_shape(p));
                    } else if (shape_num == 8  ) {
                        startGroupPoint = e.getPoint();
                        groupFunction();
                        //  draggfunction();
                    } else if (shape_num == 10) {
                        startGroupPoint = e.getPoint();
                        dragFunction();
                    }else {
                        pointStart.add(e.getPoint()); // used to save all drew lines
                    }
                    repaint();
                }// end mousePressed

                public void mouseReleased(MouseEvent e) {

                    if (shape_num == 2){
                        pointEnd.remove(cur_shape_index);
                        pointStart.remove(cur_shape_index);
                        shapelist.remove(cur_shape_index);
                        colorlist.remove(cur_shape_index);
                        filllist.remove(cur_shape_index);
                        fillbool.remove(cur_shape_index);
                        thinknesslist.remove(cur_shape_index);

                    } else if  (shape_num == 8) {
                        endGroupPoint = e.getPoint();
                        groupFunction();
                    } else if (shape_num ==10) {
                        endGroupPoint = e.getPoint();
                        dragFunction();
                    }else {
                        pointEnd.add(e.getPoint()); // used to save all drew lines
                        shapelist.add(shape_num);
                        colorlist.add(cur_color);
                        filllist.add(null);
                        fillbool.add(0);
                        thinknesslist.add(thickness);
                    }
                    repaint();
                }
            });



            this.addMouseMotionListener(new MouseAdapter() {

                public void mouseDragged(MouseEvent e) {
                    endSinglePoint = e.getPoint(); // used to draw the line when you
                    // drag
                    repaint();
                }// end mouseDragged
            });

            /*this.addKeyListener(new KeyAdapter() {
                               //  @Override
                                 public void keyPressed(KeyEvent e) {
                                     if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                                         if (shape_num == 1) {
                                             shape_num = 0;
                                            // System.out.println(99999999);
                                             c.repaintCanvas();
                                         }
                                     }
                                 }
                             }
            );*/

            //int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
           // InputMap imap = this.getInputMap(mapName);
            this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "EXIT");
            this.getActionMap().put("EXIT", new AbstractAction(){
                public void actionPerformed(ActionEvent e)
                {
                    if (shape_num == 1) {
                        shape_num = 0;
                        //System.out.println(99999999);
                        c.repaintCanvas();
                    }
                }
            });



        }




        public void dragFunction() {
            for (int g = 0; g < groupindex.size(); g++) {
                int curindexshape = groupindex.get(g);
                Point curp = pointStart.get(curindexshape);
                Point curpbot = pointEnd.get(curindexshape);
                int width = startGroupPoint.x - endGroupPoint.x;
                int height = startGroupPoint.y - endGroupPoint.y;
                Point newpbot;
                Point newptop;
                int getshape = shapelist.get(curindexshape);
                if (getshape == 4) {
                    newptop = new Point(curp.x +width, curp.y + height);
                    pointStart.set(curindexshape, newptop);
                } else {
                    newptop = new Point(curp.x + width, curp.y +height);
                    newpbot = new Point(curpbot.x + width, curpbot.y + height);
                    pointStart.set(curindexshape, newptop);
                    pointEnd.set(curindexshape, newpbot);
                }
            }
        }

        public void groupFunction(){
            for (int g = 0; g < groupindex.size(); g++) {
                int curindexshape = groupindex.get(g);
                Point curp = pointStart.get(curindexshape);
                Point curpbot = pointEnd.get(curindexshape);
                int width = startGroupPoint.x - endGroupPoint.x;
                int height = startGroupPoint.y - endGroupPoint.y;
                Point newpbot;
                Point newptop;
                int getshape = shapelist.get(curindexshape);
                if (getshape == 3){
                    newpbot = new Point(curpbot.x + width, curpbot.x + width);
                    pointEnd.set(curindexshape, newpbot);
                } else if (getshape == 4) {
                    //newptop = new Point(curp.x + width / 2, curp.y + height / 2);
                    newpbot = new Point(curpbot.x + width, curpbot.x + width);
                    //pointStart.set(curindexshape, newptop);
                    pointEnd.set(curindexshape, newpbot);
                } else if (getshape == 5){
                    //newptop = new Point(curp.x + width / 2, curp.y + height / 2);
                    newpbot = new Point(curpbot.x + width , curpbot.y + height);
                    // pointStart.set(curindexshape, newptop);
                    pointEnd.set(curindexshape, newpbot);
                }
            }
        }




        public void repaintCanvas(){
            repaint();
        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(500, 500);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            for (int i = 0; i < pointStart.size(); i++) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  // antialiasing look nicer
                        RenderingHints.VALUE_ANTIALIAS_ON);
                int c_thickness = thinknesslist.get(i);
                g2.setStroke(new BasicStroke(c_thickness));
                Color c_co = colorlist.get(i);
                g2.setColor(c_co);
                // defbigclass curshape = currshapelist.get(i);
                if (cur_shape_index == i && shape_num == 1) {
                    Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                    g2.setStroke(dashed);
                }
                if (cur_shape_index == i && shape_num == 6) {
                    //Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                    // g2.setStroke(dashed);
                    g2.setPaint(cur_color);
                    if (shapelist.get(i) == 4) {
                        int radius = (int) Math.sqrt(Math.pow(Math.abs(pointStart.get(i).x - pointEnd.get(i).x), 2) + Math.pow(Math.abs(pointStart.get(i).y - pointEnd.get(i).y), 2));
                        g2.fill(new Ellipse2D.Double(pointStart.get(i).x - radius, pointStart.get(i).y - radius,
                                radius * 2,
                                radius * 2));
                    } else if (shapelist.get(i) == 5) {
                        int width = pointEnd.get(i).x - pointStart.get(i).x;
                        width = Math.abs(width);
                        int height = pointEnd.get(i).y - pointStart.get(i).y;
                        height = Math.abs(height);
                        g2.fillRect(pointStart.get(i).x, pointStart.get(i).y, width, height);
                    }

                    filllist.set(i,cur_color);
                    fillbool.set(i,1);
                }
                if (shapelist.get(i) == 3) {
                    g2.draw(new Line2D.Double(pointStart.get(i).x, pointStart.get(i).y, pointEnd.get(i).x, pointEnd.get(i).y));
                } else if (shapelist.get(i) == 4) {
                    int radius = (int) Math.sqrt(Math.pow(Math.abs(pointStart.get(i).x - pointEnd.get(i).x), 2) + Math.pow(Math.abs(pointStart.get(i).y - pointEnd.get(i).y), 2));
                    g2.draw(new Ellipse2D.Double(pointStart.get(i).x - radius, pointStart.get(i).y - radius,
                            radius * 2,
                            radius * 2));
                    if (fillbool.get(i) == 1){
                        g2.setColor(filllist.get(i));
                        g2.fill(new Ellipse2D.Double(pointStart.get(i).x - radius, pointStart.get(i).y - radius,
                                radius * 2,
                                radius * 2));
                    }
                } else if (shapelist.get(i) == 5) {
                    int width = pointEnd.get(i).x - pointStart.get(i).x;
                    width = Math.abs(width);
                    int height = pointEnd.get(i).y - pointStart.get(i).y;
                    height = Math.abs(height);
                    int x1 = pointStart.get(i).x;;
                    int y1 = pointStart.get(i).y;
                    int x2 = pointEnd.get(i).x;
                    int y2 = pointEnd.get(i).y;
                    int drawx;
                    int drawy;
                    if (x1 < x2){
                        drawx = x1;
                    } else {
                        drawx = x2;
                    }
                    if (y1 < y2 ){
                        drawy = y1;
                    }else {
                        drawy = y2;
                    }

                    if (fillbool.get(i) == 1) {
                        g2.setColor(filllist.get(i));
                        //  g2.fillRect(pointStart.get(i).x, pointStart.get(i).y, width, height);
                        g2.fillRect(drawx, drawy, width, height);
                    }
                    g2.setColor(colorlist.get(i));
                    // g2.drawRect(pointStart.get(i).x, pointStart.get(i).y, width, height);
                    g2.drawRect(drawx, drawy, width, height);
                }
            }
        }
    }
}
