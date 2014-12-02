package com.soen6441.ui.scene;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.SwingConstants;

import com.soen6441.core.log.Log;
import com.soen6441.core.play.Play;
import com.soen6441.ui.parallel.Button;
import com.soen6441.ui.parallel.Label;
import com.soen6441.ui.parallel.View;

/**
  * @ author Zhe Zhao
  */
public class RankingScene extends View {

	/*
	 * Mark - Context - Properties
	 */

	private Play play;
	
	/*
	 * Mark - View - Properties
	 */
	
	private Label rankingLabel;
	private Label mapLabel;
	
	private List<Label> numberLabels;
	private List<Label> nameLabels;
	private List<Label> scoreLabels;
	
	private Button nextButton;
	private Button backButton;
	

	/*
	 * Mark - View - Life Cycle
	 */
	
	protected void init() {
		super.init();
		play = Play.currentPlay();
		
		numberLabels = new ArrayList<Label>();
		nameLabels = new ArrayList<Label>();
		scoreLabels = new ArrayList<Label>();
	}
	
	@Override
	protected void initSubviews() {
		super.initSubviews();

		this.setBackground(new Color(0x242424));
		
		rankingLabel = new Label();
		rankingLabel.setText("Ranking");
		rankingLabel.setFontSize(40);
		rankingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rankingLabel.setSize(800, 60);
		rankingLabel.setLocation(0, 60);
		this.add(rankingLabel);
		
		mapLabel = new Label();
		String mapFileName = play.getMapFilePath();
		String mapName = mapFileName.substring(0, mapFileName.indexOf('.'));
		mapLabel.setText(mapName);
		mapLabel.setFontSize(20);
		mapLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mapLabel.setSize(800, 60);
		mapLabel.setLocation(0, 100);
		this.add(mapLabel);
		

		nextButton = new Button();
		nextButton.setTitle("Start");
		nextButton.setLocation(300, 450);
		nextButton.setSize(200, 40);
		this.add(nextButton);
		

		backButton = new Button();
		backButton.setTitle("Back");
		backButton.setSize(60, 20);
		backButton.setLocation(10, 550);
		this.add(backButton);
	}
	
	@Override
	protected void initEvents() {
		super.initEvents();
		
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayingScene scene = new PlayingScene();
				RankingScene.this.viewFlow.push(scene);
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RankingScene.this.viewFlow.pop();
			}
		});
	}
	
	@Override
	protected void viewDidDisplay() {
		super.viewDidDisplay();
		refreshRanking();
	}
	
	private void refreshRanking() {
		
		// clear old
		for (Label label : numberLabels) {
			this.remove(label);
		}
		for (Label label : nameLabels) {
			this.remove(label);
		}
		for (Label label : scoreLabels) {
			this.remove(label);
		}
		
		Play originalPlay = play.getOriginalPlay();
		
		List<Log> records = originalPlay.getMap().getLogger().getLogs(Log.CATEGORY_MAP, "Won");
		Collections.sort(records, new Comparator<Log>(){
			@Override
			public int compare(Log log1, Log log2) {
				return scoreFromLog(log2) - scoreFromLog(log1);
			}
		});
		
		int toIndex = records.size() > 5 ? 5 : records.size();
		records = records.subList(0, toIndex);
		
		for (int i = 0 ; i < records.size() ; i ++) {
			Log log = records.get(i);
			
			Label numberLabel = new Label();
			numberLabel.setText((i + 1) + "");
			numberLabel.setFontSize(20);
			numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
			numberLabel.setSize(40, 40);
			numberLabel.setLocation(200, 160 + i * 50);
			this.add(numberLabel);
			numberLabels.add(numberLabel);
			
			Label nameLabel = new Label();
			nameLabel.setText(playerNameFromLog(log));
			nameLabel.setFontSize(20);
			nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
			nameLabel.setSize(200, 40);
			nameLabel.setLocation(280, 160 + i * 50);
			this.add(nameLabel);
			nameLabels.add(nameLabel);

			Label scoreLabel = new Label();
			scoreLabel.setText(scoreFromLog(log) + "");
			scoreLabel.setFontSize(20);
			scoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			scoreLabel.setSize(40, 40);
			scoreLabel.setLocation(560, 160 + i * 50);
			this.add(scoreLabel);
			scoreLabels.add(scoreLabel);
			
			if (playerNameFromLog(log).equals(play.getPlayerName())) {
				numberLabel.setForeground(new Color(0xFFFFFF));
				nameLabel.setForeground(new Color(0xFFFFFF));
				scoreLabel.setForeground(new Color(0xFFFFFF));
			}
		}
		
	}
	
	/**
	 * Method scoreFromLog.
	 * @param log Log
	 * @return int
	 */
	private int scoreFromLog(Log log) {
		String[] values = log.getValue().split("\\|");
		if (values.length > 1) {
			return Integer.valueOf(values[1]);
		} else {
			return Integer.valueOf(values[0]);
		}
	}
	
	/**
	 * Method playerNameFromLog.
	 * @param log Log
	 * @return String
	 */
	private String playerNameFromLog(Log log) {
		String[] values = log.getValue().split("\\|");
		if (values.length > 1) {
			return values[0];
		} else {
			return "No Name";
		}
	}
}
