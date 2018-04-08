using System;

namespace IsbnValidator
{
    public class Program
    {
        public static bool IsValidIsbn(string isbn)
        {
            // Check empty.
            if (string.IsNullOrEmpty(isbn))
            {
                return false;
            }

            // Check length.
            int len = isbn.Length;

            if (len != 10 && len != 13)
            {
                return false;
            }

            // Check the 9 digits and dashes.
            int numDashesLeft = (len == 13) ? 3 : 0;
            int digitsIndex = 0;
            int checkSum = 0;
            int last = len - 1;

            // Don't check the last digit because we will check it later.
            for (int i = 0; i < last; i++)
            {
                char c = isbn[i];

                if (c == '-')
                {
                    if (numDashesLeft == 0 || i == 0 || isbn[i - 1] == '-')
                    {
                        return false;
                    }

                    numDashesLeft--;
                }
                else if (c >= '0' && c <= '9')
                {
                    digitsIndex++;
                    checkSum += digitsIndex * (c - '0');
                }
                else
                {
                    return false;
                }
            }

            if (numDashesLeft != 0 || digitsIndex != 9)
            {
                return false;
            }

            // Check the last digit.
            int mod = checkSum % 11;

            if (mod == 10)
            {
                if (isbn[last] != 'x' && isbn[last] != 'X')
                {
                    return false;
                }
            }
            else if (isbn[last] != (mod + '0'))
            {
                return false;
            }

            return true;
        }

        private static void TestIsbn(string isbn, bool expect)
        {
            bool result = IsValidIsbn(isbn);
            string isRight = (result == expect) ? "Correct" : "Wrong";

            Console.WriteLine($"{isbn} => {result} ({isRight})");
        }

        static void Main(string[] args)
        {
            TestIsbn(null, false);
            TestIsbn("", false);
            TestIsbn(" ", false);
            TestIsbn("123", false);
            TestIsbn("0-306-40615-2", true);
            TestIsbn("030-000-009-X", true);
            TestIsbn("0306406152", true);
            TestIsbn("0306406150002", false);
            TestIsbn("-0306-40615-2", false);
            TestIsbn("0--30640615-2", false);
        }
    }
}
