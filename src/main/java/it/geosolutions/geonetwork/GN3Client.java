/*
 *  GeoNetwork-Manager - Simple Manager Library for GeoNetwork
 *
 *  Copyright (C) 2007,2016 GeoSolutions S.A.S.
 *  http://www.geo-solutions.it
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package it.geosolutions.geonetwork;

import java.io.File;

import org.apache.log4j.Logger;
import org.jdom.Element;

import it.geosolutions.geonetwork.exception.GNLibException;
import it.geosolutions.geonetwork.exception.GNServerException;
import it.geosolutions.geonetwork.op.GNInfo;
import it.geosolutions.geonetwork.op.GNMetadataAdmin;
import it.geosolutions.geonetwork.op.GNMetadataDelete;
import it.geosolutions.geonetwork.op.GNMetadataGet;
import it.geosolutions.geonetwork.op.GNMetadataGetInfo;
import it.geosolutions.geonetwork.op.GNMetadataGetVersion;
import it.geosolutions.geonetwork.op.GNMetadataInsert;
import it.geosolutions.geonetwork.op.GNMetadataSearch;
import it.geosolutions.geonetwork.op.GNMetadataUpdate;
import it.geosolutions.geonetwork.op.GNMetadataGetInfo.MetadataInfo;
import it.geosolutions.geonetwork.util.GNInsertConfiguration;
import it.geosolutions.geonetwork.util.GNPrivConfiguration;
import it.geosolutions.geonetwork.util.GNSearchRequest;
import it.geosolutions.geonetwork.util.GNSearchResponse;
import it.geosolutions.geonetwork.util.HTTPUtils;

/**
 * 
 * @author DamianoG (damiano.giampaoli at geo-solutions.it)
 */
public class GN3Client extends GNAbstractClient {

    private final static Logger LOGGER = Logger.getLogger(GN3Client.class);

    public GN3Client(String serviceURL) {
        super(serviceURL);
        LOGGER.info("A Geonetwork3 client successfully instantiated!");
    }

    public GN3Client(String serviceURL, String username, String password) {
        super(serviceURL, username, password);
        LOGGER.info("A Geonetwork3 client successfully instantiated!");
    }
    
    @Override
    public boolean ping() {
        return GNInfo.ping(connection, gnServiceURL);
    }

    @Override
    public boolean login(String username, String password) {
        LOGGER.error("Login operation is no longer supported. Please use authenticated constructor");
        return false;
    }

    @Override
    public long insertMetadata(GNInsertConfiguration cfg, File metadataFile) throws GNLibException, GNServerException {
        return GNMetadataInsert.insertMetadata(connection, gnServiceURL, metadataFile, cfg);
    }

    @Override
    public long insertRequest(File requestFile) throws GNLibException, GNServerException {
        return GNMetadataInsert.insertRequest(connection, gnServiceURL, requestFile);
    }

    @Override
    public void setPrivileges(long metadataId, GNPrivConfiguration cfg) throws GNLibException, GNServerException {
        GNMetadataAdmin.setPriv(connection, gnServiceURL, metadataId, cfg);
        
    }

    @Override
    public GNSearchResponse search(GNSearchRequest searchRequest) throws GNLibException, GNServerException {
        return GNMetadataSearch.search(connection, gnServiceURL, searchRequest);
    }

    @Override
    public GNSearchResponse search(File fileRequest) throws GNLibException, GNServerException {
        return GNMetadataSearch.search(connection, gnServiceURL, fileRequest);
    }

    @Override
    public Element get(Long id) throws GNLibException, GNServerException {
        return GNMetadataGet.get(connection, gnServiceURL, id);
    }

    @Override
    public Element get(String uuid) throws GNLibException, GNServerException {
        return GNMetadataGet.get(connection, gnServiceURL, uuid);
    }

    @Override
    public void deleteMetadata(long id) throws GNLibException, GNServerException {
        GNMetadataDelete.delete(connection, gnServiceURL, id);
        
    }

    @Override
    public void updateMetadata(long id, File metadataFile) throws GNLibException, GNServerException {
        String version = GNMetadataGetVersion.get(connection, gnServiceURL, id);
        GNMetadataUpdate.update(connection, gnServiceURL, id, version, metadataFile);
    }

    @Override
    public void updateMetadata(long id, int version, File metadataFile) throws GNLibException, GNServerException {
        GNMetadataUpdate.update(connection, gnServiceURL, id, Integer.toString(version), metadataFile);
    }

    @Override
    public MetadataInfo getInfo(Long id, boolean forUpdate) throws GNLibException, GNServerException {
        return GNMetadataGetInfo.get(connection, gnServiceURL, id, forUpdate);
    }

    @Override
    public MetadataInfo getInfo(String uuid, boolean forUpdate) throws GNLibException, GNServerException {
        return GNMetadataGetInfo.get(connection, gnServiceURL, uuid, forUpdate);
    }

    @Override
    public void updateMetadata(long id, File metadataFile, String encoding) throws GNLibException, GNServerException {
        String version = GNMetadataGetVersion.get(connection, gnServiceURL, id);
        GNMetadataUpdate.update(connection, gnServiceURL, id, version, metadataFile, encoding);
    }

    @Override
    public void updateMetadata(long id, int version, File metadataFile, String encoding) throws GNLibException, GNServerException {
        GNMetadataUpdate.update(connection, gnServiceURL, id, Integer.toString(version), metadataFile, encoding);
    }
}
