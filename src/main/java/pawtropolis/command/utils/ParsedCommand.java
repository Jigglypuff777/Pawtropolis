package pawtropolis.command.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ParsedCommand {
    private final String commandType;
    private final List<String> parameters;
}