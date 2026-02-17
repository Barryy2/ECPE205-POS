public class Main {
    public static void main(String[] args) {
        ProductScreen productScreen = new ProductScreen();
        CodeScreen codeScreen = new CodeScreen(productScreen.getProductList());
    }
}