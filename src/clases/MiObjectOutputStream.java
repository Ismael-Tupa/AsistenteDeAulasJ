package clases;

import java.io.*;

/**
 *
 * @author ISMAEL
 */
public class MiObjectOutputStream extends ObjectOutputStream{
    protected void writeStreamHeader() throws IOException{
    
    }

    public MiObjectOutputStream() throws IOException{
        super();
    }
    public MiObjectOutputStream(OutputStream out) throws IOException{
        super(out);
    }
}
