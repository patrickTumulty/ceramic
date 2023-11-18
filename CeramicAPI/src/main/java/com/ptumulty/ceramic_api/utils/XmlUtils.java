package com.ptumulty.ceramic_api.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Optional;

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

    public static String elementToString(Node node) throws TransformerException
    {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = new StreamResult(new StringWriter());
        transformer.transform(new DOMSource(node), result);
        return result.getWriter().toString();
    }

    public static Optional<Integer> getFirstChildNodeAsInteger(Element parent, String nodeName)
    {
        return getFirstChildNode(parent, nodeName).map(element -> Integer.decode(element.getTextContent()));
    }

    public static Optional<Long> getFirstChildNodeAsLong(Element parent, String nodeName)
    {
        return getFirstChildNode(parent, nodeName).map(element -> Long.decode(element.getTextContent()));
    }

    public static Optional<Boolean> getFirstChildNodeAsBoolean(Element parent, String nodeName)
    {
        return getFirstChildNode(parent, nodeName).map(element -> Boolean.parseBoolean(element.getTextContent()));
    }

    public static Optional<Double> getFirstChildNodeAsDouble(Element parent, String nodeName)
    {
        return getFirstChildNode(parent, nodeName).map(element -> Double.parseDouble(element.getTextContent()));
    }

    public static Optional<String> getFirstChildNodeAsString(Element parent, String nodeName)
    {
        return getFirstChildNode(parent, nodeName).map(Node::getTextContent);
    }

    public static Optional<Element> getFirstChildNode(Element parent, String nodeName)
    {
        var childNodes = parent.getElementsByTagName(nodeName);
        if (childNodes.getLength() > 0)
        {
            return Optional.of((Element) childNodes.item(0));
        }
        return Optional.empty();
    }

    public static Document getDocumentFromFile(File file) throws ParserConfigurationException, IOException, SAXException
    {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }

    public static Document createNewDocument() throws ParserConfigurationException
    {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    }

    public static void addChildElement(Element parent, String name, String stringValue)
    {
        var element = parent.getOwnerDocument().createElement(name);
        element.setTextContent(stringValue);
        parent.appendChild(element);
    }

}
