
package net.codetojoy.waro

class Log {
    companion object {
        var delayInMillis:Long = 500

        fun setTestMode() {
            delayInMillis = 5
        }

        fun log(s: String, isVerbose: Boolean = true) {
            if (isVerbose) {
                try { Thread.sleep(delayInMillis) } catch (e: Exception) {}
                System.out.println(s)
            }
        }
    }
}
