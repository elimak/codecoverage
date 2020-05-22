package com.elimak.codecoverage.ui.landing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elimak.codecoverage.App
import com.elimak.codecoverage.BaseTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class LandingViewModelTest: BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val applicationMock = Mockito.mock(App::class.java)

    lateinit var landingViewModel: LandingViewModel

    @Before
    fun setUp() {
        landingViewModel = LandingViewModel(applicationMock)
    }

    @Test
    fun `test set and get text`() {
        landingViewModel.text.value = "abcd"
        Assert.assertEquals(landingViewModel.text.value, "abcd")
    }
}