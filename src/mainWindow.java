import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class mainWindow {

	protected Shell shell;
	private Text text;
	public String label="";

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			mainWindow window = new mainWindow();
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("VOICE MAIL");
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(49, 10, 230, 98);
		
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent button) {
				label+="1";
				lblNewLabel.setText(label);
			}
		});
		button.setBounds(48, 133, 75, 25);
		button.setText("1");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent button_1) {
				label+="2";
				lblNewLabel.setText(label);
			}
		});
		button_1.setBounds(129, 133, 75, 25);
		button_1.setText("2");
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent button_2) {
				label+="3";
				lblNewLabel.setText(label);
			}
		});
		button_2.setBounds(210, 133, 75, 25);
		button_2.setText("3");
		
		Button button_3 = new Button(shell, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent button_3) {
				label+="4";
				lblNewLabel.setText(label);
			}
		});
		button_3.setBounds(48, 167, 75, 25);
		button_3.setText("4");
		
		Button button_4 = new Button(shell, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent button_4) {
				label+="5";
				lblNewLabel.setText(label);
			}
		});
		button_4.setBounds(129, 167, 75, 25);
		button_4.setText("5");
		
		Button button_5 = new Button(shell, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent button_5) {
				label+="6";
				lblNewLabel.setText(label);
			}
		});
		button_5.setBounds(210, 164, 75, 25);
		button_5.setText("6");
		
		Button button_6 = new Button(shell, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent button_6) {
				label+="7";
				lblNewLabel.setText(label);
			}
		});
		button_6.setBounds(48, 198, 75, 25);
		button_6.setText("7");
		
		Button button_7 = new Button(shell, SWT.NONE);
		button_7.setBounds(129, 198, 75, 25);
		button_7.setText("8");
		
		Button button_8 = new Button(shell, SWT.NONE);
		button_8.setBounds(210, 195, 75, 25);
		button_8.setText("9");
		
		Button button_9 = new Button(shell, SWT.NONE);
		button_9.setBounds(210, 226, 75, 25);
		button_9.setText("#");
		
		Button btnHnagUp = new Button(shell, SWT.NONE);
		btnHnagUp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnHnagUp.setBounds(129, 226, 75, 25);
		btnHnagUp.setText("Colgar");
		
		Button btnContestar = new Button(shell, SWT.NONE);
		btnContestar.setBounds(48, 226, 75, 25);
		btnContestar.setText("Contestar");
		
		
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(298, 10, 126, 60);
		
		Button btnIngresarMensaje = new Button(shell, SWT.NONE);
		btnIngresarMensaje.setBounds(299, 83, 125, 25);
		btnIngresarMensaje.setText("Ingresar Mensaje");

	}
}
