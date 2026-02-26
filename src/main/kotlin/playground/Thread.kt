package playground

import java.util.concurrent.CountDownLatch


object Main {
    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val numThreads = 1000
        var latch = CountDownLatch(numThreads)

        var start = System.currentTimeMillis()
        for (i in 0..<numThreads) {
            startThreads(latch)
        }
        latch.await() // wait for all threads to finish
        var end = System.currentTimeMillis()
        println("Time taken with regular threads: " + (end - start) + "ms")

        latch = CountDownLatch(numThreads) // reset the latch
        start = System.currentTimeMillis()
        for (i in 0..<numThreads) {
            startVirtualThreads(latch)
        }
        latch.await() // wait for all virtual threads to finish
        end = System.currentTimeMillis()
        println("Time taken with virtual threads: " + (end - start) + "ms")
    }

    private fun startThreads(latch: CountDownLatch) {
        Thread {
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                latch.countDown() // decrement the count of the latch
            }
        }.start()
    }

    private fun startVirtualThreads(latch: CountDownLatch) {
        Thread.startVirtualThread {
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                latch.countDown() // decrement the count of the latch
            }
        }
    }
}