package game_of_fifteen.model;

import game_of_fifteen.model.event.*;
import game_of_fifteen.model.field.Field;
import game_of_fifteen.model.field.cell_objects.Cabbage;
import game_of_fifteen.model.field.cell_objects.Tile;
import game_of_fifteen.model.field_formation.FieldFormation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Игра.
 */
public class Game {
    /**
     * Статус игры.
     */
    private GameStatus gameStatus;

    /**
     * Костяшка
     */
    private List<Tile> tile;
    /**
     * Игровое поле.
     */
    private Field gameField;

    /**
     * Конструктор.
     * @param fieldFormation лабиринт, содержащий расстановку элементов на поле.
     */
    public Game(FieldFormation fieldFormation) {
        startGame(fieldFormation);
    }

    /**
     * Старт новой игры.
     * @param fieldFormation лабиринт, содержащий расстановку элементов на поле.
     */
    public void startGame(@NotNull FieldFormation fieldFormation) {
        setStatus(GameStatus.GAME_IS_ON);

        buildField(fieldFormation);
        setTile(gameField.getTilesOnField());

        //gameField.getTilesOnField().addTileActionListener(new TileObserver()); //Возможно это важная строчка!


    }

    /**
     * Прервать игру
     */
    public void abort() {
        setStatus(GameStatus.GAME_ABORTED);
    }

    /**
     * Получить текущий статус игры.
     * @return текующий статус игры.
     */
    public GameStatus getStatus() {
        return gameStatus;
    }

    /**
     * Установить текущий статус игры.
     * @param status текущий статус игры.
     */
    private void setStatus(GameStatus status) {
        if (gameStatus != status) {
            gameStatus = status;
            fireGameStatusIsChanged(gameStatus);
        }
    }

    /**
     * Получить Костяшка.
     * @return Костяшка.
     */
    public List<Tile> getTile() {
        return tile;
    }
    public void setTile(List<Tile> tile) {
        this.tile = tile;
    }
    /**
     * Получить игровое поле.
     * @return игровое поле.
     */
    public Field getGameField() {
        return gameField;
    }

    /**
     * Обновить состояние игры.
     */
    private void updateGameStatus() {
        GameStatus status = determineOutcomeGame();
        setStatus(status);
    }

    /**
     * Определить исход игры.
     * @return статус игры.
     */
    private GameStatus determineOutcomeGame() {
        GameStatus result = GameStatus.GAME_IS_ON;

        List<Tile> tileOnField = gameField.getTilesOnField();
        Cabbage cabbage = gameField.getCabbageOnField();

//        if (goatOnField.getNumberMoves() <= 0) {
//            result = GameStatus.GOAT_NOT_HAVE_MOVES;
//        } else if (cabbage.getPosition() == goatOnField.getPosition()) {
//            result = GameStatus.WIN;
//        }

        return result;
    }

    /**
     * Построить игровое поле.
     * @param fieldFormation лабиринт, содержащий расстановку элементов на поле.
     */
    private void buildField(@NotNull FieldFormation fieldFormation) {
        gameField = fieldFormation.buildField();
    }

    /**
     * Класс, реализующий наблюдение за событиями.
     */

    private class TileObserver implements TileActionListener {
        @Override
        public void tileIsMoved(@NotNull TileActionEvent event) {
            fireTileIsMoved(event.getTile());
            updateGameStatus();
        }

        @Override
        public void tileActivityChanged(@NotNull TileActionEvent event) {
            // Not implemented yet
        }
    }

    /**
     * Список слушателей, подписанных на события игры.
     */
    private final ArrayList<GameActionListener> gameActionListeners = new ArrayList<>();

    /**
     * Добавить нвоого слушателя за событиями игры.
     * @param listener слушатель.
     */
    public void addGameActionListener(@NotNull GameActionListener listener) {
        gameActionListeners.add(listener);
    }

    /**
     * Удалить слушателя за событиями игры.
     * @param listener слушатель.
     */
    public void removeGameActionListener(@NotNull GameActionListener listener) {
        gameActionListeners.remove(listener);
    }

    /**
     * Оповестить слушателей, что коза переместилась.
     * @param goat коза, которая переместилась.
     */

    public void fireTileIsMoved(@NotNull Tile tile) {
        for (GameActionListener listener : gameActionListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            event.setTile(tile);
            listener.tileIsMoved(event);
        }
    }

    /**
     * Оповестить сулшателей {@link Game#gameActionListeners}, что статус игры изменился.
     * @param status статус игры.
     */
    public void fireGameStatusIsChanged(@NotNull GameStatus status) {
        for (GameActionListener listener : gameActionListeners) {
            GameActionEvent event = new GameActionEvent(listener);
            event.setStatus(status);
            listener.gameStatusChanged(event);
        }
    }
}
