package hu.unideb.inf.Dao;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class HighScoreXMLObj {
    private static final String FILE_NAME = "highscore.xml";

    public static void readToObject(){
        try {
            File file = new File(FILE_NAME);
            JAXBContext jaxbContext = JAXBContext.newInstance(HighScore.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            HighScore highScore = (HighScore) jaxbUnmarshaller.unmarshal(file);
            System.out.println(highScore);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}