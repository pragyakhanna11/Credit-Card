import java.util.*;
import java.lang.Math;

class Main {
  public static void main(String[] args) {
    // write code here
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the credit card number: ");
    long num = input.nextLong();
    if (isValid(num)){
      System.out.println(num + " is valid");
      if(prefixMatched(num, 4))
        System.out.println("It's a visa card");
      else if(prefixMatched(num, 5))
        System.out.println("It's a master card");
      else if(prefixMatched(num, 37))
        System.out.println("It's an American Express card");
      else if(prefixMatched(num, 6))
        System.out.println("It's a Discover card");
    }
    else
      System.out.println(num + " is not valid");
  }

  /** Returns true if the card number is valid */
  public static boolean isValid(long number) {
    int sum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
    return (getSize(number) >= 13 &&
            getSize(number) <= 16) &&
            (prefixMatched(number, 4) ||
              prefixMatched(number, 5) ||
              prefixMatched(number, 37) ||
              prefixMatched(number, 6)) &&
            (sum % 10 == 0);
  }

  /** Returns the sum of the doubled even-place digits, from right to left */
  public static int sumOfDoubleEvenPlace(long number) {
    int length = getSize(number);
    int even_sum = 0;
    for (int i = 1; i <= length; i++) {
      long p = number % 10;
      number = number / 10;
      int q = (int) p;
      int r = getDigit(2 * q);
      if (i % 2 == 0) {
        even_sum = even_sum + r;
      }
    }
    return even_sum;
  }

  /**
   * Returns the given number if it is a single digit, otherwise return the sum of
   * the two digits
   */
  public static int getDigit(int n) {
    int length = getSize(n);
    if (length != 1) {
      int p = n % 10;
      int q = n / 10;
      return p + q;
    } else
      return n;
  }

  /** Returns the sum of the odd-place digits, from right to left */
  public static int sumOfOddPlace(long number) {
    int length = getSize(number);
    int odd_sum = 0;
    for (int i = 1; i <= length; i++) {
      long p = number % 10;
      number = number / 10;
      int q = (int) p;
      if (i % 2 != 0) {
        odd_sum = odd_sum + q;
      }
    }
    return odd_sum;
  }

  /** Return true if d is a prefix for a number */
  public static boolean prefixMatched(long number, int d) {
    return getPrefix(number, getSize(d)) == d;
  }

  /** Returns the number of digits in the given number */
  public static int getSize(long number) {
    int length = String.valueOf(number).length();
    return length;
  }

  /**
   * Returns the first k number of digits from number. If the number of digits in
   * number is less than k, returns number.
   */
  public static long getPrefix(long number, int k) {
    {
      if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }
  }
}