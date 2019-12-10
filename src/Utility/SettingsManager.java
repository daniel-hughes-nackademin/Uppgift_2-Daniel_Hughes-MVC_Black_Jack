package Utility;

import java.io.*;
import java.util.Properties;

public class SettingsManager {

    public static int readNumberOfDecksFromProperties(){
        Properties properties = getProperties();
        int numberOfDecks = Integer.parseInt(properties.getProperty("numberOfDecks"));
        if(numberOfDecks < 2 || numberOfDecks > 8)
            throw new IllegalArgumentException("Number of decks must be 1-8!");
        return numberOfDecks;
    }



    public static int readNumberOfRoundsFromProperties() {
        Properties properties = getProperties();
        int numberOfRounds = Integer.parseInt(properties.getProperty("numberOfRounds"));
        if(numberOfRounds < 1 || numberOfRounds > 8)
            throw new IllegalArgumentException("Number of rounds must be 1-8!");
        return numberOfRounds;
    }

    public static int readBlackJackScoreFromProperties() {
        Properties properties = getProperties();
        int blackJackScore = Integer.parseInt(properties.getProperty("blackJackScore"));
        if(blackJackScore < 1)
            throw new IllegalArgumentException("Black Jack Score must be at least 1, but standard is 21.");
        return blackJackScore;
    }

    public static void writeNumberOfDecksToProperties(int numberOfDecks){
        if(numberOfDecks < 2 || numberOfDecks > 8)
            throw new IllegalArgumentException("Invalid number of Decks!");

        try {
            InputStream input = new FileInputStream("src/Utility/settings.properties");
            Properties properties = getProperties();
            properties.load(input);
            input.close();

            OutputStream output = new FileOutputStream("src/Utility/settings.properties");
            properties.setProperty("numberOfDecks", String.valueOf(numberOfDecks));
            properties.store(output, null);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeNumberOfRoundsToProperties(int numberOfRounds){
        if(numberOfRounds < 1 || numberOfRounds > 8)
            throw new IllegalArgumentException("Invalid number of Rounds!");

        try {
            InputStream input = new FileInputStream("src/Utility/settings.properties");
            Properties properties = getProperties();
            properties.load(input);
            input.close();

            OutputStream output = new FileOutputStream("src/Utility/settings.properties");
            properties.setProperty("numberOfRounds", String.valueOf(numberOfRounds));
            properties.store(output, null);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/Utility/settings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
