package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.util.*;

public class World {

    private TETile[][] world;

    private List<Room> rooms;
    private List<Point> paths;
    private final Random random;
    public World(String input) {

        this.random = new Random(stringToSeed(input));
//        this.random = new Random(69960558);
        this.world = new TETile[Setting.WIDTH][Setting.HEIGHT];
        this.rooms = new ArrayList<>();
    }

    /**
     * Converts a string to a seed value.
     * The seed value is calculated by summing the ASCII values of all letters in the input string.
     * Non-letter characters are ignored.
     *
     * @param input The string to be converted to a seed value
     * @return The calculated seed value
     */
    private static long stringToSeed(String input) {
        long seed = 0;
        for (int i = 0; i < input.length(); i++) {
            char b = input.charAt(i);
            if (Character.isLetter(b)) {
                seed += (int) b;
            }
        }
        for (int i = 0; i < input.length(); i++) {
            int a = input.charAt(i);
            if (Character.isDigit(a)) {
                seed += a;
            }
        }
        for (int i = 1; i < input.length(); i = i * 2) {
            int x = input.charAt(i);
            if (Character.isDigit(x)) {
                if (x < 40) {
                    seed += 8;
                }
                if (x % 4 == 0) {
                    seed += seed;
                } else if (x < 50) {
                    seed -= x;
                } else if (x < 55) {
                    seed = seed * 3;
                } else {
                    seed += x;
                }
            }
        }
        return seed;
    }

    /**
     * Generate world
     * @return TETile
     */
    public TETile[][] generate() {
        // initialize tiles
        for (int x = 0; x < Setting.WIDTH; x++) {
            for (int y = 0; y < Setting.HEIGHT; y++) {
                this.world[x][y] = Tileset.NOTHING;
            }
        }

        // generate Room
        roomGenerate();


        // generate path between room
        pathGenerate();


        return world;
    }

    private void roomGenerate() {

        for (int i = 0; i < Setting.ROOM_MAX_NUMBER; i++) {
            Room room = new Room(this.random);
            if (!room.isOverlapOthers(rooms) && room.isWithinBorders(room)) {
                this.rooms.add(room);
                writeRoomsToWorld(room);
            }

        }

    }

    private void writeRoomsToWorld(Room room) {
        for (Point p: room.getRoomArea()) {
            world[p.getX()][p.getY()] = Tileset.FLOOR;
            if (p.getX() == room.getWb() || p.getX() == room.getWe() || p.getY() == room.getHb()
                    || p.getY() == room.getHe()) {
                world[p.getX()][p.getY()] = Tileset.WALL;
            }
        }

    }


    private void pathGenerate() {
        // add new method to sort room list
        this.rooms = sortRoomList();

        for (int i = 0; i < rooms.size() - 1; i++) {
            generatePathBetweenRoom(rooms.get(i), rooms.get(i + 1));
            writePathToWorld(paths);
        }
    }

    private List<Room> sortRoomList() {
        rooms.sort((o1, o2) -> o1.getWe() - o2.getWe());
        return this.rooms;
    }


    private void writePathToWorld(List<Point> path) {
        for (Point p: path) {
            world[p.getX()][p.getY()] = Tileset.FLOOR;
            if (world[p.getX() + 1][p.getY()] == Tileset.NOTHING) {
                world[p.getX() + 1][p.getY()] = Tileset.WALL;
            }
            if (world[p.getX()][p.getY() + 1] == Tileset.NOTHING) {
                world[p.getX()][p.getY() + 1] = Tileset.WALL;
            }
            if (world[p.getX() - 1][p.getY()] == Tileset.NOTHING) {
                world[p.getX() - 1][p.getY()] = Tileset.WALL;
            }
            if (world[p.getX()][p.getY() - 1] == Tileset.NOTHING) {
                world[p.getX()][p.getY() - 1] = Tileset.WALL;
            }
        }
    }

    private void generatePathBetweenRoom(Room room1, Room room2) {
        Point p1 = room1.getRandomPoint();
        Point p2 = room2.getRandomPoint();
        paths = new ArrayList<>();
        int xMin = Math.min(p1.getX(), p2.getX());
        int xMax = Math.max(p1.getX(), p2.getX());
        int yMin = Math.min(p1.getY(), p2.getY());
        int yMax = Math.max(p1.getY(), p2.getY());

        for (int i = xMin; i <= xMax; i++) {
            paths.add(new Point(i, p1.getY()));
        }
        for (int i = yMin; i <= yMax; i++) {
            paths.add(new Point(p2.getX(), i));
        }
    }
}
