import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class XMLWorker {
    static private double overrun = 0;
    static private ArrayList<String> numbers = new ArrayList<>();
    private static ArrayList<Double> overruns = new ArrayList<>();

    private static ArrayList<String> getNumber() {
        return numbers;
    }

    private static void setNumber(Attributes attributes) {

        numbers.add(attributes.getValue(2));

    }

    private static double getOverrun() {
        return overrun;
    }

    private static void setOverrun(double overrun) {
        XMLWorker.overrun = overrun;
    }

    private static void pushOverrun() {
        overruns.add(getOverrun());
        overrun=0;
    }


    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        DefaultHandler handler = new DefaultHandler() {
            void addOverrun(Attributes attributes) {
                double d;
                NumberFormat nf = NumberFormat.getInstance();
                try {
                    double number = nf.parse(attributes.getValue(2)).doubleValue();
                    d = getOverrun() + number;
                    setOverrun(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (qName.equalsIgnoreCase("ps")) {
                    pushOverrun();
                }
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

                if (qName.equalsIgnoreCase("tp")) {
                    setNumber(attributes);
                }

                if (qName.equalsIgnoreCase("sc")) {
                    for (int i = 0; i < attributes.getLength(); i++) {
                        if (attributes.getValue(i).equalsIgnoreCase("телефонные услуги")
                                &&!attributes.getValue(i).equalsIgnoreCase("0")) {
                            addOverrun(attributes);

                        } else if (attributes.getValue(i).equalsIgnoreCase("блокировки")
                                &&!attributes.getValue(i).equalsIgnoreCase("0")) {
                            addOverrun(attributes);
                    } else if (attributes.getValue(i).equalsIgnoreCase("разовые услуги")
                                &&!attributes.getValue(i).equalsIgnoreCase("0")) {
                        addOverrun(attributes);
                    }

                    }


                }

            }

        };

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("e:\\!distrib\\1.xml"), handler);
        System.out.println(numbers.size());
        System.out.println(overruns.toString());

    }


}



