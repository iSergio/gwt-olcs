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
package org.ol3cesium.client.ol.source;

import org.ol3cesium.client.olx.source.ImageWMSSourceOptions;
import com.google.gwt.core.client.JavaScriptObject;
import org.ol3cesium.client.ol.Coordinate;
import org.ol3cesium.client.ol.proj.Projection;

/**
 * Source for WMS servers providing single, untiled images.
 * @author Serge Silaev aka iSergio <s.serge.b@gmail.com>
 */
public class ImageWMSSource extends ImageSource {
    protected ImageWMSSource() {
        //
    }
    
    public static final native ImageWMSSource create(ImageWMSSourceOptions options) /*-{
        return new ol.source.ImageWMS(options);
    }-*/;
    
    /**
     * Return the GetFeatureInfo URL for the passed coordinate, resolution, and projection. 
     * Return undefined if the GetFeatureInfo URL cannot be constructed.
     * @param coordinate Coordinate.
     * @param resolution Resolution.
     * @param projection Projection.
     * @param params GetFeatureInfo params. INFO_FORMAT at least should be provided. 
     * If QUERY_LAYERS is not provided then the layers specified in the 
     * LAYERS parameter will be used. VERSION should not be specified here.
     * @return GetFeatureInfo URL.
     */
    public final native String getGetFeatureInfoUrl(Coordinate coordinate, double resolution, Projection projection, JavaScriptObject params) /*-{
        return this.getGetFeatureInfoUrl(coordinate, resolution, projection, params);
    }-*/;
    
    /**
     * Return the GetFeatureInfo URL for the passed coordinate, resolution, and projection. 
     * Return undefined if the GetFeatureInfo URL cannot be constructed.
     * @param coordinate Coordinate.
     * @param resolution Resolution.
     * @param projection Projection.
     * @param params GetFeatureInfo params. INFO_FORMAT at least should be provided. 
     * If QUERY_LAYERS is not provided then the layers specified in the 
     * LAYERS parameter will be used. VERSION should not be specified here.
     * @return GetFeatureInfo URL.
     */
    public final native String getGetFeatureInfoUrl(Coordinate coordinate, double resolution, String projection, JavaScriptObject params) /*-{
        return this.getGetFeatureInfoUrl(coordinate, resolution, projection, params);
    }-*/;
    
//    getImageLoadFunction(){ol.ImageLoadFunctionType} experimental
//
//src/ol/source/imagewmssource.js, line 260
//Return the image load function of the source.
//
//Returns:
//
//The image load function. 

    /**
     * Get the user-provided params, i.e. those passed to the constructor through 
     * the "params" option, and possibly updated using the updateParams method.
     * @return Params.
     */
    public final native JavaScriptObject getParams() /*-{
        return this.getParams();
    }-*/;

    /**
     * Return the URL used for this WMS source.
     * @return URL.
     */
    public final native String getUrl() /*-{
        return this.getUrl();
    }-*/;
    
//    setImageLoadFunction(imageLoadFunction) experimental
//
//src/ol/source/imagewmssource.js, line 341
//Set the image load function of the source.
//
//Name	Type	Description
//imageLoadFunction	ol.ImageLoadFunctionType	
//Image load function.
    
    /**
     * Set the URL to use for requests.
     * @param url URL.
     */
    public final native void setUrl(String url) /*-{
        this.setUrl(url);
    }-*/;

    /**
     * Update the user-provided params.
     * @param params Params.
     */
    public final native void updateParams(JavaScriptObject params) /*-{
        this.updateParams(params);
    }-*/;
}
