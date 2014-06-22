/**
 *
 */
package jp.co.xwave.sc.tool;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * @author hirai
 * TODO レスポンスの型クラスはAPI毎に作る予定。ひょっとしたらリクエストも。
 */
public class XmlParser {

    /**
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T convert(InputStream in, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        T response = (T) context.createUnmarshaller().unmarshal(in);
        return response;
    }

}
