package goat_cabbage.model.labirint;

import goat_cabbage.model.Point;
import goat_cabbage.model.field.Field;
import org.jetbrains.annotations.NotNull;

/**
 * Лабиринт.
 */
public abstract class Labirint {
    /**
     * Построить поле.
     * @return поле.
     */
    public Field buildField() {
        Field field = new Field(fieldWidth(), fieldHeight(), new Point(3, 3));

        addTile(field);


        return field;
    }

    /**
     * Высота поля.
     * @return высота поля.
     */
    protected abstract int fieldHeight();

    /**
     * Ширина поля.
     * @return ширина поля.
     */
    protected abstract int fieldWidth();

    /**
     * Добавить костяшку и стены на поле.
     */
    protected abstract void addTile(@NotNull Field field);
    protected abstract void addWalls(@NotNull Field field);


}
