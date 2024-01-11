/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

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
        linterna1.setName("The Game");
        linterna1.setBrand("Neil Strauss");
        linterna1.setRetailer("Harpercollins");

        var linterna2 = new Linternas();
        linterna2.setName("Feuchtgebiete");
        linterna2.setBrand("Charlotte Roche");
        linterna2.setRetailer("Dumont Buchverlag");

        // create linternastore, assigning linternas
        var linternaList = new ArrayList<Linternas>();
        linternaList.add(linterna1);
        linternaList.add(linterna2);

        var linternastore = new Ferreteria();
        linternastore.setName("Fraport Ferreteria");
        linternastore.setLocation("Frankfurt Airport");
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
