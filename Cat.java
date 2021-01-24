package ru.geekbrains.JASD.lesson_04;

// class iteroCat
// reset()
// next(), prev(for dll)
// getCurrent()
// atEnd()
// insertBefore();
// insertAfter();
// deleteCurrent();
public class Cat {
    int age;
    String name;

    public Cat(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Cat(a=%d,n=%s)", age, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return this.age == cat.age && this.name.equals(cat.name);
    }
}
