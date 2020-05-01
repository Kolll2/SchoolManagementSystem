package helpers;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class ConsoleHelper {

    public static String toUTF8(String str){
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(str);
        byte[] b = byteBuffer.array();
        return new String(b);
    }
}
