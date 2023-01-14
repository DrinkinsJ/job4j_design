package ru.job4j.ood.srp.formatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarAdapterXML extends XmlAdapter<String, Calendar> {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public Calendar unmarshal(String xml) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DATE_FORMAT.parse(xml));
        return calendar;
    }

    @Override
    public String marshal(Calendar d) {
        return DATE_FORMAT.format(d.getTime());
    }
}
