package lebedev.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import lebedev.addressbook.model.GroupData;
import lebedev.addressbook.model.Groups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {
  Logger logger = LoggerFactory.getLogger(GroupCreationTest.class);

  @BeforeMethod
  public void groupCreationPreconditionsCheck() {
    appManager.goTo().groupPage();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsCSV() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/generatedGroups.csv"))) {
      List<Object[]> list = new ArrayList<>();
      String line = reader.readLine();
      while (line != null) {
        String[] split = line.split(";");
        list.add(new Object[]{(new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2]))});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsXML() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/generatedGroups.xml"))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(GroupData.class);
      xStream.allowTypes(new Class[]{GroupData.class});
      xStream.allowTypesByWildcard(new String[]{"lebedev.addressbook"});
      List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsJSON() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/generatedGroups.json"))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
      }.getType()); // List<GroupData>.class
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validGroupsJSON")
  public void groupCreation(GroupData groupThatWillBeCreated) {
    Groups beforeGroupList = appManager.group().all();

    appManager.group().create(groupThatWillBeCreated);

    assertThat(appManager.group().count(), equalTo(beforeGroupList.size() + 1));
    Groups afterGroupList = appManager.group().all();
    //appManager.goTo().homePage();

    assertThat(afterGroupList, equalTo(beforeGroupList.withAdded(groupThatWillBeCreated.withId(afterGroupList.stream()
            .mapToInt(GroupData::getId).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void badGroupCreation() {
    Groups beforeGroupList = appManager.group().all();
    GroupData groupForList = new GroupData().withName("GroupName'").withHeader("GroupHeader").withFooter("GroupFooter");

    appManager.group().create(groupForList);
    assertThat(appManager.group().count(), equalTo(beforeGroupList.size()));
    Groups afterGroupList = appManager.group().all();
    appManager.goTo().homePage();
    assertThat(afterGroupList, equalTo(beforeGroupList));
  }
}




