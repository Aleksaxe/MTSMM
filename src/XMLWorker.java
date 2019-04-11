import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;

public class XMLWorker {
       static private double overrun=0;
       static public double getOverrun() {
           return overrun;
       }
      static public void setOverrun(double overrun) {
           XMLWorker.overrun = overrun;
       }


    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        DefaultHandler handler=new DefaultHandler(){
            void add(Attributes attributes){
                double d=0;
                NumberFormat nf = NumberFormat.getInstance();
                try {
                    double number = nf.parse(attributes.getValue(2)).doubleValue();
                    d=getOverrun()+number;
                    setOverrun(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

             //   while ()
                if (qName.equalsIgnoreCase("sc")&&attributes!=null){
                    for (int i = 0; i <attributes.getLength() ; i++) {
                        if (attributes.getValue(i).equalsIgnoreCase("телефонные услуги")) {
                            add(attributes);

                             }else if (attributes.getValue(i).equalsIgnoreCase("Разовые услуги")){
                            add(attributes);
                        }

                    }

                }
            }
        };

        SAXParserFactory factory=SAXParserFactory.newInstance();
        SAXParser parser=factory.newSAXParser();
        factory.setNamespaceAware(true);


        parser.parse(new File("e:\\!distrib\\1.xml"),handler);
        System.out.println(getOverrun());
        }


}



