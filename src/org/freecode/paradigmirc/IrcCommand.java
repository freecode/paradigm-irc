package org.freecode.paradigmirc;

import java.util.HashSet;
import java.util.Set;

public enum IrcCommand {
	PONG("PONG %s", IrcCommand.OUTGOING),
	NICK("NICK %s", IrcCommand.OUTGOING),
	USER("USER %s * * :%s", IrcCommand.OUTGOING),
	JOIN("JOIN %s", IrcCommand.OUTGOING),
	PRIVMSG("PRIVMSG %s :%s", IrcCommand.OUTGOING),
	NOTICE("NOTICE %s :%s", IrcCommand.OUTGOING),

	PING("PING", IrcCommand.INCOMING),
	NUMERIC(null, IrcCommand.INCOMING);

	private static final int OUTGOING = 0;
	private static final int INCOMING = 1;

	private static final Set<String> validKeys = new HashSet<String>();
	static {
		for(IrcCommand cmd : values()) {
			validKeys.add(cmd.name());
		}
	}

	private int type;
	private String format;

	IrcCommand(String format, int type) {
		this.type = type;
		this.format = format;
	}

	public OutgoingCommand format(String... args) {
		if (type == INCOMING) {
			throw new IllegalArgumentException();
		}
		return new OutgoingCommand(String.format(format, args));
	}

	public static IrcCommand get(String command) {
		if(command.matches("\\d+")) {
			return NUMERIC;
		}
		command = command.toUpperCase();
		if(validKeys.contains(command)) {
			return valueOf(command);
		}
		return null;
	}
}
