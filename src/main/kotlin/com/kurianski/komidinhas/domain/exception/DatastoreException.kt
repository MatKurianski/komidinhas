package com.kurianski.komidinhas.domain.exception

import java.lang.RuntimeException

data class DatastoreException(override val cause: Throwable?) : RuntimeException(cause)

