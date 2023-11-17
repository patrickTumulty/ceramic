package com.ptumulty.ceramic_api.utils;

import org.w3c.dom.Node;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;

public class XmlUtils
{
    public static void writeNodeToFile(Node node, String filepath) throws TransformerException, IOException
    {
        try (FileOutputStream outputStream = new FileOutputStream(filepath))
        {
            var transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(node), new StreamResult(outputStream));
        }
    }
}
