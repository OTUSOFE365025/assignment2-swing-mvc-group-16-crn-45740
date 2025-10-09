// This window emulates the scanning of an item. Every time the buttom is pressed
// it will send a notification of a UPC code

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Scanner {
	// Scanner uses Swing framework to create a UPC code
	private JFrame frame;
	private JPanel scannerPanel;
	private JButton scanButton;
	private List<ScanListener> listeners = new ArrayList<>();
	private CashRegister cashRegister; // optional, for random UPC selection

	public Scanner() {
		this(null);
	}

	public Scanner(CashRegister register) {
		this.cashRegister = register;
		frame = new JFrame("Scanner");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(120, 100);
		frame.setLocation(300,50);
		frame.setVisible(true);

		// Create UI elements
		scanButton = new JButton("Scan");
		scannerPanel = new JPanel();

		// Add UI element to frame
		scannerPanel.add(scanButton);
		frame.getContentPane().add(scannerPanel);

		scanButton.addActionListener(e -> {
			String upc = generateUPC();
			if (upc != null) {
				for (ScanListener l : listeners) l.onScan(upc);
			}
		});
	}

	public void addScanListener(ScanListener l) { listeners.add(l); }

	public void removeScanListener(ScanListener l) { listeners.remove(l); }

	private String generateUPC() {
		if (cashRegister != null) {
			String upc = cashRegister.getRandomUpc();
			System.out.println("Scanned UPC: " + upc);
			return upc;
		}
		String upc = "12345";
		System.out.println(upc);
		return upc;
	}

	public JFrame getFrame() { return frame; }
	public void setFrame(JFrame frame) { this.frame = frame; }
	public JPanel getScannerPanel() { return scannerPanel; }
	public void setScannerPanel(JPanel scannerPanel) { this.scannerPanel = scannerPanel; }
	public JButton getScanButton() { return scanButton; }
	public void setScanButton(JButton scanButton) { this.scanButton = scanButton; }
}
