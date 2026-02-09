package ru.kudrin;

class ParentStatic {
    public static void method(String string) {
        System.out.println("Parent   :" + string);
    }
    public void method2(String string) {
        System.out.println("Parent   :" + string);
    }
}
class ChildStatic extends ParentStatic {
    public static void method(String string) {
        System.out.println("Child   :" + string);
    }
}
public class StaticMethodOverriding {
    public static void main(String[] args) {
        /*1)Для методов класса (или статических методов) вызывается метод в
        соответствии с типом ссылки, которое означает, что вызов метода
        определяется во время компиляции
        2) Для методов экземпляра (или нестатических методов), метод вызывается в
        соответствии с типом передаваемого объекта, а не по типу ссылки,
        что означает, что вызовы метода разрешаются во время выполнения. */
        ChildStatic.method("Me");
        ParentStatic parentStatic = new ChildStatic();
        parentStatic.method("Me");
    }
}