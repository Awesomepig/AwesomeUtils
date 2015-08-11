package com.asomepig.xml;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.plugin.dom.core.Document;
import sun.plugin.dom.core.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Project: AwesomeUtils
 * Package: com.asomepig.xml
 * Created by eric on 15-8-11 下午4:17.
 */
public class ResolveXml {
    // ============================================= local field
    public String who;


    // ============================================= local method

    /**
     * 获取指定xml文件的第一个指定节点名称的节点的信息
     * @param beanId
     * @param path
     * @return 子节点的名称和内部text
     */
    public Map<String, String> getXmlMap(String beanId,String path){

        DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
        try {
            Map<String, String> map = new HashMap<String, String>();
            DocumentBuilder db = documentbuilderfactory.newDocumentBuilder();
            String xmlPath = path;
            InputStream is = new FileInputStream(xmlPath);
            org.w3c.dom.Document document = db.parse(is);
            org.w3c.dom.Element element = document.getDocumentElement();
            NodeList beans = element.getElementsByTagName(beanId);
            Element beanElement = (Element) beans.item(0);
            NodeList childNodes = beanElement.getChildNodes();
            for(int i=0;i<childNodes.getLength();i++){
                if(childNodes.item(i).getNodeType()== Node.ELEMENT_NODE){
                    map.put(childNodes.item(i).getNodeName(), childNodes.item(i).getFirstChild().getNodeValue());
                }
            }
            return map;
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return null;
    }

    // ============================================= private method

}
