import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import java.io.IOException;
import java.util.Iterator;

public class PedBikeRatioReducer extends MapReduceBase implements
        Reducer<Text, Text, Text, Text> {
    @Override
    public void reduce(Text key, Iterator<Text> values,
                       OutputCollector<Text, Text> output,
                       Reporter reporter) throws IOException {
        int bikeTotal = 0;
        int pedTotal = 0;
        while (values.hasNext()) {
            String [] countMode = values.next().toString().split(":");
            if(countMode.length == 2){
                try{
                    String mode = countMode[0].trim();
                    int count = Integer.parseInt(countMode[1].trim());

                    if(mode.equals("Bike")){
                        bikeTotal += count;
                    } else if (mode.equals("Ped")) {
                        pedTotal += count;
                    }
                }catch(NumberFormatException e){
                    System.err.print("Bad count value:" + countMode[1] + " for key: " + key.toString());
                } catch (Exception e) {
                    System.err.println("Error while processing value:" + values.next().toString());
                }
            }
        }
        String ratio;
        if(bikeTotal > 0){
            ratio = String.format("%.5f", (double) pedTotal / bikeTotal);
        }else{
            ratio = "Undefined";
        }
        output.collect(key, new Text(ratio));
    }
}
