import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class RatioDriver {
    public static void main(String[] args) {
        JobClient client = new JobClient();

        //Create the configuration object for the job
        JobConf jobConf = new JobConf(RatioDriver.class);

        //Set a name for the job
        jobConf.setJobName("PedBikeRatio");

        //Specify the type of output key and value
        jobConf.setOutputKeyClass(Text.class);
        jobConf.setOutputValueClass(Text.class);

        //Specify names of Mapper and Reducer class
        jobConf.setMapperClass(PedBikeRatioMapper.class);
        jobConf.setReducerClass(PedBikeRatioReducer.class);

        //Specify formats of the data type of input and output
        jobConf.setInputFormat(TextInputFormat.class);
        jobConf.setOutputFormat(TextOutputFormat.class);

        //Set input and output directories using command line arguments
        //arg[0] = name of input directory on HDFS and arg[1] = name of output directory to be created
        FileInputFormat.setInputPaths(jobConf, new Path(args[0]));
        FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));

        client.setConf(jobConf);
        try {
            //run the job
            JobClient.runJob(jobConf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
