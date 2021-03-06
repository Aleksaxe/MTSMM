package XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;


public class XMLWorker {
    private double overrun = 0;
    private ArrayList<String> numbers = new ArrayList<>();
    private ArrayList<Double> overruns = new ArrayList<>();

    private void setNumber(Attributes attributes) {

        numbers.add(attributes.getValue(2));

    }

    private double getOverrun() {
        return this.overrun;
    }

    private void setOverrun(double overrun) {
        this.overrun = overrun;
    }

    private void pushOverrun() {
        overruns.add(getOverrun());
        overrun = 0;
    }


    private DefaultHandler handler = new DefaultHandler() {
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
        public void endElement(String uri, String localName, String qName) {
            if (qName.equalsIgnoreCase("ps")) {
                pushOverrun();
            }
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {

            if (qName.equalsIgnoreCase("tp")) {
                setNumber(attributes);
            }

            if (qName.equalsIgnoreCase("sc")) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    if (attributes.getValue(i).equalsIgnoreCase("телефонные услуги")
                            && !attributes.getValue(i).equalsIgnoreCase("0")) {
                        //pass the tag attributes to the method for further selection of attribute values
                        addOverrun(attributes);
                    } else if (attributes.getValue(i).equalsIgnoreCase("разовые услуги")
                            && !attributes.getValue(i).equalsIgnoreCase("0")) {
                        addOverrun(attributes);

                    }

                }


            }

        }

    };

    private SAXParserFactory factory = SAXParserFactory.newInstance();
    private SAXParser parser;

    {
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    public HashMap parse(String path) {
        try {
            parser.parse(new File(path), handler);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        HashMap<String, Double> map = new HashMap();
        for (int i = 0; i < numbers.size(); i++) {
            double d = overruns.get(i);
            if (d != 0.0) map.put(numbers.get(i), overruns.get(i));

        }

        return map;
    }


}



