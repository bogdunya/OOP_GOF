package game_of_fifteen.model.event;

import org.jetbrains.annotations.NotNull;

import java.util.EventListener;

/**
 * Интерфейс слушателя события для объекта костяшка
 */
public interface TileActionListener extends EventListener {
    /**
     * Костяшка переместилась
     * @param event объект события класса костяшка
     */
    void tileIsMoved(@NotNull TileActionEvent event);
}
