package button_click_game;

public class Button {
	private int score_num;
	private String score;
	public int getScore_num() {
		return score_num;
	}
	public void setScore_num(int score_num) {
		this.score_num = score_num;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Button(int score_num, String score) {
		super();
		this.score_num = score_num;
		this.score = score;
	}
}
