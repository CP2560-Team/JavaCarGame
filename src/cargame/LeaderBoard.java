package cargame;

import java.util.Properties;
import java.util.ArrayList;
import kava.util.Collections;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * a class to handle loading and saving high score data
 */
public class LeaderBoard {
    private Properties properties = new Properties();
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private ArrayList<Integer> scores = new ArrayList<Integer>();

    /**
     * a method to load high scores from file and populate the highscore list
     */
    public void loadData(){
        try{
            fileInputStream = new FileInputStream("save.dat");
            properties.load(fileInputStream);
            fileInputStream.close();
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
        //load High scores into scores list
        scores.add(properties.getProperty("HighScore1"));
        scores.add(properties.getProperty("HighScore2"));
        scores.add(properties.getProperty("HighScore3"));
        scores.add(properties.getProperty("HighScore4"));
        scores.add(properties.getProperty("HighScore5"));
    }

    /**
     * a method to save high scores to file
     */
    public void saveData(){
        try{
            fileOutputStream = new FileOutputStream("save.dat");
            properties.store(fileOutputStream, "Game Data");
            fileOutputStream.close();
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    /**
     * a method to update the high score list
     */
    public void setValues(){
        properties.setProperty("HighScore1", scores.get(0));
        properties.setProperty("HighScore2", scores.get(1));
        properties.setProperty("HighScore3", scores.get(2));
        properties.setProperty("HighScore4", scores.get(3));
        properties.setProperty("HighScore5", scores.get(4));
    }

    /**
     * a method to check if a player's score should be added to the high score list
     * @param newScore, the player's score for the current game
     * @return a boolean value
     */
    public boolean checkForNewHighScore(int newScore){
        scores.add(newScore);
        Collections.sort(scores);
        scores.remove(5);
        if(scores.contains(newScore)){
            return true;
        }else{
            return false;
        }
    }
}