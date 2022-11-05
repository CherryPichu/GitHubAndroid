package kr.ac.hallym.githubandroid

import android.content.ContentValues
import android.content.Context
import android.content.ContextWrapper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import kr.ac.hallym.githubandroid.DatabaseHelper.Companion.QueryCreateDB
import kr.ac.hallym.githubandroid.DatabaseHelper.Companion.TABLE_NAME
import kotlin.reflect.KFunction1

// SQLite 모델 출처 : https://cliearl.github.io/posts/android/implement-crud-with-sqliteopenhelper/
class DatabaseHelper private constructor(context: Context) : // private constructor => 싱글톤 패턴, 초기화 불가능
    // stack Overflow
    // What is the context in SQLiteOpenHelper class.what If we provide differenct context from different classes? 대한 질문
    //https://stackoverflow.com/questions/38991119/what-is-the-context-in-sqliteopenhelper-class-what-if-we-provide-differenct-cont
//https://shinjekim.github.io/android/2019/11/01/Android-context%EB%9E%80/
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        const val DATABASE_NAME = "DBName.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "book_table"
        const val COL1_id = "id"
        const val COL2_keyword = "keyword"
        const val COL3_email = "email"
//        const val COL4_EMAIL = "CreateAt"
        const val QueryCreateDB = "id INT(64) NOT NULL PRIMARY KEY," +
            "keyword VARCHAR(200)  DEFAULT NULL ," +
            "email VARCHAR(50)  DEFAULT NULL ," +
            "createAt DATETIME DEFAULT NULL DEFAULT CURRENT_TIMESTAMP"

        //https://www.charlezz.com/?p=45959
        //SingleTon Pattern(싱글톤 패턴)
        @Volatile
        private var instance: DatabaseHelper? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(DatabaseHelper::class.java) {
                instance ?: DatabaseHelper(context).also {
                    instance = it
                }
            }

    }

    /**
     * 나중에 구현하자..
     */
//    fun insertData(Table : Table) {
//        val db = this.writableDatabase
//        val contentValues = ContentValues().apply {
//
//            db.execSQL("")
//
//        }
//        db.insert(TABLE_NAME, null, contentValues) // 값이 없으면 행을 삽입하지않음
//    }

    fun insertData(name: String, phone: String, email: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COL1_id, name)
            put(COL2_keyword, phone)
            put(COL3_email, email)
        }
        db.insert(TABLE_NAME, null, contentValues) // 값이 없으면 행을 삽입하지않음
    }

    fun updateData(id: String, name: String, phone: String, email: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COL1_id, id)
            put(COL2_keyword, name)
            put(COL3_email, phone)
        }
        db.update(TABLE_NAME, contentValues, "$COL1_id = ?", arrayOf(id))
    }

    fun deleteData(id: String) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COL1_id = ?", arrayOf(id))
    }

    fun getAllData(): String {
        var result = "No data in DB"

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        try {
            if (cursor.count != 0) {
                val stringBuffer = StringBuffer()
                while (cursor.moveToNext()) {
                    stringBuffer.append("ID :" + cursor.getInt(0).toString() + "\n")
                    stringBuffer.append("NAME :" + cursor.getString(1) + "\n")
                    stringBuffer.append("PHONE :" + cursor.getString(2) + "\n")
                    stringBuffer.append("EMAIL :" + cursor.getString(3) + "\n\n ")
                }
                result = stringBuffer.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        return result
    }


    override fun onCreate(db: SQLiteDatabase?) {
        var i : List<Int>;

        val createQuery = "CREATE TABLE if not exists $TABLE_NAME (" +
                "$QueryCreateDB"+
                ")"

        db?.execSQL(createQuery)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }

}
