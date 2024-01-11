package com.vogella.xml.jaxb.maven.personal;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Personal {
    private static final String FERRETERIA_XML = "./ferreteria-jaxb.xml";

    public static void main(String[] args) throws JAXBException, IOException {
        // create linternas
        var linterna1 = new Linternas();
        linterna1.setName("XL50");
        linterna1.setBrand("Fenix");
        linterna1.setRetailer("Decathlon");

        var linterna2 = new Linternas();
        linterna2.setName("LC90");
        linterna2.setBrand("Anker");
        linterna2.setRetailer("Bolder");
        
        var linterna3 = new Linternas();
        linterna3.setName("G2X Pro");
        linterna3.setBrand("SureFire ");
        linterna3.setRetailer("Firestorm");

        // create linternastore, assigning linternas
        var linternaList = new ArrayList<Linternas>();
        linternaList.add(linterna1);
        linternaList.add(linterna2);
        linternaList.add(linterna3);

        var linternastore = new Ferreteria();
        linternastore.setName("Fraport Ferreteria");
        linternastore.setLocation("Frankfurt");
        linternastore.setLinternaList(linternaList);

        // create JAXB context and instantiate marshaller
        JAXBContext context = JAXBContext.newInstance(Ferreteria.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out
        marshaller.marshal(linternastore, System.out);

        // Write to File
        try (FileWriter fileWriter = new FileWriter(FERRETERIA_XML)) {
            marshaller.marshal(linternastore, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read from File
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (FileReader fileReader = new FileReader(FERRETERIA_XML)) {
            Ferreteria FerreteriaFromFile = (Ferreteria) unmarshaller.unmarshal(fileReader);
            List<Linternas> linternaListFromFile = FerreteriaFromFile.getLinternasList();

            System.out.println("\nOutput from our XML File: ");
            for (Linternas linterna : linternaListFromFile) {
                System.out.println("Linterna: " + linterna.getName() + " from " + linterna.getBrand());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}