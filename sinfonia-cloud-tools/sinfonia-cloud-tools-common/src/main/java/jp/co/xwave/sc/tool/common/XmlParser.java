/**
 *
 */
package jp.co.xwave.sc.tool.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * @author hirai
 */
public class XmlParser {

    /**
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T convert(String xml, Class<T> clazz) throws JAXBException {
        InputStream in;
        try {
            in = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            JAXBContext context = JAXBContext.newInstance(clazz);
            T response = (T) context.createUnmarshaller().unmarshal(in);
            return response;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param jaxb
     * @param clazz
     * @return
     * @throws JAXBException
     */
    public static <T> String convert(T jaxb, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        context.createMarshaller().marshal(jaxb, b);
        try {
            return b.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
