package com.jojo.Day4;

import com.jojo.util.Util;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Day4 {
    public static void main(String[] args) {
        List<String> rooms = Util.load("src/main/resources/day4.txt");
        Day4 day4 = new Day4();
        ResultType res = day4.findSumAndTarget(rooms);
        System.out.println(res.sumOfId);
        System.out.println(res.targetSectorId);
    }

    public ResultType findSumAndTarget(List<String> rooms){
        int sumOfId = 0;
        int targetSectorId = -1;
        for(String room : rooms){
            // Prepare every thing
            PriorityQueue<FreqPair> pq = new PriorityQueue(new Comparator<FreqPair>() {
                @Override
                public int compare(FreqPair o1, FreqPair o2) {
                    return o2.freq - o1.freq != 0 ? o2.freq - o1.freq : (o2.ch < o1.ch ? 1 : -1);
                }
            });
            int[] freq = new int[26];
            String[] name = room.split("-");
            String[] rest = name[name.length - 1].split("\\[");
            int sectorId = Integer.parseInt(rest[0]);
            String checksum = rest[1].substring(0, rest[1].length() - 1);

            // Part1 1-2. get char frequency and put char frequency to PriorityQueue
            sortCharFreqOfEachEncryptedName(name, freq, pq);

            // Part1 3. find sum of realRoom sectorId
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 5; i ++){
                FreqPair fp = pq.poll();
                sb.append(fp.ch);
            }
            if(sb.toString().equals(checksum)){
                sumOfId += sectorId;
            }



            // Part2 1.skip rooms that stand no chance to be the answer
            String targetName = "northpole object storage";
            if(name.length - 1 != 3 || name[0].length() != 9 || name[1].length() != 6 || name[2].length() != 7){
                continue;
            }
            // Part2 2.decrypt name of each room
            String decrypted = decrypt(name, sectorId);

            // printed every decrypted name to find no one is "north pole objects", only found "northpole object storage", so checked this string and magically passed test
            //System.out.println(decryped.toString());
            if(decrypted.equals(targetName)) {
                targetSectorId = sectorId;
                //System.out.println(room);
            }
        }
        // build result
        System.out.println(sumOfId);
        System.out.println(targetSectorId);
        return new ResultType(sumOfId, targetSectorId);
    }

    public void sortCharFreqOfEachEncryptedName(String[] name, int[] freq, PriorityQueue<FreqPair> pq) {
        // Part1 1.get char frequency
        for(int i = 0; i < name.length - 1; i ++){
            String s = name[i];
            for(int j = 0; j < s.length(); j ++){
                char c = s.charAt(j);
                freq[c - 'a'] ++;
            }
        }
        // Part1 2.put char frequency to PriorityQueue
        for(int i = 0; i < freq.length; i ++){
            if(freq[i] > 0){
                char c = (char)(i+'a');
                FreqPair fp = new FreqPair(c, freq[i]);
                pq.add(fp);
            }
        }
    }

    public String decrypt(String[] name, int sectorId) {
        StringBuilder decrypted = new StringBuilder();
        for(int i = 0; i < name.length - 1; i ++){
            int move = sectorId%26;
            String s = name[i];
            for(int j = 0; j < s.length(); j ++){
                char c = (char)((s.charAt(j) - 'a' + move)%26 + 'a');
                decrypted.append(c);
            }
            if(i != name.length - 2){
                decrypted.append(' ');
            }
        }
        return decrypted.toString();
    }


    public static class ResultType{
        public int sumOfId;
        public int targetSectorId;
        public ResultType(int sum, int target){
            sumOfId = sum;
            targetSectorId = target;
        }
    }

    public class FreqPair {
        public char ch;
        public int freq;
        public FreqPair(char c, int fr) {
            ch = c;
            freq = fr;
        }

        public char getCh() {
            return ch;
        }

        public int getFreq() {
            return freq;
        }
    }
}
