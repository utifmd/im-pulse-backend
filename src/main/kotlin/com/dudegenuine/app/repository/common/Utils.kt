package com.dudegenuine.app.repository.common

import java.text.DateFormat

/**
 * Fri, 09 Sep 2022
 * com.dudegenuine.im-pulse-backend by utifmd
 **/
object Utils {
    fun formattedDate(timestamp: Long): String =
        DateFormat
            .getDateInstance(DateFormat.DEFAULT)
            .format(timestamp)
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