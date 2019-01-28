import java.io.File;

public class DirWalker {
	final static long startTime = System.currentTimeMillis();
	static String DirPath="C:\\Users\\Manoj\\Documents\\Programming software in BE\\Sample Data\\Sample Data";
    public static void walk( String path ) {
        File root = new File( path );
        File[] list = root.listFiles();
        if (list == null) return;
        else {
        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
            }
            else {
            	if(f.getAbsolutePath().toString().contains(".csv")){
            		SimpleCsvParser.ReadCSV(f.getAbsolutePath().toString());
            	}
            }
        }
        }
    }
public static void main(String[] args) {
	walk(DirPath);
	SimpleCsvParser.GetDetails();
}
}