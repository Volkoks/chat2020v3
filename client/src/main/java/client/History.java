package client;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class History {
    private static PrintWriter out;

    private static String getHistoryUser(String login){
        return "historyChat/history_"+ login +".txt";
    }

    public static void start(String login){
        try {
            out = new PrintWriter(new FileOutputStream(getHistoryUser(login), true), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void stop(){
        if (out != null){
            out.close();
        }
    }

    public static void writeLine(String msg){
        out.println(msg);
    }

    public static String getLost100Post(String login) {
        File f = new File(getHistoryUser(login));
        if (!f.exists()) {
            return " ";
        }
        StringBuilder sb = new StringBuilder();
        try {
            List<String> historyList = Files.readAllLines(Paths.get(getHistoryUser(login)));
            int startpositon = 0;
            if (historyList.size() > 100){
                startpositon = historyList.size() - 100;
            }
            for (int i = startpositon; i < historyList.size() ; i++) {
                sb.append(historyList.get(i) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



}
