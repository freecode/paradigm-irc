package org.freecode.paradigmirc;

public class CLIIrcListener extends IrcListener {
	@Override
	public void onPing(IrcConnection connection, String server) {
		IrcCommand.PONG.format(server).send(connection);
	}
}
