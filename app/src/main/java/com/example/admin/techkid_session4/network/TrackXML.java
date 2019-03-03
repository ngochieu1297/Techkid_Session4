package com.example.admin.techkid_session4.network;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "track", strict = false)
public class TrackXML {
    @Element(name = "location")
    public String location;
}
