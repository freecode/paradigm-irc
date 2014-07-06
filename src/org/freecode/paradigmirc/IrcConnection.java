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
		if (listener == null) {
			return;
		}

		int firstSpace = line.indexOf(' ');
		if (firstSpace == -1) {
			return;
		}

		int colon = line.indexOf(':', 1);
		String[] parts;

		IncomingCommand.Builder builder = new IncomingCommand.Builder();

		String command = null;

		if (colon != -1) {
			String shortLine = line.substring(0, colon);
			parts = shortLine.split(" ");
			if (colon == line.length() - 1) {
				builder.setContent("");
			} else {
				builder.setContent(line.substring(colon + 1));
			}
		} else {
			parts = line.split(" ");
		}

		if (parts[0].startsWith(":")) {
			builder.setFrom(parts[0].substring(1));
		} else {
			command = parts[0];
		}

		if (parts.length > 1 && command == null) {
			command = parts[1];
		}

		IrcCommand ic = IrcCommand.get(command);

		if(ic == null) {
			System.err.println("I don't know what " + command + " is");
			return;
		}

		builder.setType(ic);
		if(ic == IrcCommand.NUMERIC) {
			builder.setNumeric(Integer.parseInt(command));
		}

		if (parts.length > 2) {
			builder.setTarget(parts[2]);
		}

		if (parts.length > 3) {
			String[] others = new String[parts.length - 3];
			System.arraycopy(parts, 3, others, 0, others.length);
			builder.setExtras(others);
		}

		try {
			Class<?>[] types = { IrcConnection.class, IncomingCommand.class };
			Method method = listener.getClass().getMethod("on" + ic.name(), types);
			method.invoke(listener, this, builder.get());
		} catch (Exception e) {
			System.err.println("I couldn't find " + listener.getClass().getSimpleName() + ".on" + ic.name() + "()");
			// e.printStackTrace();
		}
	}
}
