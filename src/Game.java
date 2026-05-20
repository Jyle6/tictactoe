import org.jline.terminal.TerminalBuilder;

void main() throws IOException {
	var terminal = TerminalBuilder.terminal();
	terminal.enterRawMode();
	var stream = terminal.input();
	stream.read();
}