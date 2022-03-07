package runtime;

public abstract class Form {
	int id;
	String name;
	String synonym;
	String comment;

	abstract public Value open(Value arg);
}
