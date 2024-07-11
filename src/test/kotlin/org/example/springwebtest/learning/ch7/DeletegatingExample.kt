package org.example.springwebtest.learning.ch7

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.reflect.KProperty

open class PropertyChangeAware{
    protected  val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener){
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener){
        changeSupport.removePropertyChangeListener(listener)
    }
}

class ObservableProperty(
    val propName: String, var propValue: Int,
    val changeSupport: PropertyChangeSupport
){
    fun getValue(): Int = propValue
    fun setValue(newValue: Int){
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(propName, oldValue, newValue)
    }
}

class ObservablePropertyV1(
        var propValue: Int, val changeSupport: PropertyChangeSupport
){
    operator fun getValue(p: PersonV1, prop: KProperty<*>): Int = propValue
    operator fun setValue(p: PersonV1, prop: KProperty<*>, newValue: Int){
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

class Person(
        val name: String, age: Int, salary: Int
) : PropertyChangeAware(){
    val _age = ObservableProperty("age", age, changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) {_age.setValue(value)}
    val _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) {_salary.setValue(value)}
}

class PersonV1(
        val name: String, age: Int, salary: Int
) : PropertyChangeAware(){
    var age: Int by ObservablePropertyV1(age, changeSupport)
    var salary: Int by ObservablePropertyV1(salary, changeSupport)
}