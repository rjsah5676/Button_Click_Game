package button_click_game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ButtonGame extends JFrame {
	private static final int BUTTON = 3;
	private Button button_10 = new Button(10, "10score.png");
	private Button button_50 = new Button(50, "50score.png");
	private Button button_100 = new Button(100, "100score.png");
	
	
	private ImageIcon Icon10 = new ImageIcon(Main.class.getResource("../images/"+button_10.getScore()));
	private ImageIcon Icon50 = new ImageIcon(Main.class.getResource("../images/"+button_50.getScore()));
	private ImageIcon Icon100 = new ImageIcon(Main.class.getResource("../images/"+button_100.getScore()));
	
	private JButton scoreButton_10 = new JButton(Icon10);
	private JButton scoreButton_50 = new JButton(Icon50);
	private JButton scoreButton_100 = new JButton(Icon100);
	
	private JLabel title = new JLabel("버튼 클릭 게임!");
	private JLabel scoreShow = new JLabel("점수: ");
	private String score_str = "0";
	private JLabel score = new JLabel(score_str);
	private String time_str = "5초 후 시작합니다";
	private JLabel time = new JLabel(time_str);
	
	private JButton startButton = new JButton("시작하기");
	private JButton exitButton = new JButton("종료하기");
	
	private int buttonCount = 0;
	private int clickCount = 0;
	private int tiMe = 5;
	
	public ButtonGame() {
		setTitle("버튼 클릭 게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
			
		title.setSize(150, 20);
		title.setLocation(200, 20);
		add(title);
		
        scoreShow.setSize(40,20);
        scoreShow.setLocation(400,20);
        add(scoreShow);
        scoreShow.setVisible(false);
        
        score.setSize(50, 20);
        score.setLocation(450, 20);
        add(score);
        score.setVisible(false);
        
        time.setSize(200, 20);
        time.setLocation(200, 250);
        add(time);
        time.setVisible(false);
		
		startButton.setSize(100, 30);
		startButton.setLocation(120, 400);		
		add(startButton);
		
		exitButton.setSize(100,30);
		exitButton.setLocation(280,400);
		add(exitButton);
		
		setSize(500, 500);
		setVisible(true);
		
        Play play = new Play();
        ButtonMake(scoreButton_10, 50);
        ButtonMake(scoreButton_50, 30);
        ButtonMake(scoreButton_100, 20);

		startButton.addActionListener(new ActionListener()
	    {		
	      @Override 
	      public void actionPerformed(ActionEvent e) 
	      { 
	         title.setVisible(false);
	         startButton.setVisible(false);
	         exitButton.setVisible(false);  
	         play.start();
	         scoreShow.setVisible(true);
	         score.setVisible(true);
	        } 
	    });
		
		exitButton.addActionListener(new ActionListener()
	    {		
	      @Override 
	      public void actionPerformed(ActionEvent e) 
	      { 
	    	  System.exit(0);
    	  }
	    });
	}

	public void ButtonMake(JButton whatButton, int size) {		
		whatButton.setSize(size, size);
		whatButton.setLocation((int)(500*Math.random()), (int)(500*Math.random()));
		add(whatButton);
		whatButton.setVisible(false);
	}
	
	private void nextButton(JButton whatButton, int size) {
		int x = (int)(500*Math.random());
		int y = (int)(500*Math.random());
		while(x >= (500-size) || y>= (500-size) || y <= size) {
			x = (int)(500*Math.random());
			y = (int)(500*Math.random());
		}
		whatButton.setLocation(x, y);
		if(tiMe == 0) 
			whatButton.setVisible(true);
	}
	
	public void hideButton(JButton whatButton) {
		try {
			Thread.sleep(800);
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		} 
		whatButton.setVisible(false);
	}
	public class Play extends Thread {
		public void run() { 
			timeSet timeset = new timeSet();
			timeset.start();
			while (true) {
					if(Math.random() > 0.5) {
						nextButton(scoreButton_10, 50);
						hideButton(scoreButton_10);
						ButtonClick(scoreButton_10, 10);
					}
					else if(Math.random() > 0.2) {
						nextButton(scoreButton_50, 30);
						hideButton(scoreButton_50);
						ButtonClick(scoreButton_50, 50);
					}
					else {
						nextButton(scoreButton_100, 20);
						hideButton(scoreButton_100);
						ButtonClick(scoreButton_100, 100);
					}
			}
		}
	}
	public void ButtonClick(JButton whatButton, int up) {
		clickCount = 0;
		whatButton.addActionListener(new ActionListener()
	    {		
	      @Override 
	      public void actionPerformed(ActionEvent e) 
	      { 
	    	  if(clickCount == 0) {
	    		  clickCount++;
	    	  int score_int = 0;
	    	  score_int = Integer.parseInt(score_str) + up;
	    	  score_str = Integer.toString(score_int);
	    	  score.setText(score_str);
	    	  whatButton.setVisible(false);
	    	  }
	        } 
	    });
	}
	public class timeSet extends Thread {
		public void run() {
			time.setVisible(true);
			for(tiMe = 5; tiMe > 0; tiMe--) {
				try {
					Thread.sleep(1000);
					time_str = Integer.toString(tiMe - 1) + "초 후 시작합니다.";
					time.setText(time_str);
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				} 
			}
			time.setVisible(false);   
		}
	}
}