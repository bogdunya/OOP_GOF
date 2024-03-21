package game_of_fifteen.model.field;

/**
 * Перемещаемый объект ячейки.
 */
public abstract class MobileCellObject extends CellObject{
    /**
     * Переместить объект в заданном направлении.
     */
    public abstract void move();

    /**
     * Может ли объект переместиться в заданном направлении.
     * @return соседнюю ячейку, если объект может переместиться в заданном направлении. Иначе null
     */
    protected abstract Cell canMove();
}
