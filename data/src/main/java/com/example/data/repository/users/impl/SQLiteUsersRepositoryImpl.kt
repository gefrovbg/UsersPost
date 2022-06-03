package com.example.data.repository.users.impl

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.data.repository.users.SQLiteUsersRepository
import com.example.link.models.user.Address
import com.example.link.models.user.Company
import com.example.link.models.user.Geo
import com.example.link.models.user.UserModel

class SQLiteUsersRepositoryImpl(context: Context, factory: SQLiteDatabase.CursorFactory?): SQLiteUsersRepository, SQLiteOpenHelper(context,
    DATABASE_NAME, factory,
    DATABASE_VERSION
) {

    override suspend fun postAllUsers(users: ArrayList<UserModel>): Boolean {

        return try {
            for (i in users.indices){
                if (getUserByUsername(searchUserName = users[i].username) == null) {
                    val values = ContentValues()
                    values.put(USER_ID, users[i].id)
                    values.put(NAME, users[i].name)
                    values.put(PHONE, users[i].phone)
                    values.put(USERNAME, users[i].username)
                    values.put(WEBSITE, users[i].website)
                    values.put(EMAIL, users[i].email)
                    values.put(COMPANY_BS, users[i].company.bs)
                    values.put(COMPANY_NAME, users[i].company.name)
                    values.put(COMPANY_CATCHPHRASE, users[i].company.catchPhrase)
                    values.put(ADDRESS_CITY, users[i].address.city)
                    values.put(ADDRESS_STREET, users[i].address.street)
                    values.put(ADDRESS_SUITE, users[i].address.suite)
                    values.put(ADDRESS_ZIPCODE, users[i].address.zipcode)
                    values.put(ADDRESS_GEO_LAT, users[i].address.geo.lat)
                    values.put(ADDRESS_GEO_LNG, users[i].address.geo.lng)
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

    override suspend fun getAllUsers(): ArrayList<UserModel> {

        val query = "SELECT * FROM ${TABLE_NAME}"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        val arrayList = arrayListOf<UserModel>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
//            val id = Integer.parseInt(cursor.getString(1)).toLong()
            val userId = Integer.parseInt(cursor.getString(1))
            val name = cursor.getString(2)
            val phone = cursor.getString(3)
            val username = cursor.getString(4)
            val website = cursor.getString(5)
            val email = cursor.getString(6)
            val company_bs = cursor.getString(7)
            val company_name = cursor.getString(8)
            val company_catchPhrase = cursor.getString(9)
            val address_city = cursor.getString(10)
            val address_street = cursor.getString(11)
            val address_suite = cursor.getString(12)
            val address_zipcode = cursor.getString(13)
            val address_geo_lat = cursor.getString(14)
            val address_geo_lng = cursor.getString(15)
            arrayList.add(
                UserModel(
                address = Address(
                    city = address_city,
                    geo = Geo(
                        lat = address_geo_lat,
                        lng = address_geo_lng
                    ),
                    street = address_street,
                    suite = address_suite,
                    zipcode = address_zipcode
                ),
                company = Company(
                    bs = company_bs,
                    name = company_name,
                    catchPhrase = company_catchPhrase
                ),
                email = email,
                id = userId,
                name = name,
                phone = phone,
                username = username,
                website = website)
            )
            cursor.moveToNext()
        }
        cursor.close()
        db.close()
        return arrayList

    }

    override suspend fun getUserByUsername(searchUserName: String): UserModel? {

        val query = "SELECT * FROM ${TABLE_NAME} WHERE ${USERNAME} =  \"${searchUserName}\""
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)

        return if (cursor.moveToFirst()) {
            cursor.moveToFirst()

            val userId = Integer.parseInt(cursor.getString(1))
            val name = cursor.getString(2)
            val phone = cursor.getString(3)
            val username = cursor.getString(4)
            val website = cursor.getString(5)
            val email = cursor.getString(6)
            val company_bs = cursor.getString(7)
            val company_name = cursor.getString(8)
            val company_catchPhrase = cursor.getString(9)
            val address_city = cursor.getString(10)
            val address_street = cursor.getString(11)
            val address_suite = cursor.getString(12)
            val address_zipcode = cursor.getString(13)
            val address_geo_lat = cursor.getString(14)
            val address_geo_lng = cursor.getString(15)
            val returnUser = UserModel(
                address = Address(
                    city = address_city,
                    geo = Geo(
                        lat = address_geo_lat,
                        lng = address_geo_lng
                    ),
                    street = address_street,
                    suite = address_suite,
                    zipcode = address_zipcode
                ),
                company = Company(
                    bs = company_bs,
                    name = company_name,
                    catchPhrase = company_catchPhrase
                ),
                email = email,
                id = userId,
                name = name,
                phone = phone,
                username = username,
                website = website)
            cursor.close()
            db.close()
            returnUser
        }else null

    }

    companion object{

        private val DATABASE_NAME = "USERS_LIST"

        private val DATABASE_VERSION = 1

        private val ID_COL = "id"
        private val USER_ID = "user_id" //int
        private val NAME = "name" //string
        private val PHONE = "phone" //string
        private val USERNAME = "username" //string
        private val WEBSITE = "website" //string
        private val EMAIL = "email" //string
        private val COMPANY_BS = "company_bs" //string
        private val COMPANY_NAME = "company_name" //string
        private val COMPANY_CATCHPHRASE = "company_catchPhrase" //string
        private val ADDRESS_CITY = "address_city" //string
        private val ADDRESS_STREET = "address_street" //string
        private val ADDRESS_SUITE = "address_suite" //string
        private val ADDRESS_ZIPCODE = "address_zipcode" //string
        private val ADDRESS_GEO_LAT = "address_geo_lat" //string
        private val ADDRESS_GEO_LNG = "address_geo_lng" //string

        private val TABLE_NAME = "users_table"

    }

    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
        + ID_COL + " INTEGER PRIMARY KEY, " +
                USER_ID + " INTEGER," +
                NAME + " TEXT," +
                PHONE + " TEXT," +
                USERNAME + " TEXT," +
                WEBSITE + " TEXT," +
                EMAIL + " TEXT," +
                COMPANY_BS + " TEXT," +
                COMPANY_NAME + " TEXT," +
                COMPANY_CATCHPHRASE + " TEXT," +
                ADDRESS_CITY + " TEXT," +
                ADDRESS_STREET + " TEXT," +
                ADDRESS_SUITE + " TEXT," +
                ADDRESS_ZIPCODE + " TEXT," +
                ADDRESS_GEO_LAT + " TEXT," +
                ADDRESS_GEO_LNG + " TEXT" +")")
        db.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

    }

}