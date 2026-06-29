package dk.tinker.memo;

import dk.tinker.memo.model.memo.Message;
import dk.tinker.memo.model.memo.MessageHeader;
import dk.tinker.memo.model.datatypes.MemoMessageType;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MemoMarshallerTest {

    @Test
    void roundTripMarshalUnmarshal() throws JAXBException {
        MemoMarshaller marshaller = new MemoMarshaller();

        Message message = new Message();
        message.setMemoVersion(new BigDecimal("1.2"));

        MessageHeader header = new MessageHeader();
        header.setMessageUUID("test-uuid-1234");
        header.setMessageType(MemoMessageType.DIGITALPOST);
        header.setLabel("Test besked");
        message.setMessageHeader(header);

        String xml = marshaller.marshal(message);
        assertNotNull(xml);
        assertTrue(xml.contains("test-uuid-1234"));
        assertTrue(xml.contains("Test besked"));

        Message roundTripped = marshaller.unmarshal(xml);
        assertEquals("test-uuid-1234", roundTripped.getMessageHeader().getMessageUUID());
        assertEquals("Test besked", roundTripped.getMessageHeader().getLabel());
    }
}
