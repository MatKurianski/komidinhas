package com.kurianski.komidinhas.adapter.datastore

import com.kurianski.komidinhas.adapter.datastore.entity.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserPostgresRepository : CrudRepository<UserEntity, String>