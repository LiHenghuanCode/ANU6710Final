# Java 文件系统操作知识总结

## 一、java.io.File 类

### 1. 创建 File 对象（表示路径）

```java
import java.io.File;

File file1 = new File("data.txt");      // 当前目录下的文件
File file2 = new File(".");             // 当前目录
File file3 = new File("../config.txt"); // 上级目录中的文件
```

### 2. 常用方法

| 方法 | 返回类型 | 作用 |
|------|---------|------|
| `file.exists()` | boolean | 文件/目录是否存在 |
| `file.isDirectory()` | boolean | 是否为目录 |
| `file.isFile()` | boolean | 是否为普通文件 |
| `file.getParentFile()` | File | 获取父目录 |
| `file.listFiles()` | File[] | 列出目录中所有文件 |
| `file.mkdir()` | boolean | 创建目录 |
| `file.mkdirs()` | boolean | 创建目录（包括所有必需的父目录）|

**注意**：`iff` = "if and only if"（当且仅当）

---

## 二、读取文本文件

### 标准模板

```java
try (var reader = new BufferedReader(new FileReader(file))) {
    for (String line = reader.readLine(); line != null; 
         line = reader.readLine()) {
        // 处理每一行
        System.out.println(line);
    }
} catch (Exception e) {
    throw new RuntimeException(e);
}
```

### 关键点

1. **`try-with-resources`**：`try(...)` 语法自动关闭资源
2. **`readLine()`**：
    - 读取一行文本
    - 到达文件末尾时返回 `null`
    - 用 `line != null` 判断是否读完
3. **异常处理**：
    - Java 强制处理某些错误（"unhandled exceptions"）
    - `throw new RuntimeException(e)` 让程序直接崩溃（简单处理方式）

---

## 三、写入文本文件

### 标准模板

```java
try (var writer = new BufferedWriter(new FileWriter(file))) {
    writer.write("Hello");
    writer.newLine();  // 方法1：添加换行符
    writer.write("World\n");  // 方法2：直接写 \n
} catch (Exception e) {
    throw new RuntimeException(e);
}
```

### 关键点

1. **两种换行方式**：
    - `writer.newLine()`：跨平台（Windows 上可能是 `\r\n`）
    - `writer.write("\n")`：Unix 风格（通用）
    - **建议**：选一种，保持一致

2. **Windows vs Unix**：
    - Windows 传统换行：`\r\n`
    - Unix/Linux/Mac：`\n`
    - 现代 Windows 也能识别 `\n`

---

## 四、完整示例

### 读取文件并统计行数

```java
import java.io.*;

public class FileExample {
    public static void main(String[] args) {
        File file = new File("data.txt");
        
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        
        int lineCount = 0;
        try (var reader = new BufferedReader(new FileReader(file))) {
            for (String line = reader.readLine(); line != null; 
                 line = reader.readLine()) {
                lineCount++;
                System.out.println("第" + lineCount + "行: " + line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        System.out.println("总共 " + lineCount + " 行");
    }
}
```

### 写入文件示例

```java
import java.io.*;

public class WriteExample {
    public static void main(String[] args) {
        File file = new File("output.txt");
        
        try (var writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("第一行");
            writer.newLine();
            writer.write("第二行");
            writer.newLine();
            writer.write("第三行");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        System.out.println("写入完成");
    }
}
```

---

## 五、核心概念总结

| 概念 | 说明 |
|------|------|
| **File 对象** | 表示文件路径，不是文件内容 |
| **BufferedReader** | 高效读取文本，按行读取 |
| **BufferedWriter** | 高效写入文本 |
| **try-with-resources** | 自动关闭资源，避免内存泄漏 |
| **异常处理** | Java 强制处理 I/O 异常 |
| **readLine()** | 返回 null 表示文件结束 |

---

## 六、常见用法模式

```java
// 1. 检查文件是否存在
if (file.exists() && file.isFile()) {
    // 读取文件
}

// 2. 列出目录中的所有文件
File dir = new File(".");
File[] files = dir.listFiles();
for (File f : files) {
    System.out.println(f.getName());
}

// 3. 创建目录
File newDir = new File("data/backup");
newDir.mkdirs();  // 创建 data 和 backup 两级目录
```

这些是 Java 文件操作的基础，掌握这些就能处理大多数文件读写任务了！
