package com.gmail.miv.task2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SaveTo(fileName = "src/com/gmail/miv/task2/TextContainer.txt")
public class TextContainer {

    public String text;

    public TextContainer(String text) {
        this.text = text;
    }

    @Saver
    public void save(String filePath) {

        try {
            FileWriter fw = new FileWriter(new File(filePath));
            fw.write(text);

            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
