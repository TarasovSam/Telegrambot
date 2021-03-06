package com.github.telegrambot.javarushclient;

import com.github.telegrambot.javarushclient.dto.GroupCountRequestArgs;
import com.github.telegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.github.telegrambot.javarushclient.dto.GroupInfo;
import com.github.telegrambot.javarushclient.dto.GroupRequestArgs;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of the {@link JavaRushGroupClient} interface.
 */
@Component
public class JavaRushGroupClientImpl implements JavaRushGroupClient {
    private final String javaRushApiGroupPath;

    public JavaRushGroupClientImpl(@Value("${javarush.api.path}") String javaRushApi) {
        this.javaRushApiGroupPath = javaRushApi + "/groups";
    }

    @Override
    public List<GroupInfo> getGroupList(GroupRequestArgs requestArgs) {
        return Unirest.get(javaRushApiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupInfo>>() {
                })
                .getBody();
    }

    @Override
    public List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs) {
        return Unirest.get(javaRushApiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupDiscussionInfo>>() {
                })
                .getBody();
    }

    @Override
    public Integer getGroupCount(GroupCountRequestArgs countRequestArgs) {
        return Integer.valueOf(
                Unirest.get(String.format("%s/count", javaRushApiGroupPath))
                        .queryString(countRequestArgs.populateQueries())
                        .asString()
                        .getBody()
        );
    }

    @Override
    public GroupDiscussionInfo getGroupById(Integer id) {
        return Unirest.get(String.format("%s/group%s", javaRushApiGroupPath, id.toString()))
                .asObject(GroupDiscussionInfo.class)
                .getBody();
    }
}
