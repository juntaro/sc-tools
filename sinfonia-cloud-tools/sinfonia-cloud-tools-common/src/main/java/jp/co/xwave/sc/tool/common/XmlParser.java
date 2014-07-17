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
 * XMLパーサー
 * 
 * @author hirai
 */
public class XmlParser {

    /**
     * XMLをJAXBオブジェクトに変換する
     * @param xml XMLのテキスト表現
     * @param clazz JAXBオブジェクト型のクラス
     * @return JAXBオブジェクト
     */
    @SuppressWarnings("unchecked")
	public static <T> T convert(String xml, Class<T> clazz) {
        InputStream in;
        try {
            in = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            JAXBContext context = JAXBContext.newInstance(clazz);
            T response = (T) context.createUnmarshaller().unmarshal(in);
            return response;
        } catch (UnsupportedEncodingException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * JAXBオブジェクトをXMLに変換する
     * @param jaxb JAXBオブジェクト
     * @param clazz JAXBオブジェクト型のクラス
     * @return XML文字列
     * @throws JAXBException
     */
    public static <T> String convert(T jaxb, Class<T> clazz) {
        try {
        	JAXBContext context = JAXBContext.newInstance(clazz);
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            context.createMarshaller().marshal(jaxb, b);
            return b.toString("UTF-8");
        } catch (UnsupportedEncodingException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
