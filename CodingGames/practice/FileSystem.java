package practice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/*
 * @source 
 * @author xiaoque
 * @date 2025.03.22
 */
abstract class FileSystemNode {
    public abstract long getSize();

    public abstract String getName();

    public abstract void setName(String name);
}

class File extends FileSystemNode {
    private long size;
    private String name;

    public File(long size) {
        this.size = size;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getSize() {
        return size;
    }

}

class Directory extends FileSystemNode {
    private final Map<String, FileSystemNode> children;
    private String name;

    public Directory(String name) {
        this.name = name;
        children = new HashMap<>();
    }

    public List<FileSystemNode> getChildren() {
        return children.values().stream().collect(Collectors.toList());
    }

    public void addChild(String name, FileSystemNode node) {
        node.setName(name);
        children.put(name, node);
    }

    public FileSystemNode getChild(String name) {
        return children.get(name);
    }

    @Override
    public long getSize() {
        return this.getChildren().stream().map(FileSystemNode::getSize).mapToLong(Long::longValue).sum();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

public class FileSystem {
    public static long totalSize(Directory dir, String path) {
        // decompose the path by / , if last element is a file, then return the size
        // if its a folder then return its subsets
        Optional<FileSystemNode> node = Optional.ofNullable(findFileByPath(dir, path));
        return node.isPresent() ? node.get().getSize() : 0;

    }

    public static FileSystemNode findFileByPath(Directory dir, String path) {
        if (path == null || path.length() == 0)
            return dir;
        path = path.charAt(0) == '/' ? path.substring(1) : path;
        if (path == null || path.length() == 0)
            return dir;
        String[] pathArr = path.endsWith("/") ? path.substring(0, path.length() - 1).split("/") : path.split("/");
        FileSystemNode prev = dir;
        for (String currPath : pathArr) {
            if (prev instanceof Directory dirr) {
                prev = dirr.getChild(currPath);
            } else {
                return null;
            }
            if (prev == null)
                return null;
        }
        return prev;
    }

    public static void main(String[] args) {
        testFunc();
    }

    private static void testFunc() {
        Directory dir = FileSystem.buildSystem();
        BiFunction<Directory, String, Long> func = (x, y) -> FileSystem.totalSize(x, y);

        valid("all", func, dir, "/");
        valid("home", func, dir, "home/");
        valid("bin", func, dir, "/bin");
        valid("not found", func, dir, "xys");
        valid("find the file", func, dir, "/var/log/wifi.log");
    }

    private static void valid(String label, BiFunction<Directory, String, Long> func, Directory dir, String path) {
        System.out.println("The case " + label + " with path :" + path);
        System.out.println("Size: " + String.valueOf(func.apply(dir, path)));
    }

    public static Directory buildSystem() {
        Directory tot = new Directory("");

        Directory home = new Directory("home");

        Directory me = new Directory("me");
        me.addChild("foo.txt", new File(416));
        me.addChild("metrics.txt", new File(5892));

        Directory src = new Directory("src");
        src.addChild("site.html", new File(6051));
        src.addChild("site.css", new File(5892));
        src.addChild("data.csv", new File(332789));

        me.addChild("src", src);

        Directory you = new Directory("you");
        you.addChild("dict.json", new File(4913364));

        home.addChild("me", me);
        home.addChild("you", you);

        Directory bin = new Directory("bin");
        bin.addChild("bash", new File(618416));
        bin.addChild("cat", new File(23648));
        bin.addChild("ls", new File(38704));

        Directory var = new Directory("var");

        Directory log = new Directory("log");
        log.addChild("dmesg", new File(1783894));
        log.addChild("wifi.log", new File(924818));
        var.addChild("log", log);

        Directory httpd = new Directory("httpd");
        httpd.addChild("access.log", new File(17881));
        httpd.addChild("access.log.0.gz", new File(4012));

        var.addChild("httpd", httpd);

        tot.addChild("home", home);
        tot.addChild("bin", bin);
        tot.addChild("var", var);

        return tot;
    }

}
