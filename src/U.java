import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class U {//Utils

    public static String rootPath;

    private static W2vInterface w2VInterface;

    private static long multiStartTime;
    private static long multiEndTime;

    private static long singleStartTime;
    private static long singleEndTime;

    public static boolean notNullnotEmpty(Collection c) {
        if (c == null || c.isEmpty())
            return false;
        return true;
    }

    public static boolean isNullOrEmpty(Collection c) {
        if (c == null || c.isEmpty())
            return true;
        return false;
    }

    public static void startSingleTimer() {
        singleStartTime = System.nanoTime();
    }

    public static void stopSingleTimer() {
        singleEndTime = System.nanoTime();
    }

    public static String getTotalSingleTime() {
        return ((singleEndTime - singleStartTime) / 1000000) + " milliseconds (" + ((singleEndTime - singleStartTime) / 1000000000) + " seconds).";
    }

    public static void startMultiTimer() {
        multiStartTime = System.nanoTime();
    }

    public static void stopMultiTimer() {
        multiEndTime = System.nanoTime();
    }

    public static String getTotalMultiTime() {
        return ((multiEndTime - multiStartTime) / 1000000) + " milliseconds (" + ((multiEndTime - multiStartTime) / 1000000000) + " seconds).";
    }

    public static Random rand = new Random();

    public static W2vInterface getW2VInterface() {
        return w2VInterface;
    }

    public static void setW2VInterface(W2vInterface w2VInterface_in) {
        w2VInterface = w2VInterface_in;
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static void print(Object o1, Object o2) {
        print(o1);
        print(o2);
    }

    //TODO add handy timer methods here?

    /*
     * Credit to http://javatechniques.com/blog/faster-deep-copies-of-java-objects/
     */
    public static Object deepCopy(Object orig) {
        Object obj = null;
        try {
            // Write the object out to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(orig);
            out.flush();
            out.close();

            // Make an input stream from the byte array and read
            // a copy of the object back in.
            ObjectInputStream in = new ObjectInputStream(
                    new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return obj;
    }

    public static <T> String join(List<T> line, String delimiter) {
        StringBuilder str = new StringBuilder();
        boolean first = true;
        for (T object : line) {
            if(!first)
                str.append(delimiter);
            else
                first = false;
            str.append(object);
        }

        return str.toString();
    }

    public static String getPositionString(int i) {
        String posStr = "the ";
        if (i == -1)
        {
            posStr += "LAST";
        }
        else if (i == 0)
        {
            posStr += "FIRST";
        }
        else if (i == 1)
        {
            posStr += "SECOND";
        }
        else if (i == 2)
        {
            posStr += "THIRD";
        }
        else
        {
            posStr += (i+1) + "TH";
        }

        return posStr + " position";
    }

    public static <T extends Comparable<T>> Map<T, List<Integer>> sortByListSize(Map<T, List<Integer>> map, final boolean order) {
        List<Entry<T, List<Integer>>> list = new LinkedList<Entry<T, List<Integer>>>(map.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<T, List<Integer>>>()
        {
            public int compare(Entry<T, List<Integer>> o1,
                               Entry<T, List<Integer>> o2) {
                if (order) {
                    return o1.getValue().size() - o2.getValue().size();
                }
                else {
                    return o2.getValue().size() - o1.getValue().size();

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<T, List<Integer>> sortedMap = new LinkedHashMap<T, List<Integer>>();
        for (Entry<T, List<Integer>> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
