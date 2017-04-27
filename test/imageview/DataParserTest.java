/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageview;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Supergirl
 */
public class DataParserTest {
    
    public DataParserTest() {
    }

    /**
     * Test of parseRow method, simple correct one liner
     */
    @Test
    public void testParseRow1() {
        System.out.println("parseRow");
        String row = "http://elelur.com/data_images/mammals/giraffe/giraffe-04.jpg Giraffe";
        DataParser instance = new DataParser();
        ImageData expResult = new ImageData("http://elelur.com/data_images/mammals/giraffe/giraffe-04.jpg","Giraffe");
        ImageData result = instance.parseRow(row);
        assertEquals(expResult.description, result.description);
        assertEquals(expResult.url, result.url);
    }

    /**
     * Test of parseRow method, simple correct one liner with longer comment
     */
    @Test
    public void testParseRow2() {
        System.out.println("parseRow");
        String row = "http://elelur.com/data_images/mammals/giraffe/giraffe-04.jpg I love giraffes!";
        DataParser instance = new DataParser();
        ImageData expResult = new ImageData("http://elelur.com/data_images/mammals/giraffe/giraffe-04.jpg","I love giraffes!");
        ImageData result = instance.parseRow(row);
        assertEquals(expResult.description, result.description);
        assertEquals(expResult.url, result.url);
    }
    
    /**
     * Test of parseRow method, extra white spaces before description
     */
    @Test
    public void testParseRow3() {
        System.out.println("parseRow");
        String row = "http://elelur.com/data_images/mammals/giraffe/giraffe-04.jpg \t I love giraffes!";
        DataParser instance = new DataParser();
        ImageData expResult = new ImageData("http://elelur.com/data_images/mammals/giraffe/giraffe-04.jpg","I love giraffes!");
        ImageData result = instance.parseRow(row);
        assertEquals(expResult.description, result.description);
        assertEquals(expResult.url, result.url);
    }

    /**
     * Test of parseRow method, only comment
     */
    @Test
    public void testParseRow4() {
        System.out.println("parseRow");
        String row = "#Hejsan";
        DataParser instance = new DataParser();
        ImageData expResult = null;
        ImageData result = instance.parseRow(row);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseRow method, missing description
     */
    @Test
    public void testParseRow5() {
        System.out.println("parseRow");
        String row = "http://elelur.com/data_images/mammals/giraffe/giraffe-04.jpg";
        DataParser instance = new DataParser();
        ImageData expResult = new ImageData("http://elelur.com/data_images/mammals/giraffe/giraffe-04.jpg", null);
        ImageData result = instance.parseRow(row);
        assertEquals(expResult.description, result.description);
        assertEquals(expResult.url, result.url);
    }
    
    /**
     * Test of parseRow method, malformed url
     */
    @Test
    public void testParseRow6() {
        System.out.println("parseRow");
        String row = "MalformedUrl.hej Description";
        DataParser instance = new DataParser();
        ImageData expResult = null;
        ImageData result = instance.parseRow(row);
        assertEquals(expResult, result);
    }

    /**
     * Test of parse method, of class DataParser.
     */
    @Test
    public void testParse_URL() throws MalformedURLException {
        System.out.println("parse");
        URL url = new URL("http://bouvet.guru/rekrytering_flera.php");
        DataParser instance = new DataParser();
        List<ImageData> result = instance.parse(url);
        assertTrue(result instanceof List); //whe dont know much more
    }
    
}
