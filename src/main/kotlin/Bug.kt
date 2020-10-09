
//class Obj {
//    var prop: Prop = Prop()
//
//    fun setProp(prop: Prop, param: Int) {
//        this.prop = prop
//    }
//}
//
//class Prop
//
//class Foo {
//    lateinit var prop: Prop
//
//    fun onCreate() {
//        prop = Prop()
//
//        val obj = Obj().apply {
//            // the problem is that 'prop' is 'this.prop' instead of 'this@Foo.prop'
//            // and there is no warnings about it
//            setProp(prop, 0)
//        }
//        //...
//    }
//}