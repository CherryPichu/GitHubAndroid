package kr.ac.hallym.githubandroid

import org.junit.Test
import org.junit.jupiter.api.Assertions.*
// https://www.youtube.com/watch?v=IhPPDzNzJBQ
internal class TableTest {

    @Test
    operator fun iterator() {

    }

    @Test
    fun get() {
//        val table = Table(mutableMapOf(1 to "One", 2 to "Two"))
//        val res = table[  listOf<Any>(1, 2)].toString()
//
//        print( "결과 : "+  res)
//        assertEquals("[One, Two]",res)
    }


    @Test
    fun set() {
        val table = Table(mutableMapOf(1 to "1", 2 to "2", "id" to "444"))
        assertEquals("{id=444}", table)
    }

    @Test
    fun getMap() {
//        fail<Any>()
    }
}