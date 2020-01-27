import java.util.*;
import java.util.concurrent.atomic.*;

/*
Token Bucket

Sample output:
failed: get 500, elapsed 0, before 100, add 0, avail 100
failed: get 500, elapsed 357, before 100, add 357, avail 457
succeeded: get 500, elapsed 300, before 457, add 300, avail 257
succeeded: get 500, elapsed 304, before 257, add 304, avail 61
failed: get 500, elapsed 303, before 61, add 303, avail 364
succeeded: get 500, elapsed 303, before 364, add 303, avail 167
failed: get 500, elapsed 304, before 167, add 304, avail 471
succeeded: get 500, elapsed 303, before 471, add 303, avail 274
succeeded: get 500, elapsed 303, before 274, add 303, avail 77
failed: get 500, elapsed 302, before 77, add 302, avail 379
succeeded: get 500, elapsed 303, before 379, add 303, avail 182
failed: get 500, elapsed 304, before 182, add 304, avail 486
succeeded: get 500, elapsed 301, before 486, add 301, avail 287
succeeded: get 500, elapsed 304, before 287, add 304, avail 91
failed: get 500, elapsed 303, before 91, add 303, avail 394
succeeded: get 500, elapsed 303, before 394, add 303, avail 197
failed: get 500, elapsed 302, before 197, add 302, avail 499
succeeded: get 500, elapsed 303, before 499, add 303, avail 302
succeeded: get 500, elapsed 301, before 302, add 301, avail 103
failed: get 500, elapsed 306, before 103, add 306, avail 409
succeeded: get 500, elapsed 301, before 409, add 301, avail 210
succeeded: get 500, elapsed 301, before 210, add 301, avail 11
failed: get 500, elapsed 304, before 11, add 304, avail 315
succeeded: get 500, elapsed 304, before 315, add 304, avail 119
failed: get 500, elapsed 302, before 119, add 302, avail 421
succeeded: get 500, elapsed 303, before 421, add 303, avail 224
succeeded: get 500, elapsed 302, before 224, add 302, avail 26
failed: get 500, elapsed 302, before 26, add 302, avail 328
succeeded: get 500, elapsed 303, before 328, add 303, avail 131
failed: get 500, elapsed 302, before 131, add 302, avail 433
succeeded: get 500, elapsed 302, before 433, add 302, avail 235
succeeded: get 500, elapsed 303, before 235, add 303, avail 38
failed: get 500, elapsed 304, before 38, add 304, avail 342
succeeded: get 500, elapsed 302, before 342, add 302, avail 144
failed: get 500, elapsed 304, before 144, add 304, avail 448
succeeded: get 500, elapsed 305, before 448, add 305, avail 253
succeeded: get 500, elapsed 301, before 253, add 301, avail 54
failed: get 500, elapsed 304, before 54, add 304, avail 358
succeeded: get 500, elapsed 301, before 358, add 301, avail 159
failed: get 500, elapsed 303, before 159, add 303, avail 462
succeeded: get 500, elapsed 303, before 462, add 303, avail 265
succeeded: get 500, elapsed 302, before 265, add 302, avail 67
failed: get 500, elapsed 303, before 67, add 303, avail 370
succeeded: get 500, elapsed 303, before 370, add 303, avail 173
failed: get 500, elapsed 302, before 173, add 302, avail 475
succeeded: get 500, elapsed 305, before 475, add 305, avail 280
succeeded: get 500, elapsed 301, before 280, add 301, avail 81
failed: get 500, elapsed 304, before 81, add 304, avail 385
succeeded: get 500, elapsed 305, before 385, add 305, avail 190
failed: get 500, elapsed 301, before 190, add 301, avail 491
succeeded: get 500, elapsed 300, before 491, add 300, avail 291
succeeded: get 500, elapsed 304, before 291, add 304, avail 95
failed: get 500, elapsed 300, before 95, add 300, avail 395
succeeded: get 500, elapsed 301, before 395, add 301, avail 196
failed: get 500, elapsed 302, before 196, add 302, avail 498
succeeded: get 500, elapsed 302, before 498, add 302, avail 300
succeeded: get 500, elapsed 300, before 300, add 300, avail 100
failed: get 500, elapsed 301, before 100, add 301, avail 401
succeeded: get 500, elapsed 302, before 401, add 302, avail 203
succeeded: get 500, elapsed 302, before 203, add 302, avail 5
failed: get 500, elapsed 304, before 5, add 304, avail 309
succeeded: get 500, elapsed 302, before 309, add 302, avail 111
failed: get 500, elapsed 302, before 111, add 302, avail 413
succeeded: get 500, elapsed 303, before 413, add 303, avail 216
succeeded: get 500, elapsed 303, before 216, add 303, avail 19
failed: get 500, elapsed 301, before 19, add 301, avail 320
succeeded: get 500, elapsed 302, before 320, add 302, avail 122
failed: get 500, elapsed 302, before 122, add 302, avail 424
succeeded: get 500, elapsed 302, before 424, add 302, avail 226
succeeded: get 500, elapsed 302, before 226, add 302, avail 28
failed: get 500, elapsed 302, before 28, add 302, avail 330
succeeded: get 500, elapsed 303, before 330, add 303, avail 133
failed: get 500, elapsed 302, before 133, add 302, avail 435
succeeded: get 500, elapsed 302, before 435, add 302, avail 237
succeeded: get 500, elapsed 303, before 237, add 303, avail 40
failed: get 500, elapsed 303, before 40, add 303, avail 343
succeeded: get 500, elapsed 300, before 343, add 300, avail 143
failed: get 500, elapsed 302, before 143, add 302, avail 445
succeeded: get 500, elapsed 303, before 445, add 303, avail 248
succeeded: get 500, elapsed 302, before 248, add 302, avail 50
failed: get 500, elapsed 301, before 50, add 301, avail 351
succeeded: get 500, elapsed 304, before 351, add 304, avail 155
failed: get 500, elapsed 302, before 155, add 302, avail 457
succeeded: get 500, elapsed 304, before 457, add 304, avail 261
succeeded: get 500, elapsed 302, before 261, add 302, avail 63
failed: get 500, elapsed 303, before 63, add 303, avail 366
succeeded: get 500, elapsed 303, before 366, add 303, avail 169
failed: get 500, elapsed 301, before 169, add 301, avail 470
succeeded: get 500, elapsed 303, before 470, add 303, avail 273
succeeded: get 500, elapsed 303, before 273, add 303, avail 76
failed: get 500, elapsed 305, before 76, add 305, avail 381
succeeded: get 500, elapsed 303, before 381, add 303, avail 184
failed: get 500, elapsed 304, before 184, add 304, avail 488
succeeded: get 500, elapsed 302, before 488, add 302, avail 290
succeeded: get 500, elapsed 301, before 290, add 301, avail 91
failed: get 500, elapsed 300, before 91, add 300, avail 391
succeeded: get 500, elapsed 302, before 391, add 302, avail 193
failed: get 500, elapsed 302, before 193, add 302, avail 495
succeeded: get 500, elapsed 302, before 495, add 302, avail 297
succeeded: get 500, elapsed 303, before 297, add 303, avail 100
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("hello");

        new Thread(() -> {
            TokenBucket b = new TokenBucket(100, 1000, 5000);
            for (int i = 0; i < 100; i++) {
                b.get(500);
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class TokenBucket {

    private final long refreshRatePerSecond;
    private final long maxCapacity;

    private long numTokensAvailable;
    private long lastRefreshMillis;

    TokenBucket(long numTokensAvailable, long refreshRatePerSecond, long capacity) {
        this.numTokensAvailable = numTokensAvailable;
        this.refreshRatePerSecond = refreshRatePerSecond;
        this.maxCapacity = capacity;
        this.lastRefreshMillis = System.currentTimeMillis();
    }

    synchronized boolean get(int amount) {
        if (amount <= 0 || amount > maxCapacity) {
            throw new IllegalArgumentException("Token get amount out of range");
        }

        // Try to refill first.
        long nowMillis = System.currentTimeMillis();
        long elapsedMillis = nowMillis - lastRefreshMillis;
        long numTokensToAdd = elapsedMillis * refreshRatePerSecond / 1000;
        long beforeTokens = numTokensAvailable; // debug only

        numTokensAvailable = Math.min(maxCapacity, numTokensAvailable + numTokensToAdd);
        lastRefreshMillis = nowMillis;

        // Return the requested amount.
        if (numTokensAvailable < amount) {
            System.out.println("failed: get " + amount + ", elapsed " + elapsedMillis + ", before " + beforeTokens
                    + ", add " + numTokensToAdd + ", avail " + numTokensAvailable);
            return false;
        }

        numTokensAvailable -= amount;
        System.out.println("succeeded: get " + amount + ", elapsed " + elapsedMillis + ", before " + beforeTokens
                + ", add " + numTokensToAdd + ", avail " + numTokensAvailable);
        return true;
    }
}
