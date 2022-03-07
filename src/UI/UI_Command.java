package UI;

public class UI_Command {
	public int id;
	public String text;
	public String hint;
	public String hotkey;
	public String command;
	public String param;
	
	public UI_Command(int id, String text, String hint, String hotkey, String command, String param) {
		this.id = id;
		this.text = text;
		this.hint = hint;
		this.hotkey = hotkey;
		this.command = command;
		this.param = param;
	}

	public UI_Command(UI_Command cmd) {
		this.id = cmd.id;
		this.text = cmd.text;
		this.hint = cmd.hint;
		this.hotkey = cmd.hotkey;
		this.command = cmd.command;
		this.param = cmd.param;
	}
}
