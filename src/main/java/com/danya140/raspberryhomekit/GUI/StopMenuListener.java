package com.danya140.raspberryhomekit.GUI;

import com.danya140.raspberryhomekit.Utils.TorrentHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO подумать о целесообразности
public class StopMenuListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        TorrentHelper.getInstance().getCommunicationManager().stop();
    }
}
