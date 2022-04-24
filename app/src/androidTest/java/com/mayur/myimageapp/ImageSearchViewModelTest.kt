package com.mayur.myimageapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mayur.myimageapp.data.RepoProvider
import com.mayur.myimageapp.data.imageSearch.ImageRepository
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageSearchViewModelTest {

    private lateinit var imageRepository: ImageRepository
    private lateinit var imageSearchViewModel: ImageSearchViewModel

    @Before
    fun setup() {
        imageRepository = RepoProvider.imageRepository
        imageSearchViewModel = ImageSearchViewModel(imageRepository)
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun onResetErrorData_searchErrorIsNull() {
        imageSearchViewModel.searchError.value = Throwable("Some Exception")

        imageSearchViewModel.resetErrorData()

        assertThat(imageSearchViewModel.searchError.value,`is`(nullValue()))
    }

    @Test
    fun onResetErrorData_showErrorIsFalse() {
        imageSearchViewModel.showErrorToast.value = true
        imageSearchViewModel.showErrorUi.value = true

        imageSearchViewModel.resetErrorData()

        assertThat(imageSearchViewModel.showErrorUi.getOrAwaitValue(), `is`(false))
        assertThat(imageSearchViewModel.showErrorToast.getOrAwaitValue(), `is`(false))
    }
}