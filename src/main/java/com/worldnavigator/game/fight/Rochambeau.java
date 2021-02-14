package com.worldnavigator.game.fight;

public enum Rochambeau {
    ROCK{
        @Override
        public boolean beats(Rochambeau rochambeau) {
            return rochambeau == SCISSOR;
        }
    },
    PAPER{
        @Override
        public boolean beats(Rochambeau rochambeau) {
            return rochambeau == ROCK;
        }
    },
    SCISSOR{
        @Override
        public boolean beats(Rochambeau rochambeau) {
            return rochambeau == PAPER;
        }
    };

    public abstract boolean beats(Rochambeau rochambeau);
}
