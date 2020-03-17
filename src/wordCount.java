import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class wordCount {
    public static void main(String[] args) throws IOException {
       // TODO Auto-generated method stub
        String str = null;
        while (true){
        System.out.println("请输入命令，格式：操作 文件路径");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()){
                str = scanner.nextLine();
            }
            String[] strings = str.split(" ");
            if (strings.length == 2){
           switch (strings[0]){
               case "-c":
                   System.out.println("字符数："+ getCharNum(strings[1]));
                   break;
               case "-w":
                   System.out.println("单词数："+ getWordNum(strings[1]));
                   break;
               case "-l":
                   System.out.println("行数："+ getLineNum(strings[1]));
                   break;
               default:
                   System.out.println("请输入正确命令格式.");
           }
       }else {
           System.out.println("请输入正确命令格式!");
       }
        }
    }




//获取行数
public static int getLineNum(String fileName) throws IOException {
   File file = new File(fileName);
   int num = 0;
   if (file.exists()){
       //读取文件
       FileReader fileReader = new FileReader(file);
       //读取行数
       LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
       while (lineNumberReader.readLine()!= null){
           num = lineNumberReader.getLineNumber();//获取行数并赋值给num
       }
       //关闭流，释放资源
       lineNumberReader.close();
       fileReader.close();
   }else {
       System.out.println("文件不存在，请重新输入！");
   }
   return num;

}

//获取文件字符数
public static int getCharNum(String fileName) throws IOException {
    int num = 0;
    File file = new File(fileName);
    if (file.exists()){
        int a = -1;
        FileReader fileReader = new FileReader(file);
        while ((a = fileReader.read())!= -1){//按字符读文件，判断是否符合，是则加一
            if (a!='\n'&& a!='\r'){
                num ++;
            }
        }
        fileReader.close();
    }else {
        System.out.println("文件不存在，请重新输入！");
    }

    return num;
}

//获取文件单词数
public static int getWordNum(String fileName) throws IOException {
    int num = 0;
    String[] strings = null;
    File file = new File(fileName);
    if (file.exists()){
       FileReader fileReader = new FileReader(fileName);
       BufferedReader is = new BufferedReader(fileReader);
       String line = null;
       StringBuffer sbf = new StringBuffer();
       while ((line = is.readLine())!= null){
           sbf.append(line);
           String str = sbf.toString();
           //正则表达式替换符号
            str = str.replaceAll("[\\p{Nd}\\u9fa5-\\uffe5\\p{Punct}\\s&&[^-]]"," ");
            //按空格将内容分割
           strings = str.split("\\s+");
           num = strings.length;
       }
       is.close();
       fileReader.close();
    }else {
        System.out.println("文件不存在，请重新输入！");
    }
    return num;
}

}