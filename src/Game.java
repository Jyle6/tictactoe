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
		if (first && player == 'O' ? cpu() : human()) {
			terminal.puts(Capability.clear_screen);
			output.println(player + " is the WINNER!!!");
			output.flush();
			break;
		}
		player = player == 'O' ? 'X' : 'O';
		terminal.puts(Capability.clear_screen);
	}
	terminal.close();
}

boolean insert(char player, int idx) {
	board[idx] = player;
	return is_win(player);
}

boolean human() throws IOException {
	while (true) {
		var in = input.read() - '1';
		if (in < 0 || in > 8 || board[in] == 'X' || board[in] == 'O') {
			output.print("\nillegal");
			output.flush();
			continue;
		}
		return insert(player, in);
	}
}

boolean cpu() throws IOException {
	return false;
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

boolean is_win(char player){return board[0]==player&&board[1]==player&&board[2]==player||board[3]==player&&board[4]==player&&board[5]==player||board[6]==player&&board[7]==player&&board[8]==player||board[0]==player&&board[3]==player&&board[6]==player||board[1]==player&&board[4]==player&&board[7]==player||board[2]==player&&board[5]==player&&board[8]==player||board[0]==player&&board[4]==player&&board[8]==player||board[2]==player&&board[4]==player&&board[6]==player;}