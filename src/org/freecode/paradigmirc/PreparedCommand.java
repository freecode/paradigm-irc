package org.freecode.paradigmirc;

public class PreparedCommand {
  private String command;

  public PreparedCommand(String command) {
    this.command = command;
  }

  public void send(IrcConnection connection) {
    connection.getWriter().write(command + "\r\n");
    connection.getWriter().flush();
  }
}
