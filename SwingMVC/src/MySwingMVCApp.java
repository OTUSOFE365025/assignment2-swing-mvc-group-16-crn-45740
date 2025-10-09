
public class MySwingMVCApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// For this assignment we wire the CashRegister MVC
		CashRegister register = new CashRegister();
		Display display = new Display("Cash Register Display");
		CashController cc = new CashController(register, display);

		// create scanner and register controller as listener
		Scanner scanner = new Scanner(register);
		scanner.addScanListener(cc);
        
		// keep the original simple MVC alive as well
		Model m = new Model("Sylvain", "Saurel");
		View v = new View("MVC with SSaurel");
		Controller c = new Controller(m, v);
		c.initController();
	}

}
