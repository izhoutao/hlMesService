package cn;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.IOUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class Rename {
    public static void main(String[] args) throws IOException {
//		放入需要改变名字的文件绝对目录
        File file = new File("D:\\Download\\jsp\\666\\AAAAA");
        changename(file);
        rewrite(file);
    }

    //	改变目录下文件的名称
    static void changename(File OldFileName) throws IOException {
        if (OldFileName.isDirectory()) {
            File[] files = OldFileName.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    changename(files[i]);
                    String path = files[i].getParentFile().getAbsolutePath();
                    File NewFileName = new File(path + "\\Directory" + i);
                    files[i].renameTo(NewFileName);
                } else {
                    String path = files[i].getParentFile().getAbsolutePath();
                    //新文件的绝对路径拼接需要改为的名称与格式
                    File NewFileName = new File(path + "\\File" + i);
                    files[i].renameTo(NewFileName);
                    //BufferedWriter writer = FileUtil.getWriter(files[i], Charset.defaultCharset(), false);
                    //IOUtils.write("",writer);
                }
            }
        }
        System.out.println("finish");
    }


    //	改变目录下文件的名称
    static void rewrite(File OldFileName) throws IOException {
        if (OldFileName.isDirectory()) {
            File[] files = OldFileName.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    rewrite(files[i]);
                } else {
                    String path = files[i].getParentFile().getAbsolutePath();
                    BufferedWriter writer = FileUtil.getWriter(files[i], Charset.defaultCharset(), false);
                    IOUtils.write("",writer);
                }
            }
        }
        System.out.println("finish");
    }
}

