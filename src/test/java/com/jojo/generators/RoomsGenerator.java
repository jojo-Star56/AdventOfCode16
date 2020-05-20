package com.jojo.generators;

import com.jojo.Day4.Rooms;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;


public class RoomsGenerator extends Generator<Rooms> {
    private int lowerNumOfRooms = 10;
    private int upperNumOfRooms = 200;
    private int lowerSecId = 100;
    private int upperSecId = 1000;
    private int lowerNumOfSec = 100;
    private int upperNumOfSec = 1000;
    private char lowerChar = 'a';
    private char upperChar = 'z';

    public RoomsGenerator() {
        super(Rooms.class);
    }

    @Override
    public Rooms generate(SourceOfRandomness sourceOfRandomness, GenerationStatus generationStatus){
        Rooms rooms = new Rooms();
        int numberOfRooms = sourceOfRandomness.nextInt(lowerNumOfRooms, upperNumOfRooms);
        int targetPosition = sourceOfRandomness.nextInt(0, numberOfRooms - 1);
        // 1.generate each encrypted room string
        for(int i = 0; i < numberOfRooms; i ++){
            if(i == targetPosition){
                // generate encrypted target name string from origin- "northpole object storage"
                String origin = "northpole object storage";
                StringBuilder encryped = new StringBuilder();
                int secId = sourceOfRandomness.nextInt(lowerSecId, upperSecId);
                int move = secId%26;
                for(int j = 0; j < origin.length(); j ++){
                    if(origin.charAt(j) == ' '){
                        encryped.append('-');
                        continue;
                    }
                    int pos = origin.charAt(j) - 'a' - move;

                    if(pos < 0){
                        pos = 26 + pos;
                    }
                    char c = (char)(pos + 'a');
                    encryped.append(c);
                }
                rooms.addRoom(encryped.toString() + "-" + secId +"[lbqod]");
                continue;
            }

            StringBuilder room = new StringBuilder();
            int numberOfNameSections = sourceOfRandomness.nextInt(lowerNumOfSec, upperNumOfSec);

            // 2.generate each encrypted room name
            for(int j = 0; j < numberOfNameSections; j ++){
                int sectionLength = sourceOfRandomness.nextInt(1, 10);
                for(int m = 0; m < sectionLength; m ++){
                    char c = sourceOfRandomness.nextChar(lowerChar, upperChar);
                    room.append(c);
                }
                room.append('-');
            }
            // 3.generate each sectorID
            int sectorId = sourceOfRandomness.nextInt(lowerSecId, upperSecId);
            room.append(sectorId);

            // 4.generate each checkSum
            StringBuilder checkSum = new StringBuilder();
            int checkSumLength = sourceOfRandomness.nextInt(1, 10);
            for(int m = 0; m < checkSumLength; m ++){
                char c = sourceOfRandomness.nextChar(lowerChar, upperChar);
                checkSum.append(c);
            }
            room.append("[" + checkSum.toString() + "]");
            rooms.addRoom(room.toString());
        }

        return rooms;
    }

    public void configure(int lowerNumOfRooms, int upperNumOfRooms, int lowerSecId, int upperSecId, int lowerNumOfSec, int upperNumOfSec, char lowerChar, char upperChar) {
        this.lowerNumOfRooms = lowerNumOfRooms;
        this.upperNumOfRooms = upperNumOfRooms;
        this.lowerSecId = lowerSecId;
        this.upperSecId = upperSecId;
        this.lowerNumOfSec = lowerNumOfSec;
        this.upperNumOfSec = upperNumOfSec;
        this.lowerChar = lowerChar;
        this.upperChar = upperChar;
    }
}
