/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usf.cutr.OPC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DatabaseConnectionInfo {
    private String username;
    private String password;
    private String database;
    private String server;
    
    protected DatabaseConnectionInfo(String inputFile) throws IOException {
        setFields(inputFile);
    }
    
    private void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setDatabase(String databaseName) {
        this.database = databaseName;
    }

    private void setServer(String serverName) {
        this.server = serverName;
    }
    
    private void setFields(String input) throws IOException {
        InputStream file = getClass().getResourceAsStream(input);
        if(file == null) {
            System.err.println("\nERROR: Could not find 'info.txt' file in src/main/resources folder."
                    + " Read README file to know more about 'info.txt' file\n");
            System.exit(0);
        }
        try {
            String[] keyValue;
            String data;
            BufferedReader br = new BufferedReader(new InputStreamReader(file));
            while((data = br.readLine()) != null)
            {
                keyValue = data.split(":", 2);
                keyValue[0] = keyValue[0].trim();
                keyValue[1] = keyValue[1].trim();
                if(keyValue[0].equals("username"))
                    setUsername(keyValue[1]);
                else if(keyValue[0].equals("password"))
                    setPassword(keyValue[1]);
                else if(keyValue[0].equals("database"))
                    setDatabase(keyValue[1]);
                else if(keyValue[0].equals("server"))
                    setServer(keyValue[1]);
            }
        }
        finally {
                file.close();
            }
    }
    
    protected String getUsername() {
            return this.username;
    }
    
    protected String getPassword() {
            return this.password;
    }
    
    protected String getDatabase() {
            return this.database;
    }
    
    protected String getServer() {
            return this.server;
    }
}
