package com.danya140.raspberryhomekit.Utils;

import com.danya140.raspberryhomekit.models.Episode;
import com.turn.ttorrent.client.CommunicationManager;
import com.turn.ttorrent.client.PeerInformation;
import com.turn.ttorrent.client.PieceInformation;

import java.io.File;
import java.net.InetAddress;
import java.util.Collections;
import java.util.concurrent.Executors;

public class TorrentHelper {

    public static final String DOWNLOAD_FOLDER = "downloads";

    public void startDownloadAsync(Episode episode) {

        try {
            InetAddress address = InetAddress.getLocalHost();
            CommunicationManager communicationManager = new CommunicationManager(Executors.newFixedThreadPool(20), Executors.newFixedThreadPool(20));
            communicationManager.start(address);
            com.turn.ttorrent.client.TorrentListener tl = new TorrentListener();
            File folder = new File(DOWNLOAD_FOLDER + "\\" + episode.getSeriesName());
            if (!folder.exists()) {
                folder.mkdir();
            }

            communicationManager.addTorrent(episode.getFileUri(), folder.getPath(), Collections.singletonList(tl));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //TODO сделать очистку торрентов
    public class TorrentListener implements com.turn.ttorrent.client.TorrentListener {
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
            System.out.println();
        }

        @Override
        public void pieceReceived(PieceInformation pieceInformation, PeerInformation peerInformation) {
            System.out.println();

        }

        @Override
        public void downloadFailed(Throwable cause) {
            System.out.println();

        }

        @Override
        public void validationComplete(int validpieces, int totalpieces) {

        }
    }
}
