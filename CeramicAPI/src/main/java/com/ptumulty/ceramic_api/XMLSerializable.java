package com.ptumulty.ceramic_api;

import com.ptumulty.ceramic_api.utils.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;

public interface XMLSerializable
{
    String getRootTagName();

    void fromXML(Element element);

    default Element toXML() throws ParserConfigurationException
    {
        return toXML(XmlUtils.createNewDocument());
    }

    Element toXML(Document doc);
}
