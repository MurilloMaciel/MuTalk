package com.maciel.murillo.user.data.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.user.data.datasource.UserRemoteDataSource
import com.maciel.murillo.user.data.mapper.UserDataToModelMapper
import com.maciel.murillo.user.data.mapper.UserModelToUserDataMapper
import com.maciel.murillo.user.data.model.UserData
import com.maciel.murillo.user.data.model.UserDataFactory
import com.maciel.murillo.user.domain.model.*
import com.maciel.murillo.util.result.Result
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class UserRemoteDataSourceImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val collection: CollectionReference = mockk()
    private val documentReference: DocumentReference = mockk()
    private val task: Task<Void> = mockk()
    private val taskDocumentSnapshot: Task<DocumentSnapshot> = mockk()
    private val taskQuerySnapshot: Task<QuerySnapshot> = mockk()
    private val documentSnapshot: DocumentSnapshot = mockk()
    private val querySnapshot: QuerySnapshot = mockk()
    private val query: Query = mockk()

    private val userDataToModelMapper: UserDataToModelMapper = mockk()
    private val userModelToUserDataMapper: UserModelToUserDataMapper = mockk()
    private val db: FirebaseFirestore = mockk()

    private val dataSource: UserRemoteDataSource = UserRemoteDataSourceImpl(
        userDataToModelMapper = userDataToModelMapper,
        userModelToUserDataMapper = userModelToUserDataMapper,
        db = db,
    )

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun insert_throwException_returnUserInsertError() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        prepareScenario(throwException = true)

        val result = dataSource.insert(user)

        assertTrue { result is Result.Error }
        assertTrue { (result as Result.Error).value is UserInsertError }
    }

    @Test
    fun insert_runsWithoutErrors_returnSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        prepareScenario()

        val result = dataSource.insert(user)

        assertTrue { result is Result.Success }
        assertEquals(user, result.get())
    }

    @Test
    fun get_throwException_returnUserGetError() = coroutineTestRule.runBlockingTest {
        prepareScenario(throwException = true)

        val result = dataSource.get("uid")

        assertTrue { result is Result.Error }
        assertTrue { (result as Result.Error).value is UserGetError }
    }

    @Test
    fun get_runsWithoutErrors_returnSuccess() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = dataSource.get("uid")

        val expect = UserFactory.makeUser()
        assertTrue { result is Result.Success }
        assertEquals(expect, result.get())
    }

    @Test
    fun update_throwException_returnUserUpdateError() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        prepareScenario(throwException = true)

        val result = dataSource.update(user)

        assertTrue { result is Result.Error }
        assertTrue { (result as Result.Error).value is UserUpdateError }
    }

    @Test
    fun update_runsWithoutErrors_returnSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        prepareScenario()

        val result = dataSource.update(user)

        assertTrue { result is Result.Success }
        assertEquals(user, result.get())
    }

    @Test
    fun delete_throwException_returnUserDeleteError() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        prepareScenario(throwException = true)

        val result = dataSource.delete(user)

        assertTrue { result is Result.Error }
        assertTrue { (result as Result.Error).value is UserDeleteError }
    }

    @Test
    fun delete_runsWithoutErrors_returnSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        prepareScenario()

        val result = dataSource.delete(user)

        assertTrue { result is Result.Success }
        assertEquals(user, result.get())
    }

    @Test
    fun getAll_throwException_returnUserGetAllError() = coroutineTestRule.runBlockingTest {
        prepareScenario(throwException = true)

        val result = dataSource.getAll()

        assertTrue { result is Result.Error }
        assertTrue { (result as Result.Error).value is UserGetAllError }
    }

    @Test
    fun getAll_runsWithoutErrors_returnSuccess() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = dataSource.getAll()

        assertTrue { result is Result.Success }
    }

    @Test
    fun getByName_throwException_returnUserInsertError() = coroutineTestRule.runBlockingTest {
        prepareScenario(throwException = true)

        val result = dataSource.getByName("name")

        assertTrue { result is Result.Error }
        assertTrue { (result as Result.Error).value is UserGetByNameError }
    }

    @Test
    fun getByName_runsWithoutErrors_returnSuccess() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = dataSource.getByName("name")

        assertTrue { result is Result.Success }
    }

    private fun prepareScenario(
        user: User = UserFactory.makeUser(),
        userData: UserData = UserDataFactory.makeUserData(),
        throwException: Boolean = false
    ) {
        if (throwException) {
            coEvery { db.collection(any()).document() } throws Exception("Message Error")
        } else {
            coEvery { userDataToModelMapper.mapFrom(any()) } returns user
            coEvery { userModelToUserDataMapper.mapFrom(any()) } returns userData
            coEvery { db.collection(any()) } returns collection
            coEvery { collection.document() } returns documentReference
            coEvery { documentReference.id } returns "id"
            coEvery { documentReference.set(any()) } returns task
            coEvery { task.exception } returns null
            coEvery { task.isCanceled } returns false
            coEvery { task.isComplete } returns true
            coEvery { task.result } returns null
            coEvery { collection.document(any()) } returns documentReference
            coEvery { documentReference.get() } returns taskDocumentSnapshot
            coEvery { documentReference.set(any()) } returns task
            coEvery { documentReference.delete() } returns task
            coEvery { taskDocumentSnapshot.exception } returns null
            coEvery { taskDocumentSnapshot.isCanceled } returns false
            coEvery { taskDocumentSnapshot.isComplete } returns true
            coEvery { taskDocumentSnapshot.result } returns documentSnapshot
            coEvery { documentSnapshot.toObject(UserData::class.java) } returns userData
            coEvery { collection.get() } returns taskQuerySnapshot
            coEvery { taskQuerySnapshot.exception } returns null
            coEvery { taskQuerySnapshot.isCanceled } returns false
            coEvery { taskQuerySnapshot.isComplete } returns true
            coEvery { taskQuerySnapshot.result } returns querySnapshot
            coEvery { querySnapshot.documents } returns listOf(documentSnapshot,documentSnapshot,documentSnapshot,documentSnapshot)
            coEvery { collection.whereEqualTo(any<String>(), any()) } returns query
            coEvery { query.get() } returns taskQuerySnapshot
        }
    }
}