package com.artemissoftware.hermesnews.util

import java.io.IOException

class NoConnectivityException() : IOException("No network available, please check your WiFi or Data connection") {

}