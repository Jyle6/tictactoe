import org.jline.terminal.TerminalBuilder;
import static org.jline.utils.InfoCmp.Capability;

void main() throws IOException {
	var terminal = TerminalBuilder.terminal();
	terminal.enterRawMode();
	var input = terminal.input();
	var output = terminal.writer();
	var start = terminal.getHeight();
	output.print("Play against:\n>Human\n CPU");
	output.flush();
	boolean selected_human = true;
	while (input.read() != '\r') {
		selected_human = !selected_human;
		terminal.puts(Capability.cursor_address, start - 3, 0);
		output.println("Play against: ");
		output.print(selected_human ? ">Human\n CPU" : " Human\n>CPU");
		output.flush();
	}
	boolean first = false;
	if (!selected_human) {
		output.print("\nIs CPU First? [y/n]");
		output.flush();
		first = input.read() == 'n';
		terminal.puts(Capability.cursor_left);
		terminal.puts(Capability.cursor_left);
		terminal.puts(Capability.cursor_left);
		terminal.puts(Capability.cursor_left);
		terminal.puts(Capability.cursor_left);
		output.print(first ? "no   " : "yes  ");
		output.flush();
	}
	//output.println("Initializing Tic-Tac-Toe board...");
	start = terminal.getHeight();
	var board = new int[9]; // 2D arrays are expensive in terms of memory and performance and cannot be easily checked by CPU
	while (true) {
		//break;
	}
	//terminal.close();
}