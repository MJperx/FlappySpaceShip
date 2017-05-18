package hu.unideb.inf.Dao;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 * This class implements the HighScoreDAO interface.
 *
 * Read and write to HighScore.xml file.
 *
 * @author MJ
 *
 */
public class HighScoreDAOImp implements HighScoreDAO {

    @Override
    public void addHighScore(HighScore highScore) {
        // Creating listOfStates
        HighScores highScores = getAllHighScores();
        highScores.addHighScore(highScore);

        try {
            // create JAXB context and initializing Marshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(HighScores.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // for getting nice formatted output
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //URL resourceUrl = getClass().getResource("HighScores.xml");
            URL resourceUrl = getClass().getResource("HighScores.xml");

            File file = new File(resourceUrl.toURI());
            OutputStream XMLfile = new FileOutputStream(file);
            System.out.println(XMLfile);
            jaxbMarshaller.marshal(highScores, file);
            jaxbMarshaller.marshal(highScores, System.out);
            XMLfile.close();

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HighScores getAllHighScores() {
        HighScores highScores = new HighScores();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(HighScores.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            InputStream XMLfile = getClass().getResourceAsStream("HighScores.xml");

            highScores = (HighScores) jaxbUnmarshaller.unmarshal(XMLfile);
            XMLfile.close();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScores;
    }
}