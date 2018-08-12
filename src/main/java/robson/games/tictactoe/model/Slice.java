package robson.games.tictactoe.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Slice {

    private List<Field> fields;

    public Slice() {
        this.fields = new ArrayList<>();
    }

    public void add(Field piece) {
        this.fields.add(piece);
    }

    public List<Field> getFields() {
        return this.fields;
    }

    public boolean hasWinner() {

        //empty path?
        if (this.fields == null || this.fields.isEmpty()) {
            return false;
        }

        //uncompleted path?
        if (!isFull()) {
            return false;
        }

        //unmatched slice?
        Field first = fields.get(0);
        for (Field piece : this.fields) {
            if (!first.getAssigned().equals(piece.getAssigned())) {
                return false;
            }
        }

        return true;

    }

    public boolean isUnworthy() {
        return isFull() && !hasWinner();
    }

    public boolean isFull() {
        for (Field field : this.fields) {
            if (!field.isAssigned()) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        for (Field field : this.fields) {
            if (field.isAssigned()) {
                return false;
            }
        }
        return true;
    }

    public int countFreeFields() {
        int result = 0;
        for (Field field : this.fields) {
            if (!field.isAssigned()) {
                result++;
            }
        }
        return result;
    }

    public Field getFirstFreeField() {
        for (Field field : this.fields) {
            if (!field.isAssigned()) {
                return field;
            }
        }
        return null;
    }

    public Field getRandomFreeField() {
        List<Field> freeFields = new ArrayList<>();
        for (Field field : this.fields) {
            if (!field.isAssigned()) {
                freeFields.add(field);
            }
        }
        if (freeFields.isEmpty()) {
            return null;
        }
        Collections.shuffle(freeFields);
        return freeFields.get(0);
    }

    public boolean containsOnly(Player player) {
        if (this.fields == null || this.fields.isEmpty() || this.isEmpty()) {
            return false;
        }

        for (Field field : this.fields) {
            if (field.getAssigned() == null) {
                continue;
            }
            if (!player.equals(field.getAssigned())) {
                return false;
            }
        }
        return true;
    }

    public boolean containsSamePlayerOnly() {
        if (this.fields == null || this.fields.isEmpty() || this.isEmpty()) {
            return false;
        }

        Player firstNotNull = null;
        for (Field field: this.fields) {
            if (field.getAssigned() != null) {
                firstNotNull = field.getAssigned();
                break;
            }
        }
        for (Field piece : this.fields) {
            if (piece.getAssigned() == null) {
                continue;
            }
            if (!firstNotNull.equals(piece.getAssigned())) {
                return false;
            }
        }
        return true;
    }
}

