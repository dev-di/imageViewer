/*
 * 
 */
package imageview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Diana
 */
public class DataParser {
    URL url;
    public DataParser() {
    }

    public ImageData parseRow(String row) {
        row = row.replaceFirst("\\s+","\t"); //merge multiple whitespaces
        String[] split = row.split("\t",2); //split at first whitespace

        if(split.length==0 || split[0].startsWith("#")) return null;
        
        ImageData imageData = null;
        imageData = new ImageData(split[0], ((split.length==1)?null:split[1]));
        
        if(imageData.url != null) return imageData;
            
        return null;
    }
    
    public List<ImageData> parse(String content) {
        List<ImageData> list = new LinkedList<>();
        list.add(new ImageData("",""));
        return list;
    }
    
    public List<ImageData> parse(URL url){
        List<ImageData> result = new LinkedList<>();
        try {
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                                    connection.getInputStream()));
            String line = in.readLine();
            while(line!=null) {
                ImageData parsedRow = parseRow(line);
                if(parsedRow != null) {
                    result.add(parsedRow);
                }
                line = in.readLine();
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(DataParser.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return result;
    }

}
