package org.m0skit0.android.mondlycodetask

import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.m0skit0.android.mondlycodetask.data.ItemsDTO
import org.m0skit0.android.mondlycodetask.data.ItemsService
import org.m0skit0.android.mondlycodetask.domain.DomainItemMapper
import org.m0skit0.android.mondlycodetask.domain.Item
import org.m0skit0.android.mondlycodetask.domain.ItemsRepositoryImpl

// TODO Parametrize tests using Kotest
@OptIn(ExperimentalCoroutinesApi::class)
class ItemsRepositoryImplTest {

    private val itemsService: ItemsService = mockk()
    private val domainItemMapper: DomainItemMapper = mockk()

    private val testCoroutineScheduler = TestCoroutineScheduler()

    private val sut = ItemsRepositoryImpl(
        itemsService = itemsService,
        domainItemMapper = domainItemMapper,
        ioDispatcher = UnconfinedTestDispatcher(scheduler = testCoroutineScheduler),
    )

    @Test
    fun `items() should return mapped items`() {
        runTest(testCoroutineScheduler) {
            // Given
            val itemsDTO: ItemsDTO = mockk()
            val mappedItems: List<Item> = listOf(mockk())
            coEvery { itemsService.items() } returns itemsDTO
            every { domainItemMapper.map(any()) } returns mappedItems
            // When
            val items = sut.items()
            // Then
            items shouldBe Result.success(mappedItems)
            coVerify(exactly = 1) {
                itemsService.items()
                domainItemMapper.map(itemsDTO)
            }
        }
    }

    @Test
    fun `items() should return failure when an error happens on remote API call`() {
        runTest(testCoroutineScheduler) {
            // Given
            val exception = Exception()
            coEvery { itemsService.items() } throws exception
            // When
            val items = sut.items()
            // Then
            items shouldBe Result.failure(exception)
            coVerify(exactly = 1) {
                itemsService.items()
            }
            verify(exactly = 0) {
                domainItemMapper.map(any())
            }
        }
    }

    @Test
    fun `items() should not call remote API on second time`() {
        runTest(testCoroutineScheduler) {
            // Given
            val itemsDTO: ItemsDTO = mockk()
            val mappedItems: List<Item> = listOf(mockk())
            coEvery { itemsService.items() } returns itemsDTO
            every { domainItemMapper.map(any()) } returns mappedItems
            // When
            sut.items()
            val items = sut.items()
            // Then
            items shouldBe Result.success(mappedItems)
            coVerify(exactly = 1) {
                itemsService.items()
                domainItemMapper.map(itemsDTO)
            }
        }
    }
}
