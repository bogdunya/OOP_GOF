package game_of_fifteen.model.event;

import game_of_fifteen.model.GameStatus;
import game_of_fifteen.model.field.cell_objects.Tile;
import org.jetbrains.annotations.NotNull;

import java.util.EventObject;

/**
 * Объект события класса игры.
 */
public class GameActionEvent extends EventObject {
    /**
     * Костяшка
     */
    private Tile tile;
    /**
     * Статус игры
     */
    private GameStatus status;

    public GameActionEvent(Object source) {
        super(source);
    }

    /**
     * Установить Костяшку
     */
    public void setTile(@NotNull Tile tile) {
        this.tile = tile;
    }
    /**
     * Установить статус игры
     * @param status статус игры.
     */
    public void setStatus(GameStatus status) {
        this.status = status;
    }

    /**
     * Получить Костяшку
     * @return Костяшка
     */
    public Tile getTile() {
        return tile;
    }

    /**
     * Получить статус игры
     * @return статус игры
     */
    public GameStatus getStatus() {
        return status;
    }
}
