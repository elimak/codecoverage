package com.elimak.codecoverage

import org.mockito.ArgumentCaptor
import org.mockito.Mockito

abstract class BaseTest {

    protected fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T

    protected fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
}