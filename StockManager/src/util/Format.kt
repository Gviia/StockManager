package util

fun Long.toWon(): String = "%,d원".format(this)