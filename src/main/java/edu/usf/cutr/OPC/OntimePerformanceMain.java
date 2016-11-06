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
        if (args.length < 1) {
            System.err.println("\nusage: java -jar target/jarfilename.jar path/to/input_gtfs.zip Number_of"
                    + "_records_to_fetch \nThe seond argument is optional. If it not provided we retreive all records");
            return;
        }

        File input = new File(args[0]);
        int numRecords = 0;
        if(args.length == 2) {
            numRecords = Integer.parseInt(args[1]);
            if(numRecords <= 0) {
                System.err.println("\nThe second parameter 'Number of records to fetch' should be greater than 0");
                return;
            }
        }
        System.err.println("\nProcessing feed :" + input.getName());
        FeedProcessor processor = new FeedProcessor(input, numRecords);
        try {
            processor.load();
        } catch (IOException e) {
            System.err.println("Unable to access input GTFS " + input.getPath() + ". Does the file exist and do I have permission to read it?");
            return;
        }
    }
}
