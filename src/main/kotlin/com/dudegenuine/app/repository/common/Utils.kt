package com.dudegenuine.app.repository.common

import java.text.DateFormat
import java.util.UUID
import java.util.regex.Pattern

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Utils {
    fun formattedDate(timestamp: Long): String =
        DateFormat
            .getDateInstance(DateFormat.DEFAULT)
            .format(timestamp)
    fun uuidOrNull(uuidString: String): UUID? = try {
        UUID.fromString(uuidString)
    } catch (e: Exception){
        println(e.localizedMessage)
        null
    }
    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }
    /*/*
     * Base class for entities with string id
     */
    abstract class StringEntityClass<out E: Entity<String>>(table: IdTable<String>, entityType: Class<E>? = null) : EntityClass<String, E>(table, entityType)

    /*
     * Base class for table objects with string id
     */
    open class StringIdTable(name: String = "", columnName: String = "id", columnLength: Int = 10) : IdTable<String>(name) {
        override val id: Column<EntityID<String>> = varchar(columnName, columnLength).entityId()
        override val primaryKey by lazy { super.primaryKey ?: PrimaryKey(id) }
    }*/
}