package org.freecode.paradigmirc;

import java.io.IOException;
import java.net.Socket;

public class Test {
  public static void main(String[] args) {
    Socket sock = null;
    try {
      sock = new Socket("irc.rizon.net", 6667);
      IrcConnection ic = new IrcConnection(sock);
      ic.setIrcListener(new CLIIrcListener());
      IrcCommand.NICK.format("mlaux95").send(ic);
      IrcCommand.USER.format("test", "test user").send(ic);
      ic.processStuff();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (sock != null) {
        try {
          sock.close();
        } catch (Exception e) {
        }
      }
    }
  }
}
