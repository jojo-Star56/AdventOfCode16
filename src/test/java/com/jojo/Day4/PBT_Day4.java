package com.jojo.Day4;

import com.jojo.generators.RoomsGenerator;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.hamcrest.beans.SamePropertyValuesAs;
import static org.junit.Assert.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@RunWith(JUnitQuickcheck.class)
public class PBT_Day4 extends TestCase {
    private static Day4 day4;
    @BeforeClass
    public static void init(){
        day4 = new Day4();
    }

    @Property   //(trails = 100000) RoomsGenerator can generate random encrypted room name which will be decrypted as "northpole object storage", need more time to code gurantee real room generation.
    public void testfindSumAndTargetReturnsCorrectSumAndTarget(@From(RoomsGenerator.class) Rooms rms) {
        List<String> rooms = rms.getRooms();
        Day4.ResultType res;
        int sumOfId = 0;
        int targetSectorId = -1;
        for(String room : rooms){
            // Prepare every thing
            PriorityQueue<Day4.FreqPair> pq = new PriorityQueue(new Comparator<Day4.FreqPair>() {
                @Override
                public int compare(Day4.FreqPair o1, Day4.FreqPair o2) {
                    return o2.freq - o1.freq != 0 ? o2.freq - o1.freq : (o2.ch < o1.ch ? 1 : -1);
                }
            });
            int[] freq = new int[26];
            String[] name = room.split("-");
            String[] rest = name[name.length - 1].split("\\[");
            int sectorId = Integer.parseInt(rest[0]);
            String checksum = rest[1].substring(0, rest[1].length() - 1);

            for(int i = 0; i < name.length - 1; i ++){
                String s = name[i];
                for(int j = 0; j < s.length(); j ++){
                    char c = s.charAt(j);
                    freq[c - 'a'] ++;
                }
            }
            for(int i = 0; i < freq.length; i ++){
                if(freq[i] > 0){
                    char c = (char)(i+'a');
                    Day4.FreqPair fp = new Day4().new FreqPair(c, freq[i]);
                    pq.add(fp);
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 5; i ++){
                Day4.FreqPair fp = pq.poll();
                sb.append(fp.ch);
            }
            if(sb.toString().equals(checksum)){
                sumOfId += sectorId;
            }
            String targetName = "northpole object storage";
            if(name.length - 1 != 3 || name[0].length() != 9 || name[1].length() != 6 || name[2].length() != 7){
                continue;
            }
            StringBuilder decryped = new StringBuilder();
            for(int i = 0; i < name.length - 1; i ++){
                int move = sectorId%26;
                String s = name[i];
                for(int j = 0; j < s.length(); j ++){
                    char c = (char)((s.charAt(j) - 'a' + move)%26 + 'a');
                    decryped.append(c);
                }
                if(i != name.length - 2){
                    decryped.append(' ');
                }
            }

            if(decryped.toString().equals(targetName)) {
                targetSectorId = sectorId;
            }
        }
        res =  new Day4.ResultType(sumOfId, targetSectorId);

        assertThat(res, SamePropertyValuesAs.samePropertyValuesAs(day4.findSumAndTarget(rooms)));
    }
}