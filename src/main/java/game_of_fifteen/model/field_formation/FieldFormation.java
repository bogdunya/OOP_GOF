package game_of_fifteen.model.field_formation;

import game_of_fifteen.model.Point;
import game_of_fifteen.model.field.Field;
import org.jetbrains.annotations.NotNull;

/**
 * Лабиринт.
 */
public abstract class FieldFormation {
    /**
     * Построить поле.
     * @return поле.
     */
    public Field buildField() {
        Field field = new Field(new Point(3, 3), 0);

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
