/*
Draw histogram on console:
Given a list of possibly negative integers, draw a histogram/barchart on the console output window. The bars should start from the x-axis and extend upwards.
*/

import java.util.*;

public class HistogramPrinter {
  public static void printHistogram(int[] yValues) {
    int maxY = 1;
    int minY = 1;
    
    for (int y: yValues) {
      maxY = Math.max(maxY, y);
      minY = Math.min(minY, y);
    }

    for (int y = maxY; y >= minY; y--) {
      for (int x = 0; x < yValues.length; x++) {
        int yv = yValues[x];
        if (yv == 0) {
          System.out.print(' ');
          continue;
        }

        int topY = (yv > 0) ? yv : -1;
        int bottomY = (yv > 0) ? 1 : yv;

        if (y <= topY && y >= bottomY) {
          System.out.print('*');
        } else {
          System.out.print(' ');
        }
      }

      System.out.println();
    }
  }
  
  public static void main(String[] args) {
    printHistogram(new int[] {1, 2, 3, 1, 6, -4, 3, 0, 0, -1});
  }
}
