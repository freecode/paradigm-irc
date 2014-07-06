package org.freecode.paradigmirc;

/**
 * Created by mlaux on 7/5/14.
 */
public class IncomingCommand {
	private IrcCommand type;

	private int numeric;
	private String from;
	private String target;
	private String content;

	private String[] extras;

	private IncomingCommand() { }

	public IrcCommand getType() {
		return type;
	}

	public int getNumeric() {
		return numeric;
	}

	public String getFrom() {
		return from;
	}

	public String getTarget() {
		return target;
	}

	public String getContent() {
		return content;
	}

	public String[] getExtras() {
		return extras;
	}

	public static class Builder {
		private IncomingCommand command;

		public Builder() {
			command = new IncomingCommand();
		}

		public void setType(IrcCommand type) {
			command.type = type;
		}

		public void setNumeric(int numeric) {
			command.numeric = numeric;
		}

		public void setFrom(String from) {
			command.from = from;
		}

		public void setTarget(String target) {
			command.target = target;
		}

		public void setContent(String content) {
			command.content = content;
		}

		public void setExtras(String... extras) {
			command.extras = extras;
		}

		public IncomingCommand get() {
			return command;
		}
	}
}
