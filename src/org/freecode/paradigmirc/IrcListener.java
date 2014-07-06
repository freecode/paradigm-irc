package org.freecode.paradigmirc;

public class IrcListener {
	public void onPING(IrcConnection connection, IncomingCommand command) {
		System.out.println("PING from " + command.getContent());
	}

	public void onPRIVMSG(IrcConnection connection, IncomingCommand command) {
		System.out.println(command.getFrom() + " PRIVMSG " + command.getTarget() + ": " + command.getContent());
	}

	public void onNUMERIC(IrcConnection connection, IncomingCommand command) {
		System.out.println("Numeric " + command.getNumeric());
	}
}
