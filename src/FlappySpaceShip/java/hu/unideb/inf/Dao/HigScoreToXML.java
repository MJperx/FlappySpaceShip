package hu.unideb.inf.Dao;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class HigScoreToXML {
    private static final String FILE_NAME = "highscore.xml";

    public static HighScore jaxbXMLToObject() {
        try {
            JAXBContext context = JAXBContext.newInstance(HighScore.class);
            Unmarshaller un = context.createUnmarshaller();
            HighScore emp = (HighScore) un.unmarshal(new File(FILE_NAME));
            return emp;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void jaxbObjectToXML(HighScore emp) {
        try {
            JAXBContext context = JAXBContext.newInstance(HighScore.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(emp, new File(FILE_NAME));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
