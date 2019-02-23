package com.danya140.raspberryhomekit.Utils;

import com.danya140.raspberryhomekit.models.SeriesNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlHelper {

    /**
     * Класс с названиями элементов в xml
     */
    private static class NodeNames {
        public static final String SERIES_NODE_NAME = "series";

        public static final String SERIES_LINK_NAME = "link";

        public static final String SERIES_SEASON_NAME = "current_season";

        public static final String SERIES_EPISODE_NAME = "current_episode";

        public static final String SERIES_NAME = "name";
    }

    /**
     * Название конфигурационного файла
     */
    private String configFile;

    /**
     * Документ. Файл после парсинга
     */
    private Document document;

    /**
     * Конструктор
     *
     * @param configFile название конфигурационного файла
     */
    public XmlHelper(String configFile) {
        this.configFile = configFile;
    }

    /**
     * Получение документа
     */
    private void read() {
        try {
            File fXmlFile = new File(configFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            document = doc;
        } catch (Exception e) {
            //TODO
        }
    }

    /**
     * Считывает конфигурационный файл с сериалами
     *
     * @return список всех сериалов
     */
    public List<SeriesNode> getAllSeriesFromConfig() {
        List<SeriesNode> result = new ArrayList<>();

        read();

        NodeList list = document.getElementsByTagName(NodeNames.SERIES_NODE_NAME);
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            SeriesNode seriesNode = new SeriesNode();

            String name = element.getAttribute(NodeNames.SERIES_NAME);
            String link = element.getElementsByTagName(NodeNames.SERIES_LINK_NAME).item(0).getTextContent();
            String currentEpisode = element.getElementsByTagName(NodeNames.SERIES_EPISODE_NAME).item(0).getTextContent();
            String currentSeason = element.getElementsByTagName(NodeNames.SERIES_SEASON_NAME).item(0).getTextContent();

            seriesNode.setName(name);
            seriesNode.setLink(link);
            seriesNode.setCurrentEpisode(Integer.parseInt(currentEpisode));
            seriesNode.setCurrentSeason(Integer.parseInt(currentSeason));

            result.add(seriesNode);
        }
        return result;
    }
}
