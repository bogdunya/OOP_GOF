package game_of_fifteen.model.event;

import org.jetbrains.annotations.NotNull;

import java.util.EventListener;

/**
 * Интерфейс слушателя события класса коза.
 */
public interface TileActionListener extends EventListener {
    /**
     * Костяшка переместилась.
     * @param event объект события класса костяшка.
     */
    void tileIsMoved(@NotNull TileActionEvent event);

    /**
     * Состояние активности костяшки изменилось.
     * @param event объект события класса костяшка.
     */
    void tileActivityChanged(@NotNull TileActionEvent event);
}
