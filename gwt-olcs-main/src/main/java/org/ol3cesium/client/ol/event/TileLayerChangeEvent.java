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
package org.ol3cesium.client.ol.event;

import org.ol3cesium.client.ol.layer.BaseLayer;

/**
 *
 * @author Serge Silaev aka iSergio <s.serge.b@gmail.com>
 */
public class TileLayerChangeEvent extends Event {
    public static enum Type {EXTENT, MAX_RESOLUTION, MIN_RESOLUTION, OPACITY, PRELOAD, SOURCE, USE_INTERIM_TILES_ON_ERROR, VISIBLE, ZINDEX, UNKNOWN};
    
    protected TileLayerChangeEvent() {
        //
    }
    
    public native final BaseLayer getLayer() /*-{
        return this.layer;
    }-*/;
    
    public final Type getType() {
        String type = getNativeType();
        if (type.equals("extent")) {
            return Type.EXTENT;
        }
        else if (type.equals("maxResolution")) {
            return Type.MAX_RESOLUTION;
        }
        else if (type.equals("minResolution")) {
            return Type.MIN_RESOLUTION;
        }
        else if (type.equals("opacity")) {
            return Type.OPACITY;
        }
        else if (type.equals("preload")) {
            return Type.PRELOAD;
        }
        else if (type.equals("source")) {
            return Type.SOURCE;
        }
        else if (type.equals("useInterimTilesOnError")) {
            return Type.USE_INTERIM_TILES_ON_ERROR;
        }
        else if (type.equals("visible")) {
            return Type.VISIBLE;
        }
        else if (type.equals("zIndex")) {
            return Type.ZINDEX;
        } else {
            return Type.UNKNOWN;
        }
    }
}
