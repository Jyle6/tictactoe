import org.jline.terminal.Attributes;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

void main() {
	Terminal terminal = null;
	try {
		terminal = TerminalBuilder.terminal();
	} catch (IOException e) {
		System.out.println("Cannot proceed: IOException, " + Arrays.toString(e.getStackTrace()));
		System.exit(1);
	}
}