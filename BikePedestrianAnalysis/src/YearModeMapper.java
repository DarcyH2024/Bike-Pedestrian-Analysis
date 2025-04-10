import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import java.io.IOException;

public class YearModeMapper extends MapReduceBase implements
        Mapper<LongWritable, Text, Text, IntWritable>{
    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output,
                    Reporter reporter) throws IOException{
        String line = value.toString().trim();
        //line = line.replaceAll("\\s+", " ");
        String[] data = line.split("\\s+");

        if (data.length == 3) {
            String year = data[0].trim();
            String mode = data[1].trim();
            String count = data[2].trim();

            try {
                int countAsInt = Integer.parseInt(count);
                String yearMode = year + "-" + mode;

                output.collect(new Text(yearMode), new IntWritable(countAsInt));

            } catch (NumberFormatException e) {
                System.out.println("Invalid count value: " + count + " in line: " + line);
            }
        } else {
            System.err.println("Invalid line format: " + line + ". Field count: " + data.length);
        }
    }
}