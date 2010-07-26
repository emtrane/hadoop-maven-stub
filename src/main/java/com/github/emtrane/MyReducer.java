package com.github.emtrane;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable>
{
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException
    {
        long sum = 0L;

        for (LongWritable value : values)
        {
            sum += value.get();
        }

        // the key could also be NullWritable and the value could be any String/Text you want
        context.write(key, new LongWritable(sum));
    }
}

