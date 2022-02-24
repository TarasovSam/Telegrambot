package com.github.telegrambot.javarushclient;

import com.github.telegrambot.javarushclient.dto.GroupCountRequestArgs;
import com.github.telegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.github.telegrambot.javarushclient.dto.GroupInfo;
import com.github.telegrambot.javarushclient.dto.GroupRequestArgs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.telegrambot.javarushclient.dto.GroupInfoType.TECH;

@DisplayName("Integration-level testing for JavaRushGroupClientImplTest")
public class JavaRushGroupClientTest {
    private final JavaRushGroupClient groupClient = new JavaRushGroupClientImpl("https://javarush.ru/api/1.0/rest");

    @Test
    public void shouldProperlyGetGroupsWithEmptyArgs() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        List<GroupInfo> groupInfoList = groupClient.getGroupList(args);

        //then
        Assertions.assertNotNull(groupInfoList);
        Assertions.assertFalse(groupInfoList.isEmpty());
    }

    @Test
    public void shouldProperlyGetWithOffSetAndLimit() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        //when
        List<GroupInfo> groupInfoList = groupClient.getGroupList(args);

        //then
        Assertions.assertNotNull(groupInfoList);
        Assertions.assertEquals(3, groupInfoList.size());
    }

    @Test
    public void shouldProperlyGetGroupsDiscWithEmptyArgs() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        List<GroupDiscussionInfo> groupDiscussionInfoList = groupClient.getGroupDiscussionList(args);

        //then
        Assertions.assertNotNull(groupDiscussionInfoList);
        Assertions.assertFalse(groupDiscussionInfoList.isEmpty());
    }

    @Test
    public void shouldProperlyGetGroupDiscWithOffSetAndLimit() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        //when
        List<GroupDiscussionInfo> groupDiscussionInfoList = groupClient.getGroupDiscussionList(args);

        //then
        Assertions.assertNotNull(groupDiscussionInfoList);
        Assertions.assertEquals(3, groupDiscussionInfoList.size());
    }

    @Test
    public void shouldProperlyGetGroupCount() {
        //given
        GroupCountRequestArgs args = GroupCountRequestArgs.builder()
                .type(TECH)
                .build();

        //when
        Integer groupCount = groupClient.getGroupCount(args);

        //then
        Assertions.assertEquals(30, groupCount);
    }

    @Test
    public void shouldProperlyGetGroupTECHCount() {
        //given
        GroupCountRequestArgs args = GroupCountRequestArgs.builder()
                .type(TECH)
                .build();

        //when
        Integer groupCount = groupClient.getGroupCount(args);

        //then
        Assertions.assertEquals(7, groupCount);
    }

    @Test
    public void shouldProperlyGetGroupById() {
        //given
        Integer androidGroupId = 16;

        //when
        GroupDiscussionInfo groupById = groupClient.getGroupById(androidGroupId);

        //then
        Assertions.assertNotNull(groupById);
        Assertions.assertEquals(16, groupById.getId());
        Assertions.assertEquals(TECH, groupById.getType());
        Assertions.assertEquals("android", groupById.getKey());
    }
}
