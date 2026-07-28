package com.app.irrverbquiz.verbs;

import android.content.Context;

import com.app.irrverbquiz.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public final class VerbsLoader {
    public static final VerbsLoader instance = new VerbsLoader();

    private VerbsLoader() {}

    public void loadVerbs(Context context, List<VerbInfo> infoList) {
        try (InputStream inputStream = context.getResources().openRawResource(R.raw.verbs)) {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            readData(document, infoList);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private void readData(Document document, List<VerbInfo> infoList) {
        Element root = document.getDocumentElement();
        NodeList verbTags = root.getElementsByTagName("verb");
        for (int i = 0; i < verbTags.getLength(); i++) {
            Element verbTag = (Element)verbTags.item(i);
            String base = verbTag.getElementsByTagName("base").item(0).getTextContent();
            String past = verbTag.getElementsByTagName("past").item(0).getTextContent();
            String participle = verbTag.getElementsByTagName("participle").item(0).getTextContent();
            String meaning = verbTag.getElementsByTagName("meaning").item(0).getTextContent();
            infoList.add(new VerbInfo(base, past, participle, meaning));
        }
    }
}
