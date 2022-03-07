package UI;

import java.io.InputStream;

import javafx.scene.image.Image;
import runtime.Runtime;

public abstract class UI {
	public UI_Menu menu;
	public UI_Toolbar toolbar;
	public UI_Commands commands;
	public int ui_id;
	public Image images;
	
     /**
     * Get UI's id by its name.
     * @return null if operation failed
     */
	public abstract int GetUiIdByName(String ui_name);
	
    /**
     * Get Commands by UI's id.
     * @return null if operation failed
     */
 	protected abstract UI_Commands GetCommands(int ui_id);

    /**
     * Get Menu by UI's id.
     * @return null if operation failed
     */
 	protected abstract UI_Menu GetMenu(int ui_id);

    /**
     * Get Toolbar by UI's id.
     * @return null if operation failed
     */
 	protected abstract UI_Toolbar GetToolbar(int ui_id);

 	/**
    * Load UI by its name.
    * @return null if operation failed
    */
	public boolean Load(String ui_name) {
		ui_id = GetUiIdByName(ui_name);
		if (ui_id < 0) {
			return false;
		}

		commands = GetCommands(ui_id);
		if (commands.parent_id > 0)
			ui_id=commands.parent_id;
		menu = GetMenu(ui_id);
		toolbar = GetToolbar(ui_id);

		// load images
		InputStream input = Runtime.class.getResourceAsStream("/Resources/Toolbars/Toolbar"+String.valueOf(ui_id)+".png");
		if (input != null) {
			images = new Image(input);
		} else {
			images = null;
		}

		return true;
	}

    /**
     * Get a Command by UI's name and command's id.
     * @return null if operation failed
     */
	public UI_Command GetCommand(int ui_id, int cmd_id) {
		UI_Commands commands = GetCommands(ui_id);
		if (commands == null) {
			return null;
		}
		return commands.Commands.get(cmd_id);
	}
	
    /**
     * Get a Command by UI's name and command's name.
     * @return null if operation failed
     */
	public UI_Command GetCommand(int ui_id, String command, String param) {
		UI_Commands commands = GetCommands(ui_id);
		if (commands == null) {
			return null;
		}
		return commands.Commands.values().stream().
				filter(cmd -> cmd.command.compareToIgnoreCase(command)==0).
				filter(cmd -> cmd.param.compareToIgnoreCase(param)==0).
				findFirst().orElse(null);
	}

}
