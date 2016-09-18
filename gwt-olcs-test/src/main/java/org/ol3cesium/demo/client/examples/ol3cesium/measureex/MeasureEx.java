/*
 * Copyright 2016 Serge Silaev aka iSergio <s.serge.b@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ol3cesium.demo.client.examples.ol3cesium.measureex;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.ol3cesium.client.ol.Coordinate;
import org.ol3cesium.client.ol.Map;
import org.ol3cesium.client.ol.MapPanel;
import org.ol3cesium.client.ol.OLConfiguration;
import org.ol3cesium.client.ol.View;
import org.ol3cesium.client.ol.control.ZoomSliderControl;
import org.ol3cesium.client.ol.geom.GeometryType;
import org.ol3cesium.client.ol.layer.BaseLayer;
import org.ol3cesium.client.ol.layer.TileLayer;
import org.ol3cesium.client.ol.source.OSMSource;
import org.ol3cesium.client.olx.MapOptions;
import org.ol3cesium.client.olx.ViewOptions;
import org.ol3cesium.client.olx.layer.TileLayerOptions;
import org.ol3cesium.demo.client.basic.AbstractExample;
import org.ol3cesium.demo.client.components.store.ShowcaseExampleStore;

/**
 *
 * @author Serge Silaev aka iSergio <s.serge.b@gmail.com>
 */
public class MeasureEx extends AbstractExample {
    private ToggleButton _drawLineStringTBtn;
    private ToggleButton _drawPolygonTBtn;
    private ToggleButton _drawCircleTBtn;
    
    private Measure _measure;
    
    public class MapWidget implements IsWidget {
        private MapPanel _mapPanel;
        
        public MapWidget() {
            super();
            asWidget();
        }

        @Override
        public final Widget asWidget() {
            if (_mapPanel == null) {
                OLConfiguration olConfiguration = new OLConfiguration();
                olConfiguration.setPath(GWT.getModuleBaseURL() + "JavaScript/ol3/");
                olConfiguration.setName("ol-debug.js");
                List<String> styles = new ArrayList<String>();
                styles.add(GWT.getModuleBaseURL() + "JavaScript/ol3/ol.css");
                styles.add(GWT.getModuleBaseURL() + "JavaScript/tooltip.css");
                olConfiguration.setStyles(styles);
                
                /**
                 * Construct OpenLayers 3 map
                 */
                _mapPanel = new MapPanel(olConfiguration) {
                    @Override
                    public Map createMap(Element element) {
                        TileLayerOptions tileLayerOptions = TileLayerOptions.create();
                        tileLayerOptions.setSource(OSMSource.create());
                        TileLayer tileLayer = TileLayer.create(tileLayerOptions);
                        
                        JsArray<BaseLayer> layers = JsArray.createArray().cast();
                        layers.push(tileLayer);

                        MapOptions mapOptions = MapOptions.create();
                        mapOptions.setLogo(false);
                        mapOptions.setLoadTilesWhileAnimating(true);
                        mapOptions.setLoadTilesWhileInteracting(true);
                        mapOptions.setTarget(element);
                        mapOptions.setLayers(layers);
                        
                        ViewOptions viewOptions = ViewOptions.create();
                        viewOptions.setCenter(Coordinate.create(-11000000, 4600000));
                        viewOptions.setZoom(4);

                        View view = View.create(viewOptions);
                        mapOptions.setView(view);

                        _map = Map.create(mapOptions);

                        _map.addControl(ZoomSliderControl.create());

                        return _map;
                    }
                };
            }
            return _mapPanel;
        }
        
        public Map getMap() {
            return _mapPanel.getMap();
        }
    }
    
    @Inject
    public MeasureEx(ShowcaseExampleStore store) {
        super("Extended measure", "Example of using the ol.interaction.Draw interaction to create a extended measuring application.", new String[]{"Map", "View", "Tile", "Measure", "Circular", "Polygon", "Geodesic", "Arc"}, store);
    }
    
    @Override
    public void buildPanel() {
        final MapWidget mapWidget = new MapWidget();
        mapWidget.asWidget().setHeight("400px");
        
        _drawLineStringTBtn = new ToggleButton();
        _drawLineStringTBtn.setText("Line length");
        _drawLineStringTBtn.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                _drawPolygonTBtn.setValue(false, false);
                _drawCircleTBtn.setValue(false, false);
                
                if (_measure == null) {
                    _measure = new Measure(mapWidget.getMap());
                    _measure.setGeodesic(true);
                }
                _measure.setMeasure(GeometryType.LINE_STRING, event.getValue());
            }
        });
        
        _drawPolygonTBtn = new ToggleButton();
        _drawPolygonTBtn.setText("Polygon area");
        _drawPolygonTBtn.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                _drawLineStringTBtn.setValue(false, false);
                _drawCircleTBtn.setValue(false, false);
                
                if (_measure == null) {
                    _measure = new Measure(mapWidget.getMap());
                    _measure.setGeodesic(true);
                }
                _measure.setMeasure(GeometryType.POLYGON, event.getValue());
            }
        });
        
        _drawCircleTBtn = new ToggleButton();
        _drawCircleTBtn.setText("Circle area and radius");
        _drawCircleTBtn.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                _drawLineStringTBtn.setValue(false, false);
                _drawPolygonTBtn.setValue(false, false);
                
                if (_measure == null) {
                    _measure = new Measure(mapWidget.getMap());
                    _measure.setGeodesic(true);
                }
                _measure.setMeasure(GeometryType.CIRCLE, event.getValue());
            }
        });
        
        PushButton measureClearPBtn = new PushButton();
        measureClearPBtn.setText("Clear");
        measureClearPBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (_measure == null) {
                    return;
                }
                _measure.clearMeasure();
            }
        });
        
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.add(_drawLineStringTBtn);
        horizontalPanel.add(_drawPolygonTBtn);
        horizontalPanel.add(_drawCircleTBtn);
        horizontalPanel.add(measureClearPBtn);
        
        contentPanel.add(new HTML("<p>Example of using the ol.interaction.Draw interaction to create a extended measuring application.</p>"));
        contentPanel.add(mapWidget);
        contentPanel.add(horizontalPanel);

        initWidget(contentPanel);
    }

//    @Override
//    public String getSourceCodeURL() {
//        return GWT.getModuleBaseURL() + "examples/ol3cesium/" + "MeasureEx.txt";
//    }
    
    @Override
    public String[] getSourceCodeURLs() {
        String[] sourceCodeURLs = new String[4];
        
        sourceCodeURLs[0] = GWT.getModuleBaseURL() + "examples/ol3cesium/measureex/MeasureEx.txt";
        sourceCodeURLs[1] = GWT.getModuleBaseURL() + "examples/ol3cesium/measureex/Measure.txt";
        sourceCodeURLs[2] = GWT.getModuleBaseURL() + "examples/ol3cesium/measureex/MeasureOverlay.txt";
        sourceCodeURLs[3] = GWT.getModuleBaseURL() + "examples/ol3cesium/measureex/GreatCircle.txt";
        
        return sourceCodeURLs;
    }
}