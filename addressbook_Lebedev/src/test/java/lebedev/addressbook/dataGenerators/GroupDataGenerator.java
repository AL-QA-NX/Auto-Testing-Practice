package lebedev.addressbook.dataGenerators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import lebedev.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter (names = "-c", description = "Group count")
    public int groupDataCount;

    @Parameter (names = "-f", description = "Target file")
    public String groupDataFileAddress;

    @Parameter (names = "-t", description = "Data type")
    public String groupDataType;


    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommanderGroupGenerator = new JCommander(generator);
        try {
            jCommanderGroupGenerator.parse(args);
        } catch (ParameterException exception) {
            jCommanderGroupGenerator.usage();
            return;
        }
        generator.run ();
    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups (groupDataCount);
        if (groupDataType.equals("csv")){
            saveAsCSV(groups, new File(groupDataFileAddress));
        } else if (groupDataType.equals("xml")){
            saveAsXML(groups, new File(groupDataFileAddress));
        } else if (groupDataType.equals("json")) {
            saveAsJSON(groups, new File(groupDataFileAddress));
        } else {
            System.out.println("Unrecognized format " + groupDataType + ". Choose between csv, xml or json.");
        }
    }

    private void saveAsJSON(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXML(List<GroupData> groups, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        String xml = xStream.toXML(groups);
        try (Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<GroupData> groups, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (GroupData group : groups) {
                writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
            }
        }
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("generated group name %s", i))
                    .withHeader(String.format("generated group header %s", i))
                    .withFooter(String.format("generated group footer %s", i)));
        }
        return groups;
    }
}
