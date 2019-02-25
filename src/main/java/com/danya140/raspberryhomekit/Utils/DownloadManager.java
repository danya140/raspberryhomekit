package com.danya140.raspberryhomekit.Utils;

import com.danya140.raspberryhomekit.models.Episode;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.MessageFormat;

public class DownloadManager {

    public static final String TEMP_FOLDER = "tmp";

    /**
     * Скачивание торрент файла
     *
     * @param episode эпизод для которого скачивается торрент
     */
    public void download(Episode episode) {
        try {
            URL website = new URL(episode.getSeriesLink());
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());

            String fileUri = MessageFormat.format("{0}\\{1} S{2} E{3}.torrent",
                    TEMP_FOLDER,
                    episode.getSeriesName(),
                    episode.getSeriesSeason(),
                    episode.getSeriesEpisode());

            FileOutputStream fos = new FileOutputStream(fileUri);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            episode.setFileUri(fileUri);
        } catch (Exception e) {
            //TODO
        }
    }

    /**
     * Удаление файла торрента
     *
     * @param episode эпизод для которого нужно удалить торрент файл
     */
    public void cleanUp(Episode episode) {
        File file = new File(episode.getFileUri());
        file.deleteOnExit();
    }
}
