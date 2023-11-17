package com.ptumulty.ceramic_api;

import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;

public interface XMLSerializable
{
    void fromXML(Element element);

    Element toXML() throws ParserConfigurationException;
}
