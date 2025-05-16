package main;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String DISCORD_BOT_TOKEN;


    /**
     * Read file in given file name as if it is a .conf or .properites file,
     * and store it in static variables for use in program
     * @param fileName  The name of the file
     */
    public static void readConfigFile(String fileName) {

        Properties prop = new Properties();
        try {
            FileInputStream fis;
            try {
                // try default0.conf config before default.conf, as defualt0 used for dev config, so I don't be uploading my api keys to Github :)
                fis = new FileInputStream("default0.conf");
                prop.load(fis);
            } catch (FileNotFoundException _) {
                fis = new FileInputStream(fileName);
                prop.load(fis);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException _) {}

        setProperties(prop);

    }

    private static void setProperties(Properties p) {
        try {

            readProperites(p);
        } catch (PropertyNotFoundException e){
            System.out.println("Could not read config file correctly: "+e.getMessage()+"\nResorting to Defaults.");
            setDefaults();
        }
    }

    private static void setDefaults() {
        DISCORD_BOT_TOKEN = "none";
    }

    private static void readProperites(Properties p) throws PropertyNotFoundException {
        DISCORD_BOT_TOKEN = tryReadProperty(p, "discordBotToken");
    }

    private static String tryReadProperty(Properties p, String prop) throws PropertyNotFoundException {
        String value = p.getProperty(prop);
        if (value == null)
            throw new PropertyNotFoundException(prop);
        return value;
    }

    private static class PropertyNotFoundException extends Exception {
        public PropertyNotFoundException(String property) {
            super("Could not find the property \""+property+"\" in the file");
        }
    }
}
