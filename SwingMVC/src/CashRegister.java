import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CashRegister {
    private Map<String, Product> products = new HashMap<>();
    private List<Product> scannedItems = new ArrayList<>();
    private Random rnd = new Random();

    public CashRegister() {
        // try to load products.txt from working directory or classpath
        File f = new File("products.txt");
        if (f.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String line;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#")) continue;
                    // expected: UPC Product Price
                    String[] parts = line.split("\\s+", 3);
                    if (parts.length >= 3) {
                        String upc = parts[0];
                        String name = parts[1];
                        String pricePart = parts[2].replace("$", "").trim();
                        try {
                            double price = Double.parseDouble(pricePart);
                            Product p = new Product(upc, name, price);
                            products.put(upc, p);
                        } catch (NumberFormatException e) {
                            // skip malformed price
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Product getProductByUpc(String upc) {
        return products.get(upc);
    }

    public List<String> getAllUpcs() {
        if (products.isEmpty()) return Collections.emptyList();
        return new ArrayList<>(products.keySet());
    }

    public Product addByUpc(String upc) {
        Product p = products.get(upc);
        if (p != null) {
            scannedItems.add(p);
        }
        return p;
    }

    public List<Product> getScannedItems() { return Collections.unmodifiableList(scannedItems); }

    public double getSubtotal() {
        double s = 0.0;
        for (Product p : scannedItems) s += p.getPrice();
        return s;
    }

    public String getRandomUpc() {
        List<String> upcs = getAllUpcs();
        if (upcs.isEmpty()) return null;
        return upcs.get(rnd.nextInt(upcs.size()));
    }
}
