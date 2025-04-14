import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;


public class YearModeReducer extends MapReduceBase implements
        Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    public void reduce(Text key, Iterator<IntWritable> values,
                       OutputCollector<Text, IntWritable> output,
                       Reporter reporter) throws IOException{
        int totalSum = 0;
        while (values.hasNext()) {
            IntWritable value = values.next();
            System.out.println("The value for " + key + " is " + value.get());
            totalSum += value.get();
        }
        output.collect(key, new IntWritable(totalSum));
    }
}
