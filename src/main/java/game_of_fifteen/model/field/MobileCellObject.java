package game_of_fifteen.model.field;

/**
 * Объект, способный перемещаться
 */
public abstract class MobileCellObject extends CellObject{

    /**
     * Переместить объект
     */
    public abstract void move();

    /**
     * Может ли объект переместиться в заданном направлении
     */
    protected abstract Cell canMove();
}
