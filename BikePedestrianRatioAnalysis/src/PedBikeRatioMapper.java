import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class PedBikeRatioMapper extends MapReduceBase implements
        Mapper<Object, Text, Text, Text> {
    @Override
    public void map(Object key, Text value, OutputCollector<Text, Text> output,
                    Reporter reporter){
        String[] data = value.toString().split(",");
        if(data.length != 3) {
            System.err.println("Line does not contain 3 values: " + value);
            return;
        }

        String year = data[0].trim();
        String mode = data[1].trim();
        String count = data[2].trim();

        try{
            int parsedCount = Integer.parseInt(count);
            output.collect(new Text(year), new Text(mode+ ":" + parsedCount));

        }catch (NumberFormatException e) {
            System.err.println("Invalid count value : " + count + " in line:" + value);

        }catch (Exception e) {
            System.err.println("Error exists in the mapper line: " + value + "-" + e.getMessage());
        }
    }
}
