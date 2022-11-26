package kr.ac.hallym.githubandroid

import android.app.Application
import android.content.ContentValues
import kr.ac.hallym.githubandroid.DatabaseHelper.Companion.TABLE_NAME

// 구현하다가 데이터 클래스로 구현하는게 더 빠르다고 판단함.
// 버림
//https://hungseong.tistory.com/30  설명 : <in T, out T>


/** Table 객체는 Map 객체를 인자로 받아서 Table의 Init{ .. }에서 미리 지정한 맴버변수만 골라서 초기화한다.
 *  Table 객체는 Map 함수에서 지원하는 연산자를 사용할 수 있다.
 *  Table 객체는 CRUD 모델을 지원한다.
 */
class Table (paramap : MutableMap<Any, Any?>){
    private var map = mutableMapOf<Any, Any?>();


    init { // 테이블 수정
        map["id"] = paramap["id"]
    }

    operator fun iterator(): Iterator<Any> {
        return map.keys.iterator()
    }

    /**
     * 리스트로 Keys 를 받아서 value를 리스트로 반환
     *  @param listOf<Any>( 파라미터들 )
     *  @sample table[  listOf<Any>(1, 2)]
     *  @return List<Any>타입
     */
    operator fun get(index : List<Any>): Any? {
        val res = mutableListOf<Any>()
        for(i in index){
            map[i]?.let { res.add(it) }
        }
        return res
    }

    operator fun get(index : Any): Any? {
        return map[index]
    }

    operator fun set(index : Any, value : Any?) {
        map[index] = value
    }

    override fun toString(): String {
        return map.toString()
    }






}

//https://stackoverflow.com/questions/48742377/kotlin-gradle-make-sure-you-have-kotlin-reflect-jar-in-the-classpath
// implementation "org.jetbrains.kotlin:kotlin-reflect:1.3.41"  필요해서 폐기
//        Table2::class.typeParameters
//        Log.d("namjung", Table2::class.members.toString() )
