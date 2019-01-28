import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.opencsv.CSVWriter;
public class SimpleCsvParser {
	static Boolean isNewFile=true,validRow=true;
	static int totalRows=0,skippedRows=0, validRows=0,totalFiles=0;
	static long totalExectime;
	public static void ReadCSV(String path) {
		Reader in;
		totalFiles++;
			try {
				in = new FileReader(path);
				Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
				String dataRow[]=new String[20];
				for (CSVRecord record : records) {
					totalRows++;
					for(int j = 0;j<record.size();j++) {
						if(isNewFile&&!record.get(j).isEmpty()) {
								dataRow[j]=record.get(j);
							}
						else {
							skippedRows++;
							isNewFile=true;
							validRow=false;
							break;
						}
					}
					if(validRow) {
						validRow=true;
						WriteCSV(dataRow);
					}
					validRow=true;
				}							
			} catch ( IOException e) {
				e.printStackTrace();
			}
			isNewFile=false;
	}
	public static void WriteCSV(String row[]) throws IOException {
		String targetLoc="./FinalCSV.csv";
		File fileExist = new File(targetLoc);
		if(!fileExist.exists()) {
			Writer writer = Files.newBufferedWriter(Paths.get(targetLoc));
			CSVWriter csvWriter = new CSVWriter(writer);
			csvWriter.writeNext(row);
			csvWriter.close();
		}
		else
		{
			CSVWriter writer = new CSVWriter(new FileWriter(targetLoc, true));
			writer.writeNext(row);
			writer.close();
		}
	}
	public static void  GetDetails(){
		skippedRows=skippedRows-(totalFiles-1);
		validRows=totalRows-skippedRows;
		final long endTime = System.currentTimeMillis();
		totalExectime=DirWalker.startTime-endTime;
		SimpleLogging.loggerFile(validRows,skippedRows,totalExectime);
	}

}
