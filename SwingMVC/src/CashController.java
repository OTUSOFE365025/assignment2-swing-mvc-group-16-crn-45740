public class CashController implements ScanListener {
    private CashRegister register;
    private Display display;

    public CashController(CashRegister register, Display display) {
        this.register = register;
        this.display = display;
    }

    @Override
    public void onScan(String upc) {
        Product p = register.addByUpc(upc);
        if (p != null) {
            display.addItem(p.toString());
            display.setSubtotal(register.getSubtotal());
        } else {
            display.addItem(upc + " (unknown)");
        }
    }
}
