package View;

import Controller.Controller;
import View.Building.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame{
    JFrame frame = new JFrame("Anthill");
    Controller controller;

    Anthill anthill;
    BuildingTownHall townHall;
    BuildingFoodStorage foodStorage;
    BuildingQueensRoom queensRoom;
    BuildingCowshed cowshed;
    BuildingChurch church;
    BuildingScienceCenter scienceCenter;

    JPanel left = new JPanel();
    JPanel center = new JPanel();
    JPanel right = new JPanel();
    JPanel info = new JPanel();

    JLabel numberAnts = new JLabel();
    JLabel numberFoods = new JLabel();

    public MainFrame(){
        controller = new Controller(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        createStructure();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void createStructure(){
        anthill = new Anthill(100);
        townHall = new BuildingTownHall(controller);
        foodStorage = new BuildingFoodStorage(controller);
        queensRoom = new BuildingQueensRoom(controller);
        cowshed = new BuildingCowshed(controller);
        church = new BuildingChurch(controller);
        scienceCenter = new BuildingScienceCenter(controller);

        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setPreferredSize(new Dimension(200, 600));
        left.add(townHall);
        left.add(foodStorage);
        left.add(queensRoom);

        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setPreferredSize(new Dimension(200, 600));
        right.add(cowshed);
        right.add(church);
        right.add(scienceCenter);

        center.setLayout(new BorderLayout());
        center.setPreferredSize(new Dimension(400, 600));

        numberAnts.setHorizontalAlignment(JLabel.CENTER);
        numberAnts.setText("M: "+controller.getNumAnts()+"/"+controller.getMaxAnts());
        numberFoods.setHorizontalAlignment(JLabel.CENTER);
        numberFoods.setText("E: "+controller.getFoods()+"/"+controller.getMaxFoods());

        info.add(numberFoods);
        info.add(numberAnts);
        center.add(info, BorderLayout.NORTH);

        center.add(anthill);

        frame.add(left);
        frame.add(center);
        frame.add(right);
    }

    public void update(){
        numberAnts.setText("M: "+controller.getNumAnts()+"/"+controller.getMaxAnts());
        numberFoods.setText("E: "+controller.getFoods()+"/"+controller.getMaxFoods());
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }

    public void addAnt(){
        anthill.addNewAnt();
    }

    public void needAway(int numAnts){
        anthill.needAway(numAnts);
    }

    public void warningStarvation(int starvation){
        int roundToDead = 6 - starvation;
        JOptionPane.showMessageDialog(null, "Меравьи голодают! Смерть через " + roundToDead + " тика(ов)");
    }

    public void gameOver(){
        JOptionPane.showMessageDialog(null, "Вы не смогли прокормить своих муравьев! Игра закончена!");
        System.exit(666);
    }
}