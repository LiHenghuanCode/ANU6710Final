# File System Tree Representation Problem

## Problem Overview

Implement a file system tree representation system that captures the state of a file system at a particular point in time and provides functionality for querying and comparing file system structures.

## Core Concepts

The system needs to represent nodes in a file system, including three types:

1. **Directories**: Contain child nodes (files and subdirectories)
2. **Text Files**: Files whose names end with `.txt`, assumed to contain text data
3. **Non-text Files**: All other files

## Interface Definition: `FsNode`

### Key Properties

- **Point-in-time Snapshot**: The FsNode representation reflects the state of the file system at the time the instance was created. Any subsequent changes to the file system are not reflected in the object.
- **Tree Structure**: All nodes in the subtree can be accessed from any given node

### Equality Definition

The system must support the following default notion of equality:

- **Directories**: Two directories are equal if their names are equal and all contained files and directories are equal
- **Text Files**: Two text files are equal if their names are equal and they contain the same text content (all lines of text are equal)
- **Non-text Files**: Two non-text files are equal if their names are equal

**Note**: "Names being equal" refers to the direct name of a file or directory, not including the path (i.e., parent directory names should not be included). The `getName()` method of the `File` class returns the correct value.

## Methods to Implement

### 1. `static FsNode createNode(File file)`

Creates a new FsNode tree rooted at the given file system path.

**Examples**:
- Input: `"testFiles/A"`
    - Expected: Returns a directory node representing that directory, containing FsNodes representing the contents of that directory
- Input: `"testFiles/A/C.txt"`
    - Expected: Returns a text file node representing that file and its contents at the time the node was created
- Input: `"testFiles/A/D.doc"`
    - Expected: Returns a non-text file node representing that file

**Parameters**:
- `file`: An abstract path to an actual file or directory in the file system

**Returns**:
- The root FsNode of a tree representing the part of the file system rooted at the given path

### 2. `File getUnderlyingFile()`

**Returns**: The abstract path of the file or directory represented by the node

### 3. `List<FsNode> allNodes()`

Lists all files and directories in the file system structure rooted at the current node, represented as FsNodes.

**Behavior Specification**:
- If the node represents a file: The list contains the current node as its single element
- If the node is a directory: The list should follow a **pre-order depth-first search (DFS)** traversal of the file system tree
    - Each directory should explore its children in **alphabetical order** (using the natural ordering of `String.compareTo`)

**Example**:
- If this node represents `"testFiles/A"`, expect a list of FsNodes representing the following paths (in order):
  ```
  ["testFiles/A", 
   "testFiles/A/B", 
   "testFiles/A/B/C.txt",
   "testFiles/A/C.txt", 
   "testFiles/A/D.doc"]
  ```

**Returns**:
- A list representing the pre-order DFS traversal of the file system tree rooted at the current node

## Implementation Requirements

### Provided Implementations

1. **`FsNonTextFile`** (Complete)
    - Represents files that are neither directories nor text files
    - Implements `equals()`, `hashCode()`, and `toString()` methods
    - `allNodes()` returns a list containing only itself

2. **`FsTextFile`** (Complete)
    - Represents text files
    - Reads and stores all text lines from the file during construction
    - Provides `getLines()` method to retrieve text lines
    - Implements `equals()` based on both name and content
    - `allNodes()` returns a list containing only itself

### Implementations to Complete

3. **`FsDir`** (Incomplete)
    - Represents directories
    - Must store a list of child nodes
    - Must implement:
        - Constructor: Read directory contents and create child nodes
        - All methods from the `FsNode` interface
        - `equals()` and `hashCode()` methods
        - Ensure child nodes are sorted in alphabetical order

4. **`FsNode.createNode(File file)`** (Incomplete)
    - Static factory method
    - Must create the appropriate implementation class instance based on file type (directory/text file/non-text file)
    - Decision logic:
        - If directory: return `FsDir`
        - If filename ends with `.txt`: return `FsTextFile`
        - Otherwise: return `FsNonTextFile`

## Suggested Test Cases

Assume the following file system structure:
```
testFiles/
├── A/
│   ├── B/
│   │   └── C.txt
│   ├── C.txt
│   └── D.doc
```

You can test:
1. Creating nodes of various types
2. Verifying the pre-order traversal order of `allNodes()`
3. Testing equality of different nodes
4. Testing deep traversal of directories

## Technical Points

- Use `File.isDirectory()` to determine if a path is a directory
- Use `File.listFiles()` to get directory contents
- Use `Arrays.sort()` or `Collections.sort()` to sort child nodes
- Use `BufferedReader` and `FileReader` to read text files
- Correctly implement the `equals()` and `hashCode()` contract
- Handle exceptions such as IOException
