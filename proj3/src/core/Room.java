package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static utils.RandomUtils.uniform;

public class Room {
    private final Random random;
    private List<Point> roomArea;
    private int wb; // width begin
    private int we; // width end
    private int hb; // height begin
    private int he; // height end

    public Room(Random random) {
        this.random = random;
        this.wb = uniform(random, 0, Setting.WIDTH - Setting.ROOM_MAX_SIZE);
        this.we = uniform(random, wb + Setting.ROOM_MIN_SIZE, wb + Setting.ROOM_MAX_SIZE);
        this.hb = uniform(random, 0, Setting.HEIGHT - Setting.ROOM_MAX_SIZE);
        this.he = uniform(random, hb + Setting.ROOM_MIN_SIZE, hb + Setting.ROOM_MAX_SIZE);
        roomArea = new ArrayList<>();
        this.generate();
    }

    private void generate() {
        for (int i = wb; i <= we; i++) {
            for (int j = hb; j <= he; j++) {
                roomArea.add(new Point(i, j));
            }
        }
    }

    public boolean isOverlapOthers(List<Room> rooms) {
        for (Room r: rooms) {
            for (Point p: r.roomArea) {
                if (this.isContainPoint(p)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isWithinBorders(Room r) {
        int adjust = 4;
        for (Point point : r.roomArea) {
            if (point.getX() < adjust || point.getX() > Setting.WIDTH - adjust
                    || point.getY() < adjust || point.getY() > Setting.HEIGHT - adjust) {
                return false;
            }
        }
        return true;
    }
    private boolean isContainPoint(Point p) {
        for (Point point: this.roomArea) {
            if (point.equal(p)) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getRoomArea() {
        return roomArea;
    }

    public int getWb() {
        return wb;
    }

    public int getWe() {
        return we;
    }

    public int getHb() {
        return hb;
    }

    public int getHe() {
        return he;
    }

    public Point getRandomPoint() {
        Point point = roomArea.get(uniform(random, 0, roomArea.size()));
        while (point.getX() == 0 || point.getX() == Setting.WIDTH || point.getY() == 0
                || point.getY() == Setting.HEIGHT) {
            point = roomArea.get(uniform(random, 0, roomArea.size()));
        }
        return point;
    }
}
