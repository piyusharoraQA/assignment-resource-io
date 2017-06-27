package com.qainfotech.tap.training.resourceio;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO {

    public Object getOptionValue(String optionKey) throws IOException {
        //   throw new UnsupportedOperationException("Not implemented.");
        FileReader fileread = new FileReader(new File("C:\\Users\\piyusharora\\Documents\\NetBeansProjects\\assignment-resource-io-master\\src\\test\\resources\\options.properties"));

        Properties p = new Properties();

        p.load(fileread);
        String s = p.getProperty(optionKey);
        return s;
    }

    public void addOption(String optionKey, Object optionValue) throws IOException {
        //  throw new UnsupportedOperationException("Not implemented.");
        FileWriter filewrite = new FileWriter(new File("C:\\Users\\piyusharora\\Documents\\NetBeansProjects\\assignment-resource-io-master\\src\\test\\resources\\options.properties"), true);
        Properties p = new Properties();
        String s = optionValue.toString();
        System.out.println(s);
        p.setProperty(optionKey, s);
        p.store(filewrite, null);
        filewrite.close();
    }
}
