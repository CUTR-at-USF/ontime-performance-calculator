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
        if (args.length < 2) {
            System.err.println("\nusage: java -jar target/jarfilename.jar <path/to/input_gtfs.zip>  <arrival_timeORdeparture_time>  <Number_of"
                    + "_records_to_fetch> \nThe second argument specify whether to consider arrival_time or departure_time to calculate schedule_deviation"
                    + "\nThe third argument is optional, which specify number of records to fetch. If it not provided we retreive all records");
            return;
        }

        File input = new File(args[0]);
        String arrivalOrDeparture = args[1].toLowerCase();
        if(!arrivalOrDeparture.equals("arrival_time") && !arrivalOrDeparture.equals("departure_time")) {
            System.err.println("\nPlease check spelling of the string provided as second argument. It should be either arrival_time or departure_time");
            return;
        }
        int numRecords = 0;
        if(args.length == 3) {
            numRecords = Integer.parseInt(args[2]);
            if(numRecords <= 0) {
                System.err.println("\nThe third parameter 'Number of records to fetch' should be greater than 0");
                return;
            }
        }
        System.err.println("\nProcessing feed :" + input.getName());
        FeedProcessor processor = new FeedProcessor(input, arrivalOrDeparture, numRecords);
        try {
            processor.load();
        } catch (IOException e) {
            System.err.println("Unable to access input GTFS " + input.getPath() + ". Does the file exist and do I have permission to read it?");
            return;
        }
    }
}
