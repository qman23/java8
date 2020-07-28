package cn.com.lee.file;


import java.io.*;

public class FileDemo {
    @FunctionalInterface
    interface FileProcessor{
        String process(BufferedReader br) throws IOException;
    }

    private static String processFile(FileProcessor p,File f) throws FileNotFoundException,IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            return p.process(br);
        }
    }
    public static void main(String[] args) throws IOException {
        File[] files = new File(".").listFiles((File dir, String name)->name.endsWith("xml"));
        for(File f:files){
            System.out.println(f.getName());
            System.out.println(processFile((BufferedReader br)->{
                br.lines().forEach(System.out::println);
                return "";
            },f));
        }
    }
}
