package com.shahad.o.domain.usecases

import com.google.firebase.auth.AuthCredential
import com.shahad.o.data.FakeRepositoryImp
import com.shahad.o.util.SignInResult
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class AuthUseCaseTest{
    private lateinit var authUseCase: AuthUseCase
    private lateinit var fakeRepositoryImp: FakeRepositoryImp

    @Before
    fun setUp(){
        fakeRepositoryImp = FakeRepositoryImp()
        authUseCase = AuthUseCase(fakeRepositoryImp)
        fakeRepositoryImp.userData = null
    }


    @Test
    fun `test googleSignIn success`() = runBlocking {

        // Given
        val credential: AuthCredential = mock(AuthCredential::class.java)
        val expectedResult = SignInResult(data = fakeRepositoryImp.userData, error = null)
        fakeRepositoryImp.userData = null

        // When
        val result: Flow<SignInResult> = authUseCase.googleSignIn(credential)

        // Then
        assertEquals(expectedResult, result.first())
    }
//    @Test
//    fun `test googleSignIn failure`() = runBlocking {
//        // Given
//        val credential: AuthCredential = mock(AuthCredential::class.java)
//        val expectedResult = SignInResult(data = null,error = "NO INTERNET")
//
//        // When
//        val result: Flow<SignInResult> = authUseCase.googleSignIn(credential)
//
//        // Then
//        assertEquals(expectedResult, result.first())
//    }
}