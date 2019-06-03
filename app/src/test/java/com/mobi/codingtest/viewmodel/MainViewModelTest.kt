package com.mobi.codingtest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobi.codingtest.data.AcronymResponse
import com.mobi.codingtest.repository.Repository

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository
    lateinit var mainViewModel: MainViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(repository)

        val list = listOf(AcronymResponse())
        val observable = Observable.create<List<AcronymResponse>> { observer ->
            observer.onNext(list)
            observer.onComplete()
        }
        Mockito.`when`(repository.getAcronyms(ArgumentMatchers.anyString())).thenReturn(observable)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAbbreviationResultTest() {
        mainViewModel.getAbbreviationResult("TV")
        Mockito.verify<Repository>(repository).getAcronyms(ArgumentMatchers.anyString())
    }

    @Test
    fun getResponseLiveData() {

    }

    companion object {
        init {
        }

        @BeforeClass @JvmStatic fun setup() {
            // Override the default "out of the box" AndroidSchedulers.mainThread() Scheduler
            //
            // This is necessary here because otherwise if the static initialization block in AndroidSchedulers
            // is executed before this, then the Android SDK dependent version will be provided instead.
            //
            // This would cause a java.lang.ExceptionInInitializerError when running the test as a
            // Java JUnit test as any attempt to resolve the default underlying implementation of the
            // AndroidSchedulers.mainThread() will fail as it relies on unavailable Android dependencies.

            // Comment out this line to see the java.lang.ExceptionInInitializerError
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }

        @AfterClass @JvmStatic fun teardown() {
            // clean up after this class, leave nothing dirty behind
        }
    }
}