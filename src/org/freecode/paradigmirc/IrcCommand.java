package org.freecode.paradigmirc;

public enum IrcCommand {
	PONG("PONG %s", IrcCommand.OUTGOING),
	NICK("NICK %s", IrcCommand.OUTGOING),
	USER("USER %s * * :%s", IrcCommand.OUTGOING),
	JOIN("JOIN %s", IrcCommand.OUTGOING),
	PRIVMSG("PRIVMSG %s :%s", IrcCommand.OUTGOING),
	NOTICE("NOTICE %s :%s", IrcCommand.OUTGOING),

	PING("PING", IrcCommand.INCOMING);

	private static final int OUTGOING = 0;
	private static final int INCOMING = 1;

	private int type;
	private String format;

	IrcCommand(String format, int type) {
		this.type = type;
		this.format = format;
	}

	public PreparedCommand format(String... args) {
		if (type == INCOMING) {
			throw new IllegalArgumentException();
		}
		return new PreparedCommand(String.format(format, args));
	}

	public static IrcCommand get(String command) {
		command = command.toUpperCase();
		return valueOf(command);
	}
}
