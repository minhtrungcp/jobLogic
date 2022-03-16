package com.example.joblogic.data.repository.sell_product

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.joblogic.data.local.db.ProductDAO
import com.example.joblogic.domain.model.Product
import javax.inject.Inject

val DATABASENAME = "MyDatabase"
val TABLENAME = "ItemToSell"
val COL_ID = "id"
val COL_NAME = "name"
val COL_PRICE = "price"
val COL_QUANTITY = "quantity"
val COL_TYPE = "type"

class DataBaseHelper @Inject constructor(var context: Context) : SQLiteOpenHelper(
    context, DATABASENAME, null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " VARCHAR(256)," + COL_PRICE + " INTEGER," + COL_QUANTITY + " INTEGER," + COL_TYPE + " INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db);
    }

    fun insertData(product: Product) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, product.name)
        contentValues.put(COL_PRICE, product.price)
        contentValues.put(COL_QUANTITY, product.quantity)
        contentValues.put(COL_TYPE, product.type)
        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Insert Sell Product Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Insert Sell Product Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readData(): MutableList<ProductDAO> {
        val list: MutableList<ProductDAO> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val product = ProductDAO(
                    result.getString(result.getColumnIndexOrThrow(COL_ID)).toInt(),
                    result.getString(result.getColumnIndexOrThrow(COL_NAME)).toString(),
                    result.getString(result.getColumnIndexOrThrow(COL_PRICE)).toInt(),
                    result.getString(result.getColumnIndexOrThrow(COL_QUANTITY)).toInt(),
                    result.getString(result.getColumnIndexOrThrow(COL_TYPE)).toInt()
                )
                list.add(product)
            } while (result.moveToNext())
        }
        return list
    }

    fun isExistData(): Boolean {
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        return result.count > 0
    }
}