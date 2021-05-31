package org.eurostates.mosecommands.arguments.area;

import org.eurostates.mosecommands.arguments.CommandArgument;
import org.eurostates.mosecommands.context.CommandArgumentContext;
import org.eurostates.mosecommands.context.CommandContext;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StateAttribArgument implements CommandArgument<String> {
    private final String id;

    // deez nuts

    public StateAttribArgument(String id){this.id = id; }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Map.Entry<String, Integer> parse(CommandContext context, CommandArgumentContext<String> argument) throws IOException {
        String text = context.getCommand()[argument.getFirstArgument()];
        return new AbstractMap.SimpleImmutableEntry<>(text, argument.getFirstArgument() + 1);
    }

    @Override
    public List<String> suggest(CommandContext commandContext, CommandArgumentContext<String> argument) {
        String peek = commandContext.getCommand()[argument.getFirstArgument()];
        List<String> list = new ArrayList<>();
        if("relations".startsWith(peek.toLowerCase())){
            list.add("relations");
        }
        if("name".startsWith(peek.toLowerCase())){
            list.add("name");
        }
        if("tag".startsWith(peek.toLowerCase())){
            list.add("tag");
        }
        if("color".startsWith(peek.toLowerCase())){
            list.add("color");
        }

        return list;
    }
}