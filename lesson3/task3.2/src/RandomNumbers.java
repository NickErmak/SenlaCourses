import java.util.Random;

public class RandomNumbers implements IRandomNumbers{

	private int[] numbers;

	public RandomNumbers(int quantity) {
		numbers = new int[quantity];
		for (int i = 0; i < quantity; i++)
			numbers[i] = getRandomNumber();
	}

	@Override
	public void showNumbers() {
		System.out.println("Auto-generated 3-digit numbers:");
		for (int number : numbers)
			System.out.println(number);
	}

	@Override
	public void showSum() {
		int firstDigitSum = 0;
		for (int number : numbers)
			firstDigitSum += number / 100;
		System.out.println("\nFirst digit summary = "+firstDigitSum);
	}

	private int getRandomNumber() {
		return new Random().nextInt(900) + 100;
	}
}
