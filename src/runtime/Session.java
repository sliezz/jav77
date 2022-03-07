package runtime;

public class Session {
	public String login;
	public String user;
	public String ui;
	public String accessGroup;
	public String home;
	public String tempdir;
	public String rootdir;
	public String basedir;
	
	public boolean Signin()
	{
		// TODO
		login = "User";
		user = "Full User Name";
		ui = "Administrator";
		accessGroup = "Administrator";
		home = System.getProperty("user.home");
		tempdir = System.getProperty("java.io.tmpdir");
		rootdir = System.getProperty("java.home");
		basedir = Session.class.getProtectionDomain().getCodeSource().getLocation().getPath();

		return true;
	}
	
	public int sessionCount()
	{
		// TODO
		return 1;
	}
}
