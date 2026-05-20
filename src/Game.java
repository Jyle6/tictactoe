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
		terminal.writer().print(selected_human ? ">Human\n CPU" : " Human\n>CPU");
		output.flush();
	}
	terminal.close();
}