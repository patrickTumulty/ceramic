package com.ptumulty.ceramic_api.utils;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils
{
    public static void ensureDirectoryExists(Path path) throws IOException
    {
        try
        {
            Files.createDirectory(path);
        }
        catch (FileAlreadyExistsException e)
        {
            // Ignore
        }
    }

    public static long getFileSize(Path path)
    {
        try
        {
            return Files.size(path);
        }
        catch (IOException e)
        {
            return 0;
        }
    }
}
