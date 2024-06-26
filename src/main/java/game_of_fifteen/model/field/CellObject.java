package game_of_fifteen.model.field;

import org.jetbrains.annotations.NotNull;

/**
 * Абстрактный класс объекта
 */
public abstract class CellObject {
    /**
     * Позиция объекта
     */
    protected Cell position;
    protected int number;

    /**
     * Получить позицию объекта
     * @return позиция объекта
     */
    public Cell getPosition() {
        return position;
    }
    public int getNumber() {return this.number;}

    /**
     * Установить позицию объекта
     * @param position позиция
     * @return установлена ли позиция
     */
    boolean setPosition(Cell position) {
        if (position != null && !canLocaleAtPosition(position)) return false;
        this.position = position;
        return true;
    }

    /**
     * Может ли объект располагаться в указанной позиции
     * @param cell позиция
     * @return может ли объект располагаться в указанной позиции
     */
    public abstract boolean canLocaleAtPosition(@NotNull Cell cell);
}
