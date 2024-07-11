package org.example.springwebtest.learning

class User (val id: Int, val name: String, val address: String)

fun saveUser(user: User){
    fun validate(value: String, fieldName: String){
        if(value.isEmpty()){
            throw IllegalArgumentException("Can`t save user ${user.id}: " + "empty $fieldName")
        }
    }
    validate(user.name, "Name")
    validate(user.address, "Address")
}

fun User.validateBeforeSave(){ // 확장함수 정의 user.id처럼 수신 객체를 지정하지 않아도 공개된 멤버 프로퍼티나 메서드에 접근가능
    fun validate(value: String, fieldName: String){
        if(value.isEmpty()){
            throw IllegalArgumentException("Can`t save user $id: empty $fieldName")
        }
    }
    validate(name, "Name")
    validate(address, "Address")
}