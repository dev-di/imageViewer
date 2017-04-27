/*
 */
package imageview;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Diana
 */
public class ImageData {
    URL url;
    String description;
    String urlString;
    
    public ImageData(String url, String comment) {
        this.description = comment;
        this.urlString = url;
        try {
            this.url = new URL(url);
        } catch (MalformedURLException ex) {
            this.url = null;
        }
    }
    
    public boolean equals(ImageData imageData) {
        return imageData!=null 
                && this.url.equals(imageData.url)
                && this.description.equals(imageData.description);
    }
    
    @Override
    public String toString() {
        return String.format("%s %s",url.toString(), description);
    } 
    
}
