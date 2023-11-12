package initGAME;

public class CustomSleep {

    public static void sleep(long milisseconds) {
        try {
            Thread.sleep(milisseconds);
        } catch (InterruptedException e) {
            //
        }
    }
}
