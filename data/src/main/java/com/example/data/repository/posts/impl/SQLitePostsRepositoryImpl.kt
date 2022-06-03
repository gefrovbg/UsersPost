package com.example.data.repository.posts.impl

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.data.repository.posts.SQLitePostsRepository
import com.example.link.models.post.Post
import com.example.link.models.user.UserModel

class SQLitePostsRepositoryImpl(context: Context, factory: SQLiteDatabase.CursorFactory?): SQLitePostsRepository, SQLiteOpenHelper(context,
    DATABASE_NAME, factory,
    DATABASE_VERSION
) {

    override suspend fun postAllUsersPosts(posts: ArrayList<Post>): Boolean {

        return try {
            for (i in posts.indices){
                if (getPostById(postId = posts[i].id) == null) {
                    val values = ContentValues()
                    values.put(POST_ID, posts[i].id)
                    values.put(BODY, posts[i].body)
                    values.put(TITLE, posts[i].title)
                    values.put(USER_ID, posts[i].userId)

                    val db = this.writableDatabase
                    db.insert(TABLE_NAME, null, values)
                    db.close()
                }
            }
            true
        }catch (e: Exception){
            false
        }

    }

    override suspend fun getAllPostsOfUser(userModel: UserModel): ArrayList<Post> {

        val query = "SELECT * FROM ${TABLE_NAME} WHERE ${USER_ID} =  \"${userModel.id}\""
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        val arrayList = arrayListOf<Post>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast()) {
            val post_id = Integer.parseInt(cursor.getString(1))
            val body = cursor.getString(2)
            val title = cursor.getString(3)
            val userId = Integer.parseInt(cursor.getString(4))
            arrayList.add(Post(id = post_id, body = body, title = title, userId = userId)) //add the item
            cursor.moveToNext()
        }
        cursor.close()
        db.close()
        return arrayList

    }

    private fun getPostById(postId: Int): Post?{
        val query = "SELECT * FROM ${TABLE_NAME} WHERE ${POST_ID} =  \"${postId}\""
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            val post_id = Integer.parseInt(cursor.getString(1))
            val body = cursor.getString(2)
            val title = cursor.getString(3)
            val userId = Integer.parseInt(cursor.getString(4))
            Post(id = post_id, body = body, title = title, userId = userId)
        }else null
    }

    companion object{

        private val DATABASE_NAME = "POSTS_LIST"

        private val DATABASE_VERSION = 1

        private val ID_COL = "id"
        private val POST_ID = "post_id" //int
        private val BODY = "body" //string
        private val TITLE = "title" //string
        private val USER_ID = "userId" //string


        private val TABLE_NAME = "posts_table"

    }

    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                POST_ID + " INTEGER," +
                BODY + " TEXT," +
                TITLE + " TEXT," +
                USER_ID + " INTEGER" +")")
        db.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

    }

}