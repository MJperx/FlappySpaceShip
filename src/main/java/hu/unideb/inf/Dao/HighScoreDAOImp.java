package hu.unideb.inf.Dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 * The {@code HighScoreDAOImp} class implements the {@link HighScoreDAO} interface.
 * Read and write to HighScore.xml file.
 * @author MJ
 */
public class HighScoreDAOImp implements HighScoreDAO {
    /** {@link Logger} for logging.*/
    private static Logger logger = LoggerFactory.getLogger( HighScoreDAOImp.class );

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

            URL resourceUrl = getClass().getResource("HighScores.xml");

            File file = new File(resourceUrl.toURI());
            OutputStream XMLfile = new FileOutputStream(file);
            jaxbMarshaller.marshal(highScores, file);
            XMLfile.close();
            logger.info("Add new HighScore: " + highScore.getName() + " score: " + highScore.getScore());
        } catch (JAXBException e) {
            logger.error("JAXB Exception during marshalling.");
        } catch (URISyntaxException e) {
            logger.error("URISyntax Exception during marshalling.");
        } catch (IOException e) {
            logger.error("IO Exception during marshalling.");
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
            logger.error("JAXB Exception during unmarshalling.");
        } catch (IOException e) {
            logger.error("IO Exception during unmarshalling.");
        }
        return highScores;
    }
}