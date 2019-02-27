package com.danya140.raspberryhomekit.Utils;

import com.danya140.raspberryhomekit.GUI.TrayMenu;
import com.danya140.raspberryhomekit.models.Episode;
import com.danya140.raspberryhomekit.scrappers.AbstractScrapper;
import com.turn.ttorrent.client.CommunicationManager;
import com.turn.ttorrent.client.FileMetadataProvider;
import com.turn.ttorrent.client.PeerInformation;
import com.turn.ttorrent.client.PieceInformation;
import com.turn.ttorrent.common.TorrentMetadata;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Сервис по работе закачке
 */
public class TorrentHelper {

    /**
     * Папка для загрузки
     */
    public static final String DOWNLOAD_FOLDER = "downloads";

    private CommunicationManager communicationManager;

    private XmlHelper xmlHelper;

    private Semaphore semaphore;

    private static TorrentHelper instance;

    private TorrentHelper() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            communicationManager = new CommunicationManager(Executors.newFixedThreadPool(20), Executors.newFixedThreadPool(20));
            communicationManager.start(address);
            xmlHelper = new XmlHelper(AbstractScrapper.CONFIG_FILE_NAME);
            //Разрешаем только 2 загрузки за раз
            semaphore = new Semaphore(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static TorrentHelper getInstance() {
        if (instance == null) {
            instance = new TorrentHelper();
        }
        return instance;
    }

    /**
     * Начать загрузку эпизода
     *
     * @param episode эпизод,который будем загружать
     */
    public void startDownloadAsync(Episode episode) {
        try {
            com.turn.ttorrent.client.TorrentListener tl = new TorrentListener(episode);
            File folder = new File(DOWNLOAD_FOLDER + "\\" + episode.getSeriesName());
            if (!folder.exists()) {
                folder.mkdir();
            }

            communicationManager.addTorrent(episode.getFileUri(), folder.getPath(), Collections.singletonList(tl));
            semaphore.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public CommunicationManager getCommunicationManager() {
        return communicationManager;
    }

    //TODO сделать очистку торрентов вынести в отдельный класс
    public class TorrentListener implements com.turn.ttorrent.client.TorrentListener {

        Episode episode;

        public TorrentListener(Episode episode) {
            this.episode = episode;
        }

        @Override
        public void peerConnected(PeerInformation peerInformation) {
        }

        @Override
        public void peerDisconnected(PeerInformation peerInformation) {

        }

        @Override
        public void pieceDownloaded(PieceInformation pieceInformation, PeerInformation peerInformation) {

        }

        @Override
        public void downloadComplete() {
            TrayMenu trayMenu = TrayMenu.getInstance();
            trayMenu.showDownloadCompleteNotification(episode);

            File file = new File(episode.getFileUri());
            FileMetadataProvider metadataProvider = new FileMetadataProvider(episode.getFileUri());
            xmlHelper.updateXml(episode);
            try {
                TorrentMetadata metadata = metadataProvider.getTorrentMetadata();
                communicationManager.removeTorrent(metadata.getHexInfoHash());
                file.deleteOnExit();
            } catch (IOException e) {
                e.printStackTrace();
            }
            semaphore.release();
        }

        @Override
        public void pieceReceived(PieceInformation pieceInformation, PeerInformation peerInformation) {

        }

        @Override
        public void downloadFailed(Throwable cause) {

        }

        @Override
        public void validationComplete(int validpieces, int totalpieces) {
            if (validpieces == totalpieces) semaphore.release();
        }
    }
}
