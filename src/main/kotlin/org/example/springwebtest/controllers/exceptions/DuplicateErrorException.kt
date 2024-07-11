package org.example.springwebtest.controllers.exceptions


class DuplicateErrorException: RuntimeException {
    override var message: String = ""
    constructor(_message: String){
        message = _message
    }
}