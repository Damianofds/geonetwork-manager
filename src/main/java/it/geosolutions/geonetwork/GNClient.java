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

import org.jdom.Element;

import it.geosolutions.geonetwork.exception.GNLibException;
import it.geosolutions.geonetwork.exception.GNServerException;
import it.geosolutions.geonetwork.op.GNMetadataAdmin;
import it.geosolutions.geonetwork.op.GNMetadataInsert;
import it.geosolutions.geonetwork.op.GNMetadataGetInfo.MetadataInfo;
import it.geosolutions.geonetwork.util.GNInsertConfiguration;
import it.geosolutions.geonetwork.util.GNPrivConfiguration;
import it.geosolutions.geonetwork.util.GNSearchRequest;
import it.geosolutions.geonetwork.util.GNSearchResponse;
import it.geosolutions.geonetwork.util.HTTPUtils;

/**
* Facade for the various GN operations
*  
* @author DamianoG (damiano.giampaoli at geo-solutions.it)
*/
public interface GNClient {

    boolean ping();

    /**
     * Kept for backward compatibility
     */
    boolean login(String username, String password);

    /**
     * Facade for {@link GNMetadataInsert#insertMetadata(HTTPUtils, String, File, GNInsertConfiguration)}
     */
    long insertMetadata(GNInsertConfiguration cfg, File metadataFile) throws GNLibException, GNServerException;

    /**
     * Facade for {@link GNMetadataInsert#insertRequest((HTTPUtils connection, String gnServiceURL, File inputFile))}
     */
    long insertRequest(File requestFile) throws GNLibException, GNServerException;

    /**
     * Facade for {@link GNMetadataAdmin#setPriv(it.geosolutions.geonetwork.util.HTTPUtils, java.lang.String, long, it.geosolutions.geonetwork.util.GNPrivConfiguration) }
     */
    void setPrivileges(long metadataId, GNPrivConfiguration cfg) throws GNLibException, GNServerException;

    GNSearchResponse search(GNSearchRequest searchRequest) throws GNLibException, GNServerException;

    GNSearchResponse search(File fileRequest) throws GNLibException, GNServerException;

    Element get(Long id) throws GNLibException, GNServerException;

    Element get(String uuid) throws GNLibException, GNServerException;

    public void deleteMetadata(long id) throws GNLibException, GNServerException;

    void updateMetadata(long id, File metadataFile) throws GNLibException, GNServerException;

    void updateMetadata(long id, int version, File metadataFile) throws GNLibException, GNServerException;
    
    public void updateMetadata(long id, File metadataFile, String encoding) throws GNLibException, GNServerException;

    public void updateMetadata(long id, int version, File metadataFile, String encoding) throws GNLibException, GNServerException;

    /**
     * Uses the custom service xml.metadata.info.get.
     * See http://trac.osgeo.org/geonetwork/ticket/1062
     */
    MetadataInfo getInfo(Long id, boolean forUpdate) throws GNLibException, GNServerException;

    /**
     * Uses the custom service xml.metadata.info.get.
     * See http://trac.osgeo.org/geonetwork/ticket/1062
     */
    MetadataInfo getInfo(String uuid, boolean forUpdate) throws GNLibException, GNServerException;
    
    HTTPUtils getConnection();
}