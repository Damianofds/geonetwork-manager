package it.geosolutions.geonetwork.onlinetests;

import java.util.Properties;

import org.geotools.test.OnlineTestSupport;

import it.geosolutions.geonetwork.GeonetworkTest;

/**
* 
* Utility class to enrich the {@link GeonetworkTest} base test class with the GeoTools OnlineTestCase behaviour.
* see http://docs.geotools.org/latest/developer/conventions/test/online.html for more info   
* 
* @author DamianoG (damiano.giampaoli at geo-solutions.it)
*/
public class GeonetworkOnlineTests extends OnlineTestSupport{

    protected String gnServiceURL;
    protected String gnUsername;
    protected String gnPassword;
    
    @Override
    protected String getFixtureId() {
        return "geonetwork.params";
    }
    
    @Override
    protected void connect() {
        
        gnServiceURL = (String)getFixture().get("URL");
        gnUsername = (String)getFixture().get("username");
        gnPassword = (String)getFixture().get("password");
    }
    
    @Override
    protected Properties createExampleFixture() {
        Properties sampleFixture = new Properties();
        sampleFixture.put("URL", "http://localhost:8080/geonetwork");
        sampleFixture.put("username", "admin");
        sampleFixture.put("password", "admin");
        return sampleFixture;
    }
}
