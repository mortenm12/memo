package dk.tinker.memo;

import dk.tinker.memo.model.memo.Message;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class MemoMarshaller {

    private final JAXBContext context;

    public MemoMarshaller() throws JAXBException {
        this.context = JAXBContext.newInstance(Message.class);
    }

    public String marshal(Message message) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(message, writer);
        return writer.toString();
    }

    public Message unmarshal(String xml) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Message) unmarshaller.unmarshal(new StringReader(xml));
    }
}
