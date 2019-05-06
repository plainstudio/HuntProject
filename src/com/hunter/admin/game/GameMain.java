package com.hunter.admin.game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.hunter.admin.Main;

public class GameMain extends JPanel{
	Main main;
	public GameMain(Main main) {
		this.main=main;
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(1400, 900));
	}

}
