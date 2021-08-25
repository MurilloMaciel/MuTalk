package com.maciel.murillo.user.domain.model

import com.maciel.murillo.util.error.CrudError

class UserGetError(msg: String, collection: String) : CrudError.Get(msg, collection)
class UserGetAllError(msg: String, collection: String) : CrudError.GetAll(msg, collection)
class UserInsertError(msg: String, collection: String) : CrudError.Insert(msg, collection)
class UserDeleteError(msg: String, collection: String) : CrudError.Delete(msg, collection)
class UserUpdateError(msg: String, collection: String) : CrudError.Update(msg, collection)
class UserGetByNameError(msg: String, collection: String) : CrudError.Filter(msg, collection)