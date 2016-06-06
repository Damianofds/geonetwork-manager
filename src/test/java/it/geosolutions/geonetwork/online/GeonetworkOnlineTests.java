package it.geosolutions.geonetwork.online;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.geotools.test.OnlineTestSupport;

/**
* 
* Utility class to enrich the {@link GeonetworkTest} base test class with the GeoTools OnlineTestCase behaviour.
* see http://docs.geotools.org/latest/developer/conventions/test/online.html for more info   
* 
* @author DamianoG (damiano.giampaoli at geo-solutions.it)
*/
public class GeonetworkOnlineTests extends OnlineTestSupport{

    private final static Logger LOGGER = Logger.getLogger(GeonetworkOnlineTests.class);
    
    protected int gnVersion;
    protected String gnServiceURL;
    protected String gnUsername;
    protected String gnPassword;
    
    @Override
    protected String getFixtureId() {
        return "geonetwork.params";
    }
    
    @Override
    protected void connect() {
        String gnVersionAsString = (String)getFixture().get("version");
        try{
            gnVersion = Integer.parseInt(gnVersionAsString);
            if(gnVersion != 2 && gnVersion != 3){
                throw new IllegalArgumentException("Invalid value for the Geonetwork version. The value provided in the fixture file is: '" + gnVersionAsString + "'");
            }
        }
        catch(Exception e){
            throw new IllegalArgumentException("Invalid value for the Geonetwork version. The value provided in the fixture file is: '" + gnVersionAsString + "'");
        }
        gnServiceURL = (String)getFixture().get("URL");
        gnUsername = (String)getFixture().get("username");
        gnPassword = (String)getFixture().get("password");
        
        LOGGER.info("Going to run Online tests against Geonetwork " + gnVersion);
    }
    
    @Override
    protected Properties createExampleFixture() {
        Properties sampleFixture = new Properties();
        sampleFixture.put("version", "type_2_or_3");
        sampleFixture.put("URL", "http://localhost:8080/geonetwork");
        sampleFixture.put("username", "admin");
        sampleFixture.put("password", "admin");
        return sampleFixture;
    }
}
