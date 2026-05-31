import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import static org.jline.utils.InfoCmp.Capability;

Terminal terminal;
InputStream input;
PrintWriter output;
boolean first = false;
// 2D arrays are expensive in terms of memory and performance and cannot be easily checked by CPU
char[] board = "123456789".toCharArray();
char player = 'O';
boolean playHuman;

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
	init();
	configureGame();
	output.print('\n');
	while (true) {
		printBoardState();
		if (first && player == 'O') cpu();
		else human();
		player = player == 'O' ? 'X' : 'O';
		terminal.puts(Capability.clear_screen);
	}
	//terminal.close();
}

private void human() throws IOException {
	while (true) {
		var in = input.read() - '1';
		if (in < 0 || in > 9 || board[in] == 'X' || board[in] == 'O') {
			output.print("\nillegal");
			output.flush();
			continue;
		}
		board[in] = player;
		break;
	}
}

void cpu() throws IOException {
}

void printBoardState() {
	output.println(player + "'s turn!");
	output.write(board, 0, 3);
	output.print('\n');
	output.write(board, 3, 3);
	output.print('\n');
	output.write(board, 6, 3);
	output.flush();
}

void configureGame() throws IOException {
	playHuman = ask_yn("Against a human?");
	if (!playHuman) {
		first = ask_yn("\nIs CPU first?");
	}
}

void init() throws IOException {
	terminal = TerminalBuilder.terminal();
	terminal.enterRawMode();
	input = terminal.input();
	output = terminal.writer();
}