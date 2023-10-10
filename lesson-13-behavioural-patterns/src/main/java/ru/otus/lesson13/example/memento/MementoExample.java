package ru.otus.lesson13.example.memento;

import ru.otus.lesson13.example.memento.data.TextItem;

public class MementoExample {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.add(new TextItem("Мама"));
        editor.add(new TextItem("мыла"));
        editor.add(new TextItem("раму"));

        editor.print();

        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(editor.saveState());

        editor.add(new TextItem("что-то"));
        caretaker.setMemento(editor.saveState());

        editor.add(new TextItem("пошло"));
        caretaker.setMemento(editor.saveState());

        editor.add(new TextItem("не так"));

        editor.print();

        editor.restoreState(caretaker.getMemento());
        editor.print();

        editor.restoreState(caretaker.getMemento());
        editor.print();

        editor.restoreState(caretaker.getMemento());
        editor.print();
    }
}
