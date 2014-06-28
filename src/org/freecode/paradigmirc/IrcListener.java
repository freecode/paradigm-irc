package org.freecode.paradigmirc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IrcListener {
  private static final Map<IrcCommand, Class<?>[]> argTypes;
  static {
    argTypes = new HashMap<IrcCommand, Class<?>[]>();

    Method[] methods = IrcListener.class.getDeclaredMethods();
    for(Method method : methods) {
      if(!method.getName().startsWith("on")) {
        continue;
      }

      IrcCommand command = IrcCommand.get(method.getName().substring(2));
      argTypes.put(command, method.getParameterTypes());
    }
  }

  public static Class<?>[] getArgTypes(IrcCommand command) {
    return argTypes.get(command);
  }

  public void onPing(IrcConnection connection, String server) { }

  public void onPrivmsg(IrcConnection connection, String target, String message) { }

  //public void onNumeric(IrcConnection connection, int numeric, String target, String content) { }
}
