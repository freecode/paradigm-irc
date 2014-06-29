package org.freecode.paradigmirc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;

public class IrcConnection {
	private Socket sock;

	private BufferedReader reader;
	private PrintWriter writer;

	private IrcListener listener;

	public IrcConnection(Socket sock) throws IOException {
		this.sock = sock;

		this.reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		this.writer = new PrintWriter(sock.getOutputStream());
	}

	public BufferedReader getReader() {
		return reader;
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public void setIrcListener(IrcListener listener) {
		this.listener = listener;
	}

	public void processStuff() {
		try {
			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}

				System.out.println("> " + line);
				processOneLine(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	private void processOneLine(String line) {
		int firstSpace = line.indexOf(' ');
		if (firstSpace == -1) {
			return;
		}

		int colon = line.indexOf(':', 1);
		String[] parts;

		String from = null, command = null, target = null;
		String longParam = null;

		if (colon != -1) {
			String shortLine = line.substring(0, colon);
			parts = shortLine.split(" ");
			if (colon == line.length() - 1) {
				longParam = "";
			} else {
				longParam = line.substring(colon + 1);
			}
		} else {
			parts = line.split(" ");
		}

		if (parts[0].startsWith(":")) {
			from = parts[0].substring(1);
		} else {
			command = parts[0];
		}

		if (parts.length > 1 && command == null) {
			command = parts[1];
		}

		if (parts.length > 2) {
			target = parts[2];
		}

		if (parts.length > 3) {
			String[] others = new String[parts.length - 3];
			System.arraycopy(parts, 3, others, 0, others.length);
		}

		System.out.println("from=" + from + ", command=" + command + ", target=" + target + ", longParam=" + longParam);

		if (listener == null) {
			return;
		}

		IrcCommand ic = IrcCommand.get(command);

		try {

			Class<?>[] types = IrcListener.getArgTypes(ic);
			Class<?>[] all = new Class[types.length + 1];
			all[0] = IrcListener.class;
			System.arraycopy(types, 0, all, 1, types.length);
			Method method = listener.getClass().getMethod("on" + ic.name(), all);
			method.invoke(listener);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
