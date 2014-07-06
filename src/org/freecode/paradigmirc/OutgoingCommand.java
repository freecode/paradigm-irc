package org.freecode.paradigmirc;

public class OutgoingCommand {
	private String command;

	public OutgoingCommand(String command) {
		this.command = command;
	}

	public void send(IrcConnection connection) {
		connection.getWriter().write(command + "\r\n");
		connection.getWriter().flush();
	}
}
