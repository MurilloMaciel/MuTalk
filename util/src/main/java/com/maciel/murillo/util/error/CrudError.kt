package com.maciel.murillo.util.error

private const val GET_DEFAULT_MESSAGE = "An error occurred while fetching data -> "
private const val GET_ALL_DEFAULT_MESSAGE = "An error occurred while fetching all data -> "
private const val INSERT_DEFAULT_MESSAGE = "An error occurred while inserting data -> "
private const val UPDATE_DEFAULT_MESSAGE = "An error occurred while updating data -> "
private const val DELETE_DEFAULT_MESSAGE = "An error occurred while deleting data -> "
private const val FILTER_DEFAULT_MESSAGE = "An error occurred while fetching with filter -> "

sealed class CrudError(val message: String?, val collection: String?) {

    open class Get(
        collection: String? = null,
        msg: String? = GET_DEFAULT_MESSAGE
    ) : CrudError(GET_DEFAULT_MESSAGE + msg, collection)

    open class GetAll(
        collection: String? = null,
        msg: String? = GET_ALL_DEFAULT_MESSAGE
    ) : CrudError(GET_ALL_DEFAULT_MESSAGE + msg, collection)

    open class Insert(
        collection: String? = null,
        msg: String? = INSERT_DEFAULT_MESSAGE
    ) : CrudError(INSERT_DEFAULT_MESSAGE + msg, collection)

    open class Update(
        collection: String? = null,
        msg: String? = UPDATE_DEFAULT_MESSAGE
    ) : CrudError(UPDATE_DEFAULT_MESSAGE + msg, collection)

    open class Delete(
        collection: String? = null,
        msg: String? = DELETE_DEFAULT_MESSAGE
    ) : CrudError(DELETE_DEFAULT_MESSAGE + msg, collection)

    open class Filter(
        collection: String? = null,
        msg: String? = FILTER_DEFAULT_MESSAGE
    ) : CrudError(FILTER_DEFAULT_MESSAGE + msg, collection)
}