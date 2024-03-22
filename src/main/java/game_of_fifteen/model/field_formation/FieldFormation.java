package game_of_fifteen.model.field_formation;

import game_of_fifteen.model.Point;
import game_of_fifteen.model.field.Field;
import org.jetbrains.annotations.NotNull;

/**
 * Создатель поля
 */
public abstract class FieldFormation {

    /**
     * Построить поле
     * Задать параметры типа игры 0-классика, 1-нет боковых стен, 2-костяшка может передвигаться из правого нижнего угла в левый верхний
     * @return поле
     */
    public Field buildField() {
        Field field = new Field(2);
        addTile(field);
        return field;
    }

    /**
     * Высота поля
     * @return высота поля
     */
    protected abstract int fieldHeight();

    /**
     * Ширина поля
     * @return ширина поля
     */
    protected abstract int fieldWidth();

    /**
     * Добавить костяшку и стены на поле
     */
    protected abstract void addTile(@NotNull Field field);

}
