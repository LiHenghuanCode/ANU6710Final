1. Nodes + ArrayList of references to neighbours

（面向对象的图结构）

这是对象引用式（OOP风格）图结构：
每个节点是一个对象，每个对象持有对邻居节点的引用。

结构示例
```java
class Node {
String value;
List<Node> neighbours = new ArrayList<>();

    Node(String value) { this.value = value; }
}
```

2. Adjacency Lists with vertex identifiers


```java
Map<String, List<String>> graph = new HashMap<>();

graph.put("A", List.of("B", "C"));
graph.put("B", List.of("D"));
graph.put("C", List.of());
```
另外维护顶点值：
```java
Map<String, Integer> values = new HashMap<>();
values.put("A", 10);
values.put("B", 20);
```


3. Adjacency Matrix with vertex identifiers

```java
Map<String, Integer> valueMap;
```

