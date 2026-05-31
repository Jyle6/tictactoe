import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import static org.jline.utils.InfoCmp.Capability;

Terminal terminal;
InputStream input;
PrintWriter output;
int start;
boolean first = false;
// 2D arrays are expensive in terms of memory and performance and cannot be easily checked by CPU
char[] board = "123456789".toCharArray();
char player = 'O';

boolean ask_yn(String prompt) throws IOException {
	output.print(prompt + " [y/n]");
	output.flush();
	terminal.puts(Capability.cursor_left);
	terminal.puts(Capability.cursor_left);
	terminal.puts(Capability.cursor_left);
	terminal.puts(Capability.cursor_left);
	terminal.puts(Capability.cursor_left);
	var is_yes = input.read() == 'y';
	output.print(is_yes ? "yes  " : "no   ");
	output.flush();
	return is_yes;
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
	while (true) {
		output.println(player + "'s turn!");
		output.write(board, 0, 3);
		output.print('\n');
		output.write(board, 3, 3);
		output.print('\n');
		output.write(board, 6, 3);
		output.flush();
		if (first && player == 'O') {
			// CPU
		} else if (player == 'X' || selected_human) {
			var in = input.read() - '1';
			if ((in >= 1 && in <= 9) || board[in] == 'X' || board[in] == 'O') {
				output.print("\nillegal move\n");
				continue;
			}
			board[in] = player;
		}
		player = player == 'O' ? 'X' : 'O';
	}
	//terminal.close();
}