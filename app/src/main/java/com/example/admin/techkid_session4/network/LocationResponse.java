package com.example.admin.techkid_session4.network;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "trackList", strict = false)
public class LocationResponse {
    @Element(name = "track")
    public TrackXML trackXML;
}

