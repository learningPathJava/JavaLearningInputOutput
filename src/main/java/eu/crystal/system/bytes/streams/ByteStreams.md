# Byte Streams
## For handling input and output of bytes

- Byte Streams in Java are used to perform input and output operations on 8 bit data. 
- Streams are defined mainly by two abstract classes namely InputStream and OutputStream.

Subclasses:
- FileInputStream	
    - Input stream to read a file.
- FileOutputStream
    - Output stream to write to a file.
- DataInputStream
    - Input stream to read integers and floating point numbers.
- DataOutputStream
    - Output stream to write integers and floating point numbers.

Some of the most important key methods are:
- read()
    - This method is used to read bytes of data.
- write()
    - This method is used to write bytes of data.
- close()
    - This method is used to close the current input or output stream.


Reading from an InputStream
```java
public int read(byte[] bytes, int offset, int length) throws IOException
// Read "length" number of bytes, store in bytes array starting from offset of index.
public int read(byte[] bytes) throws IOException
// Same as read(bytes, 0, bytes.length)
```

Writing to an OutputStream
```java
public void write(byte[] bytes, int offset, int length) throws IOException
// Write "length" number of bytes, from the bytes array starting from offset of index.
public void write(byte[] bytes) throws IOException
// Same as write(bytes, 0, bytes.length)
```

Opening & Closing I/O Streams
```java
FileInputStream in = null;
......  
try {
   in = new FileInputStream(...);  // Open stream
   ......
   ......
} catch (IOException ex) {
   ex.printStackTrace();
} finally {  // always close the I/O streams
   try {
      if (in != null) in.close();
   } catch (IOException ex) {
      ex.printStackTrace();
   }
}
```

JDK 1.7 introduces a new try-with-resources syntax, which automatically closes all the opened resources after try or catch, as follows. This produces much neater codes.
```java
try (FileInputStream in = new FileInputStream(...)) {
   ......
   ......
} catch (IOException ex) {
   ex.printStackTrace();
}  // Automatically closes all opened resource in try (...).
```

Buffered I/O Byte-Streams - BufferedInputStream & BufferedOutputStream

```java
FileInputStream fileIn = new FileInputStream("in.dat");
BufferedInputStream bufferIn = new BufferedInputStream(fileIn);
DataInputStream dataIn = new DataInputStream(bufferIn);
// or
DataInputStream in = new DataInputStream(
                        new BufferedInputStream(
                           new FileInputStream("in.dat")));
```