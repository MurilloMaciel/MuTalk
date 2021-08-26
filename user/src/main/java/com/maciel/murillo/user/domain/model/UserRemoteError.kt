package com.maciel.murillo.user.domain.model

import com.maciel.murillo.util.error.CrudError

class UserGetError(msg: String, collection: String) : CrudError.Get(collection, msg)
class UserGetAllError(msg: String, collection: String) : CrudError.GetAll(collection, msg)
class UserInsertError(msg: String, collection: String) : CrudError.Insert(collection, msg)
class UserDeleteError(msg: String, collection: String) : CrudError.Delete(collection, msg)
class UserUpdateError(msg: String, collection: String) : CrudError.Update(collection, msg)
class UserGetByNameError(msg: String, collection: String) : CrudError.Filter(collection, msg)