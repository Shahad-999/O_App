package com.shahad.o.domain.usecases

import com.shahad.o.data.FakeRepositoryImp
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCalendarTest{
    private lateinit var getCalendar: GetCalendar
    private lateinit var fakeRepositoryImp: FakeRepositoryImp

    @Before
    fun setUp(){
        fakeRepositoryImp = FakeRepositoryImp()
        getCalendar = GetCalendar(fakeRepositoryImp)
    }

    @Test
    fun `test get calendar first item in range`(): Unit = runBlocking{
        //Given
        val year = 2024
        val month = 2
        //When
        val result = getCalendar.getCalendar(year, month = month)

        //Then
        result?.takeIf { it.isNotEmpty() }?.first()?.date?.let {
            val compare = it >= 1706734800000L
            assert(compare)
        }
    }
    @Test
    fun `test get calendar is last item in range`(): Unit = runBlocking{
        //Given
        val year = 2024
        val month = 2

        //When
        val result = getCalendar.getCalendar(year, month = month)

        //Then
        result?.takeIf { it.isNotEmpty() }?.last()?.date?.let {
            val compare = it <= 1709240400000L
            assert(compare)
        }
    }

}