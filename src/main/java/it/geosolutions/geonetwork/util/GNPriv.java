/*
 *  GeoNetwork-Manager - Simple Manager Library for GeoNetwork
 *
 *  Copyright (C) 2007,2011 GeoSolutions S.A.S.
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
package it.geosolutions.geonetwork.util;

/**
 * Operation privileges as required by GeoNetwork.
 * <p>
 * In the <a href="http://geonetwork-opensource.org/latest/developers/xml_services/metadata_xml_services.html#request-to-metadata-admin-service">
 * geonetwork services documentation page</a>, operations are defined by their
 * own id, each representing a granted privilege: <UL>
 * <LI>0: view</LI>
 * <LI>1: download</LI>
 * <LI>2: editing</LI>
 * <LI>3: notify</LI>
 * <LI><i>4: dynamic</i></LI>
 * <LI><i>5: featured</i></LI>
 * </UL>
 *
 * Anyway the <a href="http://geonetwork.svn.sourceforge.net/viewvc/geonetwork/branches/2.6.x/web/src/main/webapp/WEB-INF/classes/setup/sql/data/data-db-mckoi.sql?revision=7877&view=markup"
 * >DB init scripts</a> in geonetwork define <pre>{@code:
 * INSERT INTO Operations VALUES  (0,'view','y');
 * INSERT INTO Operations VALUES  (1,'download','y');
 * INSERT INTO Operations VALUES  (2,'editing','y');
 * INSERT INTO Operations VALUES  (3,'notify','y');
 * INSERT INTO Operations VALUES  (5,'dynamic','y');
 * INSERT INTO Operations VALUES  (6,'featured','y');}</pre>
 * (note the differences starting from id 4).<br/>
 * <P>
 * <u>We'll align to this latter definitions</u>.
 * Anyway, you may use the {@link GNPrivAlt} enum as alternative in case you need it.
 *
 *
 * @author ETj (etj at geo-solutions.it)
 */
public enum GNPriv {
    /** priv #0 */
    VIEW(0),
    /** priv #1 */
    DOWNLOAD(1),
    /** priv #2 */
    EDITING(2),
    /** priv #3 */
    NOTIFY(3),
    /** priv #5 */
    DYNAMIC(5),
    /** priv #6 */
    FEATURED(6);

    private int id;

    private GNPriv(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static GNPriv get(int id) {
        for (GNPriv priv : GNPriv.values()) {
            if(priv.getId() == id)
                return priv;
        }
        return null;
    }
}
