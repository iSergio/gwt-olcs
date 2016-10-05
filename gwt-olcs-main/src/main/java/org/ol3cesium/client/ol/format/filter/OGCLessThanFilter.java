/*
 * Copyright 2016 iserge.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ol3cesium.client.ol.format.filter;

/**
 *
 * @author iserge
 */
public class OGCLessThanFilter extends OGCComparisonBinaryFilter {
    protected OGCLessThanFilter() {
        //
    }
    
    /**
     * Represents a <PropertyIsLessThan> comparison operator.
     * @param propertyName Name of the context property to compare.
     * @param expression The value to compare.
     * @return Represents a <PropertyIsLessThan> comparison operator.
     */
    public static final native OGCLessThanFilter create(String propertyName, double expression) /*-{
        return new ol.format.ogc.filter.LessThan(propertyName, expression);
    }-*/;
}
