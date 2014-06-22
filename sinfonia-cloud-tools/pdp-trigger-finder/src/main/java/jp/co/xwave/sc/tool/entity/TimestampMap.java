/**
 *
 */
package jp.co.xwave.sc.tool.entity;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author hirai
 *
 */
public class TimestampMap extends ToStringObject {

    /** */
    private String date;
    /** */
    private String time;
    /** */
    private String timeZone;

    /**
     * @return date
     */
    @XmlAttribute
    public String getDate() {
        return date;
    }

    /**
     * @param date
     *            セットする date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return time
     */
    @XmlAttribute
    public String getTime() {
        return time;
    }

    /**
     * @param time
     *            セットする time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return timeZone
     */
    @XmlAttribute
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * @param timeZone
     *            セットする timeZone
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

}
