package game_of_fifteen.model;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Класс точки с координатами
 */
public class Point {

    /**
     * Координата X
     */
    private final int x;

    /**
     * Координата Y
     */
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Получить координату X
     * @return координата X
     */
    public int getX() {
        return this.x;
    }

    /**
     * Получить координату Y
     * @return координата Y
     */
    public int getY() {
        return y;
    }

    /**
     * Получить новую точку с заданным смещением в заданном направлении
     * @param direction направление
     * @param delta смещение
     * @return новая точка с заданным смещением в заданном направлении
     */
    public Point to(@NotNull Direction direction, int delta) {
        int newX = x;
        int newY = y;
        switch (direction) {
            case NORTH:
                newY -= delta;
                break;
            case SOUTH:
                newY += delta;
                break;
            case EAST:
                newX += delta;
            case WEST:
                newX -= delta;
                break;
        }
        return new Point(newX, newY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x" + x +
                ", y=" + y +
                '}';
    }
}
