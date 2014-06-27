package org.freecode.paradigmirc;

public class CLIIrcListener extends IrcListener {
  @Override
  public void onPING(IrcConnection connection, String server) {
    IrcCommand.PONG.format(server).send(connection);
  }
}
