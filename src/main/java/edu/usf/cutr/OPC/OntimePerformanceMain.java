/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usf.cutr.OPC;

import java.io.File;
import java.io.IOException;

public class OntimePerformanceMain {

    /**
     * Take an input GTFS static data file path
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("usage: java -jar target/jarfilename.jar path/to/input_gtfs.zip");
            return;
        }

        File input = new File(args[0]);
        System.err.println("\nProcessing feed :" + input.getName());
        FeedProcessor processor = new FeedProcessor(input);
        try {
            processor.load();
        } catch (IOException e) {
            System.err.println("Unable to access input GTFS " + input.getPath() + ". Does the file exist and do I have permission to read it?");
            return;
        }
    }
}
