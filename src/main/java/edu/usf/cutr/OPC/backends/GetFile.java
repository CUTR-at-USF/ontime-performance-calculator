/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usf.cutr.OPC.backends;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class GetFile {
    public InputStream getFileFromJar(String path) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
        return is;
    }

    public File getJarLocation() {
        URL jarLocation = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        File f = null;
        try {
            f = new File(jarLocation.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return f;
    }

    // convert InputStream to String
    public static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
