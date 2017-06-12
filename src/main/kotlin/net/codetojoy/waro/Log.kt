
package net.codetojoy.waro

fun String.log(isVerbose:Boolean = true) {
    if (isVerbose) {
        if (Config.logDelay) {
            val delayInMillis = 300L
            try { Thread.sleep(delayInMillis) } catch (e: Exception) {}
        }
        System.out.println(this)
    }
}
