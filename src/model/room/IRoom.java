package model.room;

import model.enums.RoomType;

public interface IRoom {
  public String getRoomNumber();
  public Double getRoomPrice();
  public RoomType getRoomType();
  public boolean isFree();
}
