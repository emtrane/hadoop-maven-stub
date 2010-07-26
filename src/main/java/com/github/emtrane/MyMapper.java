package com.github.emtrane;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable>
{
    @Override
    protected void map(LongWritable offset, Text input, Context context) throws IOException, InterruptedException
    {
        String line = input.toString();
        System.out.println("Processing " + line);

        String[] tokens = line.split(" ");

        for (String token : tokens)
        {
            context.write(new Text(token), new LongWritable(1L));
        }
    }
}

