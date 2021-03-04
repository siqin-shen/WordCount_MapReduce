import java.util.*;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private static final int MISSING = 9999;

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		String line = value.toString();
		String[] words= line.split("[\\W]");
		String[] targets = {"hackathon", "dec", "chicago", "java"};

		HashMap<String, Integer> Temp= new HashMap<String, Integer>();

		for (String word : words){
			Temp.put(word.toLowerCase(), 0);
		}

		for (int i = 0; i < targets.length; i++){
			if (Temp.containsKey(targets[i])) {
				context.write(new Text(targets[i]), new IntWritable(1));
			}
			else {
				context.write(new Text(targets[i]), new IntWritable(0));
			}
		}
		
	}
}
