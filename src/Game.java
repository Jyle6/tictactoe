import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import static org.jline.utils.InfoCmp.Capability;

Terminal terminal;
InputStream input;
PrintWriter output;
int start;
boolean first = false;

boolean ask_yn(String prompt) throws IOException {
	output.print(prompt + " [Y/n]");
	output.flush();
	terminal.puts(Capability.cursor_left);
	terminal.puts(Capability.cursor_left);
	terminal.puts(Capability.cursor_left);
	terminal.puts(Capability.cursor_left);
	terminal.puts(Capability.cursor_left);
	var is_no = input.read() == 'n';
	output.print(is_no ? "no   " : "yes  ");
	output.flush();
	return is_no;
}

void main() throws IOException {
	terminal = TerminalBuilder.terminal();
	terminal.enterRawMode();
	input = terminal.input();
	output = terminal.writer();
	start = terminal.getHeight();
	boolean selected_human = ask_yn("Against a human?");
	if (!selected_human) {
		first = ask_yn("\nIs CPU first?");
	}
	output.println("Initializing Tic-Tac-Toe board...");
	start = terminal.getHeight();
	var board = new int[9]; // 2D arrays are expensive in terms of memory and performance and cannot be easily checked by CPU
	while (true) {
		//break;
	}
	//terminal.close();
}