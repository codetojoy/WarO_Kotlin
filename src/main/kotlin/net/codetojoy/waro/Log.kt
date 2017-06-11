
package net.codetojoy.waro

fun String.log(isVerbose:Boolean = true) {
    if (isVerbose) {
        val delayInMillis:Long = 300
        try { Thread.sleep(delayInMillis) } catch (e: Exception) {}
        System.out.println(this)
    }
}
