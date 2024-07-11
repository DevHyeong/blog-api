package org.example.springwebtest.learning.ch4


fun print(){

}

open class User1 private constructor(val nickname: String){
    companion object{
        fun newSubscribingUser(email: String) = User1(email.substringBefore('@'))
        fun newFacebookUser(accountId: Int) = User1(accountId.toString())
    }
}

open class SuperUser(val superName: String){

}

class User2(superName: String) : SuperUser(superName) {

}