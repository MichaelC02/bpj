import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Button;

public class UC1_Login {

	protected Shell shlBenutzerauthentifizierung;
	private Text text;
	private Text text_1;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UC1_Login window = new UC1_Login();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlBenutzerauthentifizierung.open();
		shlBenutzerauthentifizierung.layout();
		while (!shlBenutzerauthentifizierung.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlBenutzerauthentifizierung = new Shell();
		shlBenutzerauthentifizierung.setSize(330, 183);
		shlBenutzerauthentifizierung.setText("Benutzerauthentifizierung");
		
		text = new Text(shlBenutzerauthentifizierung, SWT.BORDER);
		text.setBounds(157, 31, 127, 19);
		
		text_1 = new Text(shlBenutzerauthentifizierung, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(157, 62, 127, 19);
		
		Label lblNewLabel = new Label(shlBenutzerauthentifizierung, SWT.NONE);
		lblNewLabel.setBounds(46, 34, 94, 14);
		lblNewLabel.setText("Benutzername");
		
		Label lblNewLabel_1 = new Label(shlBenutzerauthentifizierung, SWT.NONE);
		lblNewLabel_1.setBounds(46, 65, 94, 14);
		lblNewLabel_1.setText("Passwort");
		
		Button btnLogin = formToolkit.createButton(shlBenutzerauthentifizierung, "Login", SWT.NONE);
		btnLogin.setBounds(116, 107, 72, 22);

	}
}
