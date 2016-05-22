
public class TestingPad {
	public static void main(String[] args) throws InterruptedException {
		int count = 0;
		while (true) {
			count = ++count % 10;
			System.out.println(count);
			Thread.sleep(1000);
		}
	}
}