package com.danya140.raspberryhomekit.Utils;

import com.turn.ttorrent.client.*;

import java.net.InetAddress;
import java.util.Collections;
import java.util.concurrent.Executors;

public class TorrentHelper {

    public void startDownloadAsync(String torrentFilePath, String downloadPath){
        SimpleClient client = new SimpleClient();

        try {
            InetAddress address = InetAddress.getLocalHost();
            CommunicationManager communicationManager = new CommunicationManager(Executors.newFixedThreadPool(10), Executors.newFixedThreadPool(10));
            communicationManager.start(address);
            com.turn.ttorrent.client.TorrentListener tl = new TorrentListener();
            communicationManager.addTorrent("D:\\Torrent's\\cleaner.torrent", "D:\\Torrent's", Collections.singletonList(tl));
            //client.downloadTorrentAsync("D:\\Torrent's\\cleaner.torrent", "D:\\Torrent's", address);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class TorrentListener implements com.turn.ttorrent.client.TorrentListener{
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
