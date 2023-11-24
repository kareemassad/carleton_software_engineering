import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBook extends DefaultListModel<BuddyInfo> {

    private List<BuddyInfo> list;

    public AddressBook() {
        list = new ArrayList<>();
    }

    public void addBuddy(BuddyInfo buddy) {
        if (buddy != null) {
            list.add(buddy);
            this.addElement(buddy); // To update the DefaultListModel
        }
    }

    public void save(String fileName) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for (BuddyInfo buddy : list) {
                writer.write(buddy.toString());
                writer.newLine();
            }
        }
        catch (IOException e){
            System.out.println("Failed to save file");
        }
    }

    public void removeBuddy(BuddyInfo buddy) {
        list.remove(buddy);
        this.removeElement(buddy); // To update the DefaultListModel
    }

    public List<BuddyInfo> getList() {
        return list;
    }

    public void setList(List<BuddyInfo> list) {
        this.list = list;
    }

    public String toXML(){
        StringBuilder xml = new StringBuilder("<AddressBook>");
        for(BuddyInfo buddyInfo : list) {
            xml.append(buddyInfo.toXML());
        }
        xml.append("</AddressBook>");
        return xml.toString();
    }

    public void importAddressBook(String fileName) {
        //unsure if these are needed
//        list.clear();
//        this.clear();


        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null){
                BuddyInfo buddy = BuddyInfo.importBuddyInfo(line);
                System.out.println("read: " + buddy.toString());
                list.add(buddy);
                this.addElement(buddy);
            }
        }
        catch (IOException e){
            System.out.println("Failed to import file");
        }
    }

    public void exportToXmlFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write(this.toXML());
        } catch (IOException e){
            System.out.println("Failed to export to XML file");
        }
    }

    public void importFromXmlFile(String fileName) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            AddressBookHandler handler = new AddressBookHandler(this);
            saxParser.parse(new File(fileName), handler);
        } catch (ParserConfigurationException e) {
            System.out.println("Parser Configuration Error: " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("SAX Parsing Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General Error: " + e.getMessage());
        }
    }

    
}
