package com.github.emtrane;

import org.apache.commons.cli.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            CommandLine line = parseCommandLine(args);

            Configuration conf = new Configuration();

            Job job = new Job(conf, line.getOptionValue('n', "Generic Job"));

            // I/O
            FileInputFormat.addInputPath(job, new Path(line.getOptionValue('i') + "*"));
            FileOutputFormat.setOutputPath(job, new Path(line.getOptionValue('o')));

            job.setInputFormatClass(TextInputFormat.class);
            job.setOutputFormatClass(TextOutputFormat.class);

            // job.setNumReduceTasks(0); // enable this line if you just want mappers to directly write the output
            job.setJarByClass(MyMapper.class);
            job.setMapperClass(MyMapper.class);

            // why do I need to set the following two in addition to the typed arguments in the Mapper?
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(LongWritable.class);

            job.setReducerClass(MyReducer.class);
            job.waitForCompletion(true);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static CommandLine parseCommandLine(String[] args) throws ParseException {
        CommandLineParser parser = new PosixParser();

        Options options = new Options();
        options.addOption("i", "input", true, "Input path to read from");
        options.addOption("o", "output", true, "Output path to write to");
        options.addOption("n", "name", true, "Name of the job");

        CommandLine line = parser.parse(options, args);

        if (!line.hasOption('i') || !line.hasOption('o'))
        {
            new HelpFormatter().printHelp("Main", options);
            System.exit(1);
        }

        return line;
    }
}

