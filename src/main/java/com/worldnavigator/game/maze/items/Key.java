package com.worldnavigator.game.maze.items;

public enum Key implements Item {
  COPPER {
    @Override
    public String getName() {
      return "copperKey";
    }

    @Override
    public String accept(ItemVisitor itemVisitor) {
      return itemVisitor.visitKey(Key.COPPER);
    }

    @Override
    public String toString() {
      return "copperKey";
    }
  },
  SILVER {
    @Override
    public String getName() {
      return "silverKey";
    }

    @Override
    public String accept(ItemVisitor itemVisitor) {
      return itemVisitor.visitKey(Key.SILVER);
    }

    @Override
    public String toString() {
      return "silverKey";
    }
  },
  GOLD {
    @Override
    public String getName() {
      return "goldKey";
    }

    @Override
    public String accept(ItemVisitor itemVisitor) {
      return itemVisitor.visitKey(Key.GOLD);
    }
  },
  NULL {
    @Override
    public String getName() {
      return "null";
    }

    @Override
    public String accept(ItemVisitor itemVisitor) {
      return itemVisitor.visitKey(Key.NULL);
    }

    @Override
    public String toString() {
      return "nullKey";
    }
  };
}
