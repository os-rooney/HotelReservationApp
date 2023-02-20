package model.enums;

public enum RoomType {
  SINGLE("1"), DOUBLE("2");

  public final String type;

  private RoomType(String type) {
    this.type = type;
  }

  public static RoomType getType(String type) {
    for (RoomType roomType : values()) {
      if (roomType.type.equals(type)) {
        return roomType;
      }
    }
    throw new IllegalArgumentException();
  }
}