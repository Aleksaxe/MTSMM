package FileWorker;

import java.io.File;

public class XmlGrab {
    public static File[] getFiles() {
        File folder = new File("E:\\!distrib\\git\\MTS\\fileTreeTest1");
        File[] files=folder.listFiles();
        assert files != null;
        return files;
    }
}
