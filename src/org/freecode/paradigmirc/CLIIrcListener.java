package org.freecode.paradigmirc;

public class CLIIrcListener extends IrcListener {
	@Override
	public void onPING(IrcConnection connection, IncomingCommand command) {
		super.onPING(connection, command);
	}

	@Override
	public void onPRIVMSG(IrcConnection connection, IncomingCommand command) {
		super.onPRIVMSG(connection, command);

	}

	@Override
	public void onNUMERIC(IrcConnection connection, IncomingCommand command) {
		super.onNUMERIC(connection, command);
	}
}
