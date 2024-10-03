package com.github.examples.hello_webflux

class Greeting(){
    var message: String? = null

    constructor(message: String?) : this() {
        this.message = message
    }

    override fun toString(): String {
        return "Greeting{" +
                "message='" + message + '\'' +
                '}'
    }
}
